package com.hezhiheng.musicplayer.ui.main;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hezhiheng.musicplayer.R;
import com.hezhiheng.musicplayer.adapter.MusicListAdapter;
import com.hezhiheng.musicplayer.entity.MusicList;
import com.hezhiheng.musicplayer.ui.musiclist.MusicListActivity;

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
    @BindView(R.id.my_favorite_music_list_container)
    ConstraintLayout mMyFavoriteListContainer;

    private List<MusicList> lists = MusicList.createList();

    private final MusicListAdapter.OnItemClickListener itemClickListener = (view, position) -> {
        Intent intent = new Intent(MainActivity.this, MusicListActivity.class);
        intent.putExtra("position", position);
        startActivity(intent);
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        ButterKnife.bind(this);

        MusicListAdapter musicListAdapter = new MusicListAdapter(lists, this);
        musicListAdapter.setItemClickListener(itemClickListener);
        mMusicListContainer.setAdapter(musicListAdapter);
        mMusicListContainer.setLayoutManager(new LinearLayoutManager(this));
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
}
