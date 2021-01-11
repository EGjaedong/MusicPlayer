package com.hezhiheng.musicplayer.adapter.viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hezhiheng.musicplayer.R;
import com.hezhiheng.musicplayer.adapter.MusicListContentAdapter;

public class MusicItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView mMusicItemNumber;
    public TextView mMusicItemName;
    public TextView mMusicItemDesc;
    private MusicListContentAdapter.OnItemClickListener mItemClickListener;

    public MusicItemViewHolder(@NonNull View itemView) {
        super(itemView);
        mMusicItemNumber = itemView.findViewById(R.id.music_item_number_text);
        mMusicItemName = itemView.findViewById(R.id.music_item_name_text);
        mMusicItemDesc = itemView.findViewById(R.id.music_item_desc_text);
    }

    public void setClickListener(MusicListContentAdapter.OnItemClickListener listener) {
        mItemClickListener = listener;
    }


    @Override
    public void onClick(View v) {
        if (mItemClickListener != null) {
            mItemClickListener.onItemClick(v, getAdapterPosition());
        }
    }
}
