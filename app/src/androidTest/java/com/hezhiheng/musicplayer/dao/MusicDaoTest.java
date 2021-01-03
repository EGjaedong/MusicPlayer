package com.hezhiheng.musicplayer.dao;

import android.util.Log;

import androidx.room.Room;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.hezhiheng.musicplayer.db.entity.Music;
import com.hezhiheng.musicplayer.db.roomdatabases.AppDatabase;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.MaybeObserver;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

@RunWith(AndroidJUnit4.class)
public class MusicDaoTest {
    private static final String TAG = MusicDaoTest.class.getSimpleName();

    private AppDatabase mDatabase;

    @Before
    public void init() {
        mDatabase = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getInstrumentation().getContext(),
                AppDatabase.class)
                // 允许在主线程中运行，仅用于测试
                .allowMainThreadQueries()
                .build();
    }

    @After
    public void close() {
        mDatabase.close();
    }

    @Test
    public void testSaveAll() {
        List<Music> musics = Music.createList();
        Maybe<List<Long>> insertResult = mDatabase.getMusicDao().saveAll(musics);
        insertResult.subscribe(new MaybeObserver<List<Long>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onSuccess(@NonNull List<Long> list) {
                Assert.assertEquals(Long.valueOf(3), Long.valueOf(list.size()));
                Log.i(TAG, "insert success. Insert number is :" + list.size());
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
}
