package com.hezhiheng.musicplayer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hezhiheng.musicplayer.R;
import com.hezhiheng.musicplayer.adapter.viewholder.MusicItemViewHolder;
import com.hezhiheng.musicplayer.entity.Music;

import java.util.List;

public class MusicListContentAdapter extends RecyclerView.Adapter<MusicItemViewHolder> {
    private List<Music> mList;
    private Context mContext;

    public MusicListContentAdapter(List<Music> mList, Context context) {
        this.mList = mList;
        mContext = context;
    }

    @NonNull
    @Override
    public MusicItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.music_item_layout, parent, false);
        return new MusicItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MusicItemViewHolder holder, int position) {
        Music music = mList.get(position);
        if (music != null) {
            holder.mMusicItemNumber.setText(String.valueOf(position + 1));
            holder.mMusicItemName.setText(music.getName());
            holder.mMusicItemDesc.setText(String.format(mContext.getString(R.string.music_item_desc_template),
                    music.getSinger(), music.getAlbum()));
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
