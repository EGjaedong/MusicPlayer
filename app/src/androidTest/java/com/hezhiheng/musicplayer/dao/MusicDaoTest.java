package com.hezhiheng.musicplayer.dao;

import android.util.Log;

import androidx.room.Room;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.hezhiheng.musicplayer.db.dao.MusicDao;
import com.hezhiheng.musicplayer.db.entity.Music;
import com.hezhiheng.musicplayer.db.roomdatabases.AppDatabase;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.functions.Consumer;

@RunWith(AndroidJUnit4.class)
public class MusicDaoTest {
    private static final String TAG = MusicDaoTest.class.getSimpleName();

    private AppDatabase mDatabase;
    private MusicDao mMusicDao;

    @Before
    public void init() {
        mDatabase = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getInstrumentation().getContext(),
                AppDatabase.class)
                // 允许在主线程中运行，仅用于测试
                .allowMainThreadQueries()
                .build();
        mMusicDao = mDatabase.getMusicDao();
    }

    @After
    public void close() {
        mDatabase.close();
    }

    @Test
    public void testSaveAll() {
        List<Music> musics = Music.createList();
        Maybe<List<Long>> insertResult = mMusicDao.saveAll(musics);
        insertResult.subscribe(list -> {
            Assert.assertEquals(Long.valueOf(3), Long.valueOf(list.size()));
            Log.i(TAG, "insert success. Insert number is :" + list.size());
        }).dispose();
    }

    @Test
    public void testSaveOne() {
        mMusicDao.saveOne(new Music("烟花易冷", "周杰伦", "跨时代"))
                .subscribe(aLong -> {
                    Assert.assertEquals(Long.valueOf(1), aLong);
                    Log.i(TAG, "insert success. insert music id is :" + aLong);
                }).dispose();
    }

    @Test
    public void testFindOneByName() {
        mMusicDao.saveOne(new Music("烟花易冷", "周杰伦", "跨时代"))
                .subscribe(aLong -> {
                    mMusicDao.findOneByName("烟花易冷")
                            .subscribe(music -> {
                                Assert.assertEquals("烟花易冷", music.getName());
                                Log.i(TAG,
                                        "insert success. insert music id is :" + aLong + " and name is :" + music.getName());
                            }).dispose();
                }).dispose();
    }

    @Test
    public void testFindOneById() {
        mMusicDao.saveOne(new Music("烟花易冷", "周杰伦", "跨时代")).subscribe(aLong -> {
            mMusicDao.findOneById(1).subscribe(music -> {
                Assert.assertEquals(aLong, Long.valueOf(music.getMusicId()));
            }).dispose();
        }).dispose();
    }
}
