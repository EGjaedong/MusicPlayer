package com.hezhiheng.musicplayer.db.entity;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

public class MusicWithMusicLists {
    @Embedded
    public Music music;

    @Relation(
            parentColumn = "music_id",
            entityColumn = "music_list_id",
            associateBy = @Junction(MusicListAndMusicCrossRef.class)
    )
    public List<MusicList> musicLists;
}
