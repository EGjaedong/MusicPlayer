package com.hezhiheng.musicplayer.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "music_list")
public class MusicList {
    @PrimaryKey(autoGenerate = true)
    private int musicListId;
    @ColumnInfo
    private String title;

    public MusicList() {
    }

    @Ignore
    public MusicList(String title) {
        this.title = title;
    }

    @Ignore
    public MusicList(int musicListId, String title) {
        this.musicListId = musicListId;
        this.title = title;
    }

    public int getMusicListId() {
        return musicListId;
    }

    public void setMusicListId(int musicListId) {
        this.musicListId = musicListId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public static List<MusicList> createList() {
        List<MusicList> lists = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            lists.add(new MusicList("音乐列表" + i));
        }
        return lists;
    }
}
