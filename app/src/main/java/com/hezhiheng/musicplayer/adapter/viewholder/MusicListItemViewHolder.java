package com.hezhiheng.musicplayer.adapter.viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hezhiheng.musicplayer.R;

public class MusicListItemViewHolder extends RecyclerView.ViewHolder {
    public TextView mMusicListTitle;
    public TextView mMusicListCount;

    public MusicListItemViewHolder(@NonNull View itemView) {
        super(itemView);
        mMusicListTitle = itemView.findViewById(R.id.music_list_title);
        mMusicListCount = itemView.findViewById(R.id.music_list_count);
    }
}
