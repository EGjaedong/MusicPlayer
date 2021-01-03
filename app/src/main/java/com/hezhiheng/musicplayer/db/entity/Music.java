package com.hezhiheng.musicplayer.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "music")
public class Music {
    @PrimaryKey(autoGenerate = true)
    private int musicId;
    @ColumnInfo
    private String name;
    @ColumnInfo
    private String singer;
    @ColumnInfo
    private String album;

    public Music() {
    }

    @Ignore
    public Music(String name, String singer, String album) {
        this.name = name;
        this.singer = singer;
        this.album = album;
    }

    public int getMusicId() {
        return musicId;
    }

    public void setMusicId(int musicId) {
        this.musicId = musicId;
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
