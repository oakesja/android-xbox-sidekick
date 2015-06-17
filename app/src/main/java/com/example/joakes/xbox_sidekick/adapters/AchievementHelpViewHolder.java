package com.example.joakes.xbox_sidekick.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.joakes.xbox_sidekick.R;
import com.example.joakes.xbox_sidekick.views.ImageTextView;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by joakes on 6/4/15.
 */
public class AchievementHelpViewHolder extends RecyclerView.ViewHolder {
    @InjectView(R.id.youtube_icon)
    ImageView youtubeIcon;
    @InjectView(R.id.youtube_title)
    TextView youtubeTitle;
    @InjectView(R.id.youtube_author)
    TextView youtubeAuthor;

    public AchievementHelpViewHolder(View view) {
        super(view);
        ButterKnife.inject(this, view);
    }
}
