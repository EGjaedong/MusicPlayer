package com.hezhiheng.musicplayer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hezhiheng.musicplayer.R;
import com.hezhiheng.musicplayer.adapter.viewholder.MusicListItemViewHolder;
import com.hezhiheng.musicplayer.entity.MusicList;

import java.util.List;

public class MusicListAdapter extends RecyclerView.Adapter<MusicListItemViewHolder> {
    public List<MusicList> mMusicLists;
    public Context mContext;
    private OnItemClickListener mItemClickListener;

    public MusicListAdapter(List<MusicList> mMusicLists, Context mContext) {
        this.mMusicLists = mMusicLists;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public MusicListItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.music_list_item_layout, parent, false);
        return new MusicListItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MusicListItemViewHolder holder, int position) {
        MusicList musicList = mMusicLists.get(position);
        if (musicList != null) {
            holder.mMusicListTitle.setText(musicList.getTitle());
            holder.mMusicListCount.setText(String.valueOf(musicList.getCount()));
            holder.setClickListener(mItemClickListener);
        }
    }

    public void setItemClickListener(OnItemClickListener listener) {
        mItemClickListener = listener;
    }

    @Override
    public int getItemCount() {
        return mMusicLists.size();
    }

    public interface OnItemClickListener {
        void onItemClick(View v, int position);
    }
}
