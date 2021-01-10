package com.hezhiheng.musicplayer.repository;

import com.hezhiheng.musicplayer.MusicPlayerApplication;
import com.hezhiheng.musicplayer.db.dao.MusicListDao;
import com.hezhiheng.musicplayer.db.entity.MusicList;
import com.hezhiheng.musicplayer.db.roomdatabases.AppDatabase;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;

public class MusicListRepository {
    private MusicListDao mDao =
            AppDatabase.getInstance(MusicPlayerApplication.getInstance().getApplicationContext()).getMusicListDao();

    public Maybe<List<Long>> saveAll(List<MusicList> lists) {
        return mDao.saveAll(lists);
    }

    public Flowable<List<MusicList>> getAllMusicLists() {
        return mDao.getAllList();
    }

    public Maybe<Long> saveOne(MusicList musicList) {
        return mDao.saveOne(musicList);
    }

    public Maybe<MusicList> findOneByTitle(String title) {
        return mDao.findOneByTitle(title);
    }
}
