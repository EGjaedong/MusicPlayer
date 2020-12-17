package com.hezhiheng.musicplayer.ui.musiclist;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.hezhiheng.musicplayer.R;

public class MusicListActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.music_list_layout);
    }
}
