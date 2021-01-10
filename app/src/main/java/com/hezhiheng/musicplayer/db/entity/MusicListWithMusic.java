package com.hezhiheng.musicplayer.db.entity;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

public class MusicListWithMusic {
    @Embedded
    public MusicList musicList;

    @Relation(
            parentColumn = "music_list_id",
            entityColumn = "music_id",
            associateBy = @Junction(MusicListAndMusicCrossRef.class)
    )
    public List<Music> musics;
}
