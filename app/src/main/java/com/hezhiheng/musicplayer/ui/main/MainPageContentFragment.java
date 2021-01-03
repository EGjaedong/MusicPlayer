package com.hezhiheng.musicplayer.ui.main;

import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hezhiheng.musicplayer.R;
import com.hezhiheng.musicplayer.adapter.MusicListAdapter;
import com.hezhiheng.musicplayer.db.entity.MusicList;
import com.hezhiheng.musicplayer.ui.musiclist.MusicListFragment;
import com.hezhiheng.musicplayer.viewmodel.MusicListViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainPageContentFragment extends Fragment {
    public static final String MAIN_PAGE_CONTENT_FRAGMENT_TAG = MainPageContentFragment.class.getSimpleName();

    private Unbinder mBind;
    private FragmentActivity mActivity;
    private MusicListViewModel mViewModel;
    private List<MusicList> mMusicLists;
    private Disposable mDisposable;
    private MusicListAdapter mAdapter;
    private boolean isFirstShow = true;

    @BindView(R.id.created_music_list_container)
    RecyclerView mMusicListContainer;
    @BindView(R.id.main_page_header_container)
    LinearLayout mMainPageHeaderContainer;
    @BindView(R.id.main_page_scroll)
    ScrollView mScrollview;
    @BindView(R.id.main_page_user_info_container)
    LinearLayout mMainUserInfoContainer;


    private final MusicListAdapter.OnItemClickListener itemClickListener = (view, position) -> {
        mActivity.getSupportFragmentManager().beginTransaction().add(
                mActivity.findViewById(R.id.main_page_content_container).getId(), new MusicListFragment()).commit();
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_page_content_layout, container, false);
        mBind = ButterKnife.bind(this, view);
        mActivity = getActivity();
        mViewModel = new ViewModelProvider(this, new MusicListViewModel.Factory()).get(MusicListViewModel.class);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        initView();
    }

    @Override
    public void onStop() {
        super.onStop();
        mDisposable.dispose();
    }

    private void initView() {
        getAllMusicLists();
        showMusicLists();
        setScrollListener();
    }

    private void getAllMusicLists() {
        mDisposable = mViewModel.getAllMusicList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(lists -> {
                    mMusicLists = lists;
                    if (isFirstShow) {
                        showMusicLists();
                    } else {
                        updateMusicLists();
                    }
                }, throwable -> {
                    Log.e(MAIN_PAGE_CONTENT_FRAGMENT_TAG, "Get all music lists error!");
                });
    }

    private void setScrollListener() {
        mScrollview.setOnScrollChangeListener((v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
            Rect scrollRect = new Rect();
            mScrollview.getHitRect(scrollRect);
            if (mMainUserInfoContainer.getLocalVisibleRect(scrollRect)) {
                mMainUserInfoContainer.setVisibility(View.VISIBLE);
                mMainPageHeaderContainer.setVisibility(View.INVISIBLE);
            } else {
                mMainUserInfoContainer.setVisibility(View.INVISIBLE);
                mMainPageHeaderContainer.setVisibility(View.VISIBLE);
            }
        });
    }

    private void showMusicLists() {
        if (mMusicLists != null && mMusicLists.size() != 0) {
            mAdapter = new MusicListAdapter(mMusicLists);
            mAdapter.setItemClickListener(itemClickListener);
            mMusicListContainer.setAdapter(mAdapter);
            mMusicListContainer.setLayoutManager(new LinearLayoutManager(getContext()));
            mMusicListContainer.setNestedScrollingEnabled(false);
            isFirstShow = !isFirstShow;
        }
    }

    private void updateMusicLists() {
        mAdapter.setMusicLists(mMusicLists);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mBind.unbind();
    }
}
