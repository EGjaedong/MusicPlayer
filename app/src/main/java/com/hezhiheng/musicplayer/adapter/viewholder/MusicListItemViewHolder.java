package com.hezhiheng.musicplayer.adapter.viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hezhiheng.musicplayer.R;
import com.hezhiheng.musicplayer.adapter.MusicListAdapter;

public class MusicListItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView mMusicListTitle;
    public TextView mMusicListCount;
    private MusicListAdapter.OnItemClickListener mItemClickListener;

    public MusicListItemViewHolder(@NonNull View itemView) {
        super(itemView);
        mMusicListTitle = itemView.findViewById(R.id.music_list_title);
        mMusicListCount = itemView.findViewById(R.id.music_list_count);
        itemView.setOnClickListener(this);
    }

    public void setClickListener(MusicListAdapter.OnItemClickListener listener) {
        mItemClickListener = listener;
    }

    @Override
    public void onClick(View v) {
        if (mItemClickListener != null) {
            mItemClickListener.onItemClick(v, getAdapterPosition());
        }
    }
}
