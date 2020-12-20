package com.hezhiheng.musicplayer.adapter.viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hezhiheng.musicplayer.R;

public class MusicItemViewHolder extends RecyclerView.ViewHolder {
    public TextView mMusicItemNumber;
    public TextView mMusicItemName;
    public TextView mMusicItemDesc;

    public MusicItemViewHolder(@NonNull View itemView) {
        super(itemView);
        mMusicItemNumber = itemView.findViewById(R.id.music_item_number_text);
        mMusicItemName = itemView.findViewById(R.id.music_item_name_text);
        mMusicItemDesc = itemView.findViewById(R.id.music_item_desc_text);
    }
}
