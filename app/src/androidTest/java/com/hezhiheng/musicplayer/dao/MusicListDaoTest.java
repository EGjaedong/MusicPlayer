package com.hezhiheng.musicplayer.dao;

import android.util.Log;

import androidx.room.Room;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.hezhiheng.musicplayer.db.entity.MusicList;
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
public class MusicListDaoTest {
    private static final String TAG = MusicListDaoTest.class.getSimpleName();

    private AppDatabase mDatabase;

    @Before
    public void createDb() {
        mDatabase = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getInstrumentation().getContext(),
                AppDatabase.class)
                // 允许在主线程中运行，仅用于测试
                .allowMainThreadQueries()
                .build();
    }

    @After
    public void closeDb() {
        mDatabase.close();
    }

    @Test
    public void testSaveAll() {
        List<MusicList> musicLists = MusicList.createList();
        Maybe<List<Long>> insertResult = mDatabase.getMusicListDao().saveAll(musicLists);
        insertResult.subscribe(new MaybeObserver<List<Long>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onSuccess(@NonNull List<Long> list) {
                Assert.assertEquals("not equals", Long.valueOf(10), Long.valueOf(list.size()));
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
