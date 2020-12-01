package com.hezhiheng.musicplayer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class StartPageActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_page_layout);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.button_start_page})
    void btnClick() {
        Intent intent = new Intent(StartPageActivity.this, MainActivity.class);
        startActivity(intent);
        this.finish();
    }
}