package com.hezhiheng.musicplayer.ui;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hezhiheng.musicplayer.R;
import com.hezhiheng.musicplayer.adapter.MusicListAdapter;
import com.hezhiheng.musicplayer.entity.MusicList;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends Activity {
    @BindView(R.id.created_music_list_container)
    RecyclerView mMusicListContainer;

    private List<MusicList> lists = MusicList.createList();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        ButterKnife.bind(this);

        MusicListAdapter musicListAdapter = new MusicListAdapter(lists, this);
        mMusicListContainer.setAdapter(musicListAdapter);
        mMusicListContainer.setLayoutManager(new LinearLayoutManager(this));
    }
}
