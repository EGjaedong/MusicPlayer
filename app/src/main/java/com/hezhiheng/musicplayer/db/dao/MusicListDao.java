package com.hezhiheng.musicplayer.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.hezhiheng.musicplayer.db.entity.MusicList;
import com.hezhiheng.musicplayer.db.entity.MusicListWithMusic;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;

@Dao
public interface MusicListDao {
    @Query("select * from music_list")
    Flowable<List<MusicList>> getAllList();

    @Insert
    Maybe<List<Long>> saveAll(List<MusicList> musicLists);

    @Insert
    Maybe<Long> saveOne(MusicList musicList);

    @Transaction
    @Query("select * from music_list")
    Flowable<List<MusicListWithMusic>> getMusicListWithMusic();

    @Query("select * from music_list where title = :title")
    Maybe<MusicList> findOneByTitle(String title);

    @Query("select * from music_list where musicListId = :id")
    Maybe<MusicList> findOneById(int id);
}
