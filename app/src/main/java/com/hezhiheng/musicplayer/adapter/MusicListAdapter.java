package com.hezhiheng.musicplayer.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hezhiheng.musicplayer.R;
import com.hezhiheng.musicplayer.adapter.viewholder.MusicListItemViewHolder;
import com.hezhiheng.musicplayer.db.entity.MusicList;

import java.util.List;

public class MusicListAdapter extends RecyclerView.Adapter<MusicListItemViewHolder> {
    public List<MusicList> mMusicLists;
    private OnItemClickListener mItemClickListener;

    public MusicListAdapter(List<MusicList> mMusicLists) {
        this.mMusicLists = mMusicLists;
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
            holder.mMusicListCount.setText(String.valueOf(233));
            holder.setClickListener(mItemClickListener);
        }
    }

    public void setMusicLists(List<MusicList> lists) {
        mMusicLists = lists;
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
