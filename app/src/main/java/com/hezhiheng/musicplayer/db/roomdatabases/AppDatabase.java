package com.hezhiheng.musicplayer.db.roomdatabases;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.hezhiheng.musicplayer.db.dao.MusicDao;
import com.hezhiheng.musicplayer.db.dao.MusicListAndMusicCrossRefDao;
import com.hezhiheng.musicplayer.db.dao.MusicListDao;
import com.hezhiheng.musicplayer.db.entity.Music;
import com.hezhiheng.musicplayer.db.entity.MusicList;
import com.hezhiheng.musicplayer.db.entity.MusicListAndMusicCrossRef;

@Database(entities = {MusicList.class, Music.class, MusicListAndMusicCrossRef.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase instance;
    private static final String DATA_BASE_NAME = "music_list_db";

    public static AppDatabase getInstance(Context context) {
        synchronized (AppDatabase.class) {
            if (instance == null) {
                instance =
                        Room.databaseBuilder(context, AppDatabase.class, DATA_BASE_NAME).build();
            }
        }
        return instance;
    }

    public abstract MusicListDao getMusicListDao();

    public abstract MusicDao getMusicDao();

    public abstract MusicListAndMusicCrossRefDao getCrossRefDao();
}
