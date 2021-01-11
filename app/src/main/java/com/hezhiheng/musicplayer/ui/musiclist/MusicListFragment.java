package com.hezhiheng.musicplayer.ui.musiclist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hezhiheng.musicplayer.R;
import com.hezhiheng.musicplayer.adapter.MusicListContentAdapter;
import com.hezhiheng.musicplayer.db.entity.Music;
import com.hezhiheng.musicplayer.ui.playerpage.PlayPageFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MusicListFragment extends Fragment {
    private Unbinder mBind;
    private FragmentActivity mActivity;
    private List<Music> mList = Music.createList();
    MusicListContentAdapter mAdapter;

    @BindView(R.id.music_list_container)
    RecyclerView mMusicContainer;

    private final MusicListContentAdapter.OnItemClickListener itemClickListener = (view, position) -> {
        mActivity.getSupportFragmentManager().beginTransaction().add(
                mActivity.findViewById(R.id.main_page_content_container).getId(), new PlayPageFragment()).commit();
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.music_list_layout, container, false);
        mBind = ButterKnife.bind(this, view);
        mActivity = getActivity();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        initView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mBind.unbind();
    }

    private void initView() {
        mAdapter = new MusicListContentAdapter(mList, getContext());
        mMusicContainer.setAdapter(mAdapter);
        mMusicContainer.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}
