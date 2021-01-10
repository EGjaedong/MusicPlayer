package com.hezhiheng.musicplayer.viewmodel;

import android.util.Log;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.hezhiheng.musicplayer.MusicPlayerApplication;
import com.hezhiheng.musicplayer.R;
import com.hezhiheng.musicplayer.db.entity.MusicList;
import com.hezhiheng.musicplayer.repository.MusicListRepository;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MusicListViewModel extends ViewModel {
    private static final String TAG = MusicListViewModel.class.getSimpleName();

    private MusicListRepository mRepository;

    public MusicListViewModel(MusicListRepository repository) {
        mRepository = repository;
        init();
    }

    private void init() {
        initFavourite();
    }

    private void initFavourite() {
        MusicList myFavMusicList = new MusicList(MusicPlayerApplication.getInstance()
                .getApplicationContext().getString(R.string.my_favorite_list_title_text));
        mRepository.saveOne(myFavMusicList)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> Log.i(TAG, "save my favorite success and id is :" + aLong))
                .dispose();
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
