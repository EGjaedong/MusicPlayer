package com.hezhiheng.musicplayer.db.roomdatabases;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.hezhiheng.musicplayer.db.dao.MusicListDao;
import com.hezhiheng.musicplayer.db.entity.Music;
import com.hezhiheng.musicplayer.db.entity.MusicList;

@Database(entities = {MusicList.class, Music.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase instance;
    private static final String DATA_BASE_NAME = "music_list_db";

    public static AppDatabase getInstance(Context context) {
        synchronized (AppDatabase.class) {
            if (instance == null) {
                instance =
                        Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, DATA_BASE_NAME).build();
            }
        }
        return instance;
    }

    public abstract MusicListDao getMusicListDao();
}
