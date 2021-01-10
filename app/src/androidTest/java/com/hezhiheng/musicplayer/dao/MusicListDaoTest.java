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
import com.hezhiheng.musicplayer.db.entity.MusicListWithMusic;
import com.hezhiheng.musicplayer.db.roomdatabases.AppDatabase;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.reactivestreams.Subscription;

import java.util.List;

import io.reactivex.BackpressureStrategy;
import io.reactivex.FlowableSubscriber;
import io.reactivex.Maybe;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

@RunWith(AndroidJUnit4.class)
public class MusicListDaoTest {
    private static final String TAG = MusicListDaoTest.class.getSimpleName();

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
    public void closeDb() {
        mDatabase.close();
    }

    @Test
    public void testSaveAll() {
        List<MusicList> musicLists = MusicList.createList();
        Maybe<List<Long>> insertResult = mMusicListDao.saveAll(musicLists);
        insertResult.subscribe(list -> {
            Assert.assertEquals(Long.valueOf(10), Long.valueOf(list.size()));
            Log.i(TAG, "insert success. Insert number is :" + list.size());
        }).dispose();
    }

    @Test
    public void testSaveOne() {
        MusicList musicList = new MusicList();
        musicList.setTitle("我喜欢的音乐");
        Maybe<Long> saveResult = mMusicListDao.saveOne(musicList);
        saveResult.subscribe(aLong -> {
            Assert.assertEquals(Long.valueOf(1), aLong);
            Log.i(TAG, "insert success. Music list id is :" + aLong);
        }).dispose();
    }

    @Test
    public void testFindOneByName() {
        MusicList musicList = new MusicList();
        String title = "我喜欢的音乐";
        musicList.setTitle(title);
        Maybe<Long> saveResult = mMusicListDao.saveOne(musicList);
        saveResult.subscribe(aLong -> {
            mMusicListDao.findOneByTitle(title).subscribe(musicList1 -> {
                Assert.assertEquals(title, musicList1.getTitle());
                Log.i(TAG, "find one success. title is :" + musicList1.getTitle());
            }).dispose();
        }).dispose();
    }

    @Test
    public void testFindAll() {
        List<MusicList> musicLists = MusicList.createList();
        Maybe<List<Long>> insertResult = mMusicListDao.saveAll(musicLists);
        insertResult.subscribe(list -> {
            mMusicListDao.getAllList().subscribe(new FlowableSubscriber<List<MusicList>>() {
                @Override
                public void onSubscribe(@NonNull Subscription s) {
                    s.request(1);
                }

                @Override
                public void onNext(List<MusicList> lists) {
                    Assert.assertEquals(10, list.size());
                    Log.i(TAG, "get all success, and size is :" + list.size());
                }

                @Override
                public void onError(Throwable t) {

                }

                @Override
                public void onComplete() {

                }
            });
        }).dispose();
    }

    @Test
    public void testFindMusicListWithMusic() {
        MusicList musicList = new MusicList();
        mMusicListDao.saveOne(musicList).subscribe(aLong -> {
            Log.i(TAG, "music list insert success and id is :" + aLong);
            List<Music> musics = Music.createList();
            mMusicDao.saveAll(musics).subscribe(list -> {
                Assert.assertEquals(3, list.size());
                Log.i(TAG, "music insert success and size is :" + list.size());
                mMusicListDao.findAllMusicListWithMusic().subscribe(new FlowableSubscriber<List<MusicListWithMusic>>() {
                    @Override
                    public void onSubscribe(@NonNull Subscription s) {
                        s.request(1);
                    }

                    @Override
                    public void onNext(List<MusicListWithMusic> musicListWithMusics) {
                        Assert.assertEquals(1, musicListWithMusics.size());
                        Assert.assertEquals(3, musicListWithMusics.get(0).musics.size());
                        MusicListWithMusic musicListWithMusic = musicListWithMusics.get(0);
                        Log.i(TAG, "music list is: " + musicListWithMusic.musicList.getTitle()
                                + " and musics are :" + musicListWithMusic.musics);
                    }

                    @Override
                    public void onError(Throwable t) {
                        t.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
            }).dispose();
        }).dispose();
    }
}
