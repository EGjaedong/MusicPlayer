package com.hezhiheng.musicplayer.entity;

import java.util.ArrayList;
import java.util.List;

public class Music {
    private String name;
    private String singer;
    private String album;

    public Music() {
    }

    public Music(String name, String singer, String album) {
        this.name = name;
        this.singer = singer;
        this.album = album;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public static List<Music> createList() {
        List<Music> lists = new ArrayList<>();
        lists.add(new Music("晴天", "周杰伦", "叶惠美"));
        lists.add(new Music("兰亭序", "周杰伦", "跨时代"));
        lists.add(new Music("稻香", "周杰伦", "魔杰座"));
        return lists;
    }
}
