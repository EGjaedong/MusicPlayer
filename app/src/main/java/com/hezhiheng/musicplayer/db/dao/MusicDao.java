package com.hezhiheng.musicplayer.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.hezhiheng.musicplayer.db.entity.Music;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;

@Dao
public interface MusicDao {
    @Query("select * from music")
    Flowable<List<Music>> getAllMusic();

    @Insert
    Maybe<List<Long>> saveAll(List<Music> musics);
}
