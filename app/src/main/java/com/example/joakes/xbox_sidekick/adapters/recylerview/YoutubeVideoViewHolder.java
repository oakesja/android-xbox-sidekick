package com.example.joakes.xbox_sidekick.adapters.recylerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.joakes.xbox_sidekick.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

// TODO place with adapter
public class YoutubeVideoViewHolder extends RecyclerView.ViewHolder {
    @InjectView(R.id.youtube_icon)
    ImageView youtubeIcon;
    @InjectView(R.id.youtube_title)
    TextView youtubeTitle;
    @InjectView(R.id.youtube_author)
    TextView youtubeAuthor;

    public YoutubeVideoViewHolder(View view) {
        super(view);
        ButterKnife.inject(this, view);
    }
}
