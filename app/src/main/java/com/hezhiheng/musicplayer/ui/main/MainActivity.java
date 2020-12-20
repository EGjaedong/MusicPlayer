package com.hezhiheng.musicplayer.ui.main;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import com.hezhiheng.musicplayer.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends FragmentActivity {
    @BindView(R.id.tab_bar_button_main_page)
    ImageButton mImageButtonMainPage;
    @BindView(R.id.main_page_content_container)
    FrameLayout mContainer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.main_page_content_container, new MainPageContentFragment())
                .addToBackStack(MainPageContentFragment.MAIN_PAGE_CONTENT_FRAGMENT_TAG).commit();
    }

    @OnClick({R.id.tab_bar_button_main_page})
    void viewClick(View view) {
        switch (view.getId()) {
            case R.id.tab_bar_button_main_page:
                getSupportFragmentManager().beginTransaction()
                        .replace(mContainer.getId(), new MainPageContentFragment()).commit();
                break;
        }
    }
}
