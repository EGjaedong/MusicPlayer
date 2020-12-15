package com.hezhiheng.musicplayer.ui;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;

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
    @BindView(R.id.main_page_header_container)
    LinearLayout mMainPageHeaderContainer;
    @BindView(R.id.main_page_scroll)
    ScrollView mScrollview;
    @BindView(R.id.main_page_user_info_container)
    LinearLayout mMainUserInfoContainer;

    private List<MusicList> lists = MusicList.createList();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        ButterKnife.bind(this);

        MusicListAdapter musicListAdapter = new MusicListAdapter(lists, this);
        mMusicListContainer.setAdapter(musicListAdapter);
        mMusicListContainer.setLayoutManager(new LinearLayoutManager(this));

        mScrollview.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                Rect scrollRect = new Rect();
                mScrollview.getHitRect(scrollRect);
                if (mMainUserInfoContainer.getLocalVisibleRect(scrollRect)) {
                    mMainUserInfoContainer.setVisibility(View.VISIBLE);
                    mMainPageHeaderContainer.setVisibility(View.INVISIBLE);
                } else {
                    mMainUserInfoContainer.setVisibility(View.INVISIBLE);
                    mMainPageHeaderContainer.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}
