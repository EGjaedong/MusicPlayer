package com.hezhiheng.musicplayer.dao;

import android.util.Log;

import androidx.room.Room;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.hezhiheng.musicplayer.db.dao.MusicDao;
import com.hezhiheng.musicplayer.db.dao.MusicListAndMusicCrossRefDao;
import com.hezhiheng.musicplayer.db.dao.MusicListDao;
import com.hezhiheng.musicplayer.db.entity.Music;
import com.hezhiheng.musicplayer.db.entity.MusicList;
import com.hezhiheng.musicplayer.db.entity.MusicListAndMusicCrossRef;
import com.hezhiheng.musicplayer.db.roomdatabases.AppDatabase;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.FlowableSubscriber;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

@RunWith(AndroidJUnit4.class)
public class CrossRefDaoTest {
    private static final String TAG = CrossRefDaoTest.class.getSimpleName();

    private AppDatabase mDatabase;
    private MusicListDao mMusicListDao;
    private MusicDao mMusicDao;
    private MusicListAndMusicCrossRefDao mCrossRefDao;

    @Before
    public void createDb() {
        mDatabase = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getInstrumentation().getContext(),
                AppDatabase.class)
                // 允许在主线程中运行，仅用于测试
                .allowMainThreadQueries()
                .build();
        mMusicListDao = mDatabase.getMusicListDao();
        mMusicDao = mDatabase.getMusicDao();
        mCrossRefDao = mDatabase.getCrossRefDao();
    }

    @After
    public void close() {
        mDatabase.close();
    }

    @Test
    public void testSaveOne() {
        MusicList musicList = new MusicList("我喜欢的歌");
        Music music = new Music("烟花易冷", "周杰伦", "跨时代");
        MusicListAndMusicCrossRef crossRef = new MusicListAndMusicCrossRef();
        mMusicListDao.saveOne(musicList).subscribe(aLong -> {
            mMusicDao.saveOne(music).subscribe(aLong1 -> {
                mMusicListDao.findOneById(1).subscribe(musicListInDb -> {
                    mMusicDao.findOneById(1).subscribe(musicInDb -> {
                        crossRef.setMusicListId(musicListInDb.getMusicListId());
                        crossRef.setMusicId(musicInDb.getMusicId());
                        mCrossRefDao.saveOne(crossRef).subscribe(aLong3 -> {
                            Assert.assertEquals(Long.valueOf(1), aLong3);
                            Log.i(TAG, "insert success. id is :" + aLong3);
                        }).dispose();
                    }).dispose();
                }).dispose();
            }).dispose();
        }).dispose();
    }

    @Test
    public void testFindAllMusicInOneMusicList() {
        MusicList musicList = new MusicList("我喜欢的歌");
        Music music = new Music("烟花易冷", "周杰伦", "跨时代");
        MusicListAndMusicCrossRef crossRef = new MusicListAndMusicCrossRef();
        mMusicListDao.saveOne(musicList).subscribe(aLong -> {
            mMusicDao.saveOne(music).subscribe(aLong1 -> {
                mMusicListDao.findOneById(1).subscribe(musicListInDb -> {
                    mMusicDao.findOneById(1).subscribe(musicInDb -> {
                        crossRef.setMusicListId(musicListInDb.getMusicListId());
                        crossRef.setMusicId(musicInDb.getMusicId());
                        Log.i(TAG, "music list find success and title is : " + musicListInDb.getTitle());
                        Log.i(TAG, "music find success and name is : " + musicInDb.getName());
                        mCrossRefDao.saveOne(crossRef).subscribe(aLong3 -> {
                            Log.i(TAG, "cross reference find success and id is :" + aLong3);
                            mCrossRefDao.findAllMusicInOneMusicList(aLong.intValue())
                                    .subscribe(listAndMusicCrossRefs -> {
                                        Assert.assertEquals(1, listAndMusicCrossRefs.size());
                                        Log.i(TAG, "get all success, and list size is :"
                                                + listAndMusicCrossRefs.size());
                                    }, Throwable::printStackTrace).dispose();
                        }).dispose();
                    }).dispose();
                }).dispose();
            }).dispose();
        }).dispose();
    }

    /*@Test
    public void testSaveAll() {
        List<MusicListAndMusicCrossRef> crossRefs = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            MusicListAndMusicCrossRef crossRef = new MusicListAndMusicCrossRef();
            crossRef.setMusicListId(1);
            crossRef.setMusicListId(i + 1);
        }
        mCrossRefDao.saveAll(crossRefs).subscribe(list -> {
            Assert.assertEquals(10, list.size());
            Log.i(TAG, "insert success");
        }, Throwable::printStackTrace).dispose();
    }*/
}
