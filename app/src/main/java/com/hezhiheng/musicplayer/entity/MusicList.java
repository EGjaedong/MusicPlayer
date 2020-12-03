package com.hezhiheng.musicplayer.entity;

import java.util.ArrayList;
import java.util.List;

public class MusicList {
    private String title;
    private int count;

    public MusicList(String title, int count) {
        this.title = title;
        this.count = count;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public static List<MusicList> createList() {
        List<MusicList> lists = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            lists.add(new MusicList("音乐列表" + i, 233 + i));
        }
        return lists;
    }
}
