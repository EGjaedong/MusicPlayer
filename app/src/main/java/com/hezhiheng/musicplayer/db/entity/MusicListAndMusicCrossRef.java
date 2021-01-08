package com.hezhiheng.musicplayer.db.entity;

import androidx.room.Entity;

@Entity(tableName = "cross_ref", primaryKeys = {"musicListId", "musicId"})
public class MusicListAndMusicCrossRef {
    private int musicListId;
    private int musicId;

    public int getMusicListId() {
        return musicListId;
    }

    public void setMusicListId(int musicListId) {
        this.musicListId = musicListId;
    }

    public int getMusicId() {
        return musicId;
    }

    public void setMusicId(int musicId) {
        this.musicId = musicId;
    }
}
