package com.hezhiheng.musicplayer.ui.musiclist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hezhiheng.musicplayer.R;
import com.hezhiheng.musicplayer.adapter.MusicListContentAdapter;
import com.hezhiheng.musicplayer.db.entity.Music;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MusicListFragment extends Fragment {
    private Unbinder mBind;

    private List<Music> mList = Music.createList();

    @BindView(R.id.music_list_container)
    RecyclerView mMusicContainer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.music_list_layout, container, false);
        mBind = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mBind.unbind();
    }

    private void initView() {
        MusicListContentAdapter adapter = new MusicListContentAdapter(mList, getContext());
        mMusicContainer.setAdapter(adapter);
        mMusicContainer.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}
