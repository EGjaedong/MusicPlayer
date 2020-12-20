package com.hezhiheng.musicplayer.ui.main;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hezhiheng.musicplayer.R;
import com.hezhiheng.musicplayer.adapter.MusicListAdapter;
import com.hezhiheng.musicplayer.entity.MusicList;
import com.hezhiheng.musicplayer.ui.musiclist.MusicListActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainPageContent extends Fragment {
    private Unbinder mBind;

    @BindView(R.id.created_music_list_container)
    RecyclerView mMusicListContainer;
    @BindView(R.id.main_page_header_container)
    LinearLayout mMainPageHeaderContainer;
    @BindView(R.id.main_page_scroll)
    ScrollView mScrollview;
    @BindView(R.id.main_page_user_info_container)
    LinearLayout mMainUserInfoContainer;

    private List<MusicList> lists = MusicList.createList();

    private FragmentActivity mActivity;

    private final MusicListAdapter.OnItemClickListener itemClickListener = (view, position) -> {
        Intent intent = new Intent(getContext(), MusicListActivity.class);
        intent.putExtra("position", position);
        startActivity(intent);
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_page_content_layout, container, false);
        mBind = ButterKnife.bind(this, view);
        mActivity = getActivity();
        initView();
        return view;
    }

    private void initView() {
        MusicListAdapter musicListAdapter = new MusicListAdapter(lists, getContext());
        musicListAdapter.setItemClickListener(itemClickListener);
        mMusicListContainer.setAdapter(musicListAdapter);
        mMusicListContainer.setLayoutManager(new LinearLayoutManager(getContext()));
        mMusicListContainer.setNestedScrollingEnabled(false);

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

    @Override
    public void onDestroy() {
        super.onDestroy();
        mBind.unbind();
    }
}
