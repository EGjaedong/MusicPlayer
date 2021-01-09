package com.hezhiheng.musicplayer.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(tableName = "cross_ref", primaryKeys = {"music_list_id", "music_id"})
public class MusicListAndMusicCrossRef {
    @ColumnInfo(name = "music_list_id", index = true)
    private int musicListId;
    @ColumnInfo(name = "music_id", index = true)
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
