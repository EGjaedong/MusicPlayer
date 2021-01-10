package com.hezhiheng.musicplayer.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.hezhiheng.musicplayer.db.entity.MusicListAndMusicCrossRef;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;

@Dao
public interface MusicListAndMusicCrossRefDao {
    @Insert
    Maybe<Long> saveOne(MusicListAndMusicCrossRef crossRef);

    /*@Insert
    Maybe<List<Long>> saveAll(List<MusicListAndMusicCrossRef> crossRefs);*/

    @Query("select * from cross_ref where music_list_id = :id")
    Flowable<List<MusicListAndMusicCrossRef>> findAllMusicInOneMusicList(int id);
}
