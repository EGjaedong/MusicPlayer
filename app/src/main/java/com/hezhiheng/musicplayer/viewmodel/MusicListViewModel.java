package com.hezhiheng.musicplayer.viewmodel;

import android.util.Log;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.hezhiheng.musicplayer.db.entity.MusicList;
import com.hezhiheng.musicplayer.repository.MusicListRepository;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.MaybeObserver;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MusicListViewModel extends ViewModel {
    private static final String TAG = MusicListViewModel.class.getSimpleName();

    private MusicListRepository mRepository;

    public MusicListViewModel(MusicListRepository repository) {
        mRepository = repository;
        init();
    }

    private void init() {
        saveAll(MusicList.createList());
    }

    public void saveAll(List<MusicList> lists) {
        mRepository.saveAll(lists).subscribeOn(Schedulers.io()).subscribe(new MaybeObserver<List<Long>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onSuccess(@NonNull List<Long> list) {
                Log.i(TAG, "insert success");
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.e(TAG, "insert error");
            }

            @Override
            public void onComplete() {

            }
        });
    }

    public Flowable<List<MusicList>> getAllMusicList() {
        return mRepository.getAllMusicLists();
    }

    public static class Factory implements ViewModelProvider.Factory {
        private final MusicListRepository mRepository;

        public Factory() {
            this.mRepository = new MusicListRepository();
        }

        @androidx.annotation.NonNull
        @Override
        @SuppressWarnings("unchecked")
        public <T extends ViewModel> T create(@androidx.annotation.NonNull Class<T> modelClass) {
            if (modelClass.isAssignableFrom(MusicListViewModel.class)) {
                return (T) new MusicListViewModel(mRepository);
            }
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }

}
