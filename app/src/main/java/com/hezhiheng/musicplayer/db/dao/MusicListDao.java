package com.hezhiheng.musicplayer.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.hezhiheng.musicplayer.db.entity.MusicList;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;

@Dao
public interface MusicListDao {
    @Query("select * from music_list")
    Flowable<List<MusicList>> getAllList();

    @Insert
    Maybe<List<Long>> saveAll(List<MusicList> musicLists);
}
