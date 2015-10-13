package com.example.joakes.xbox_sidekick.adapters.recylerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.joakes.xbox_sidekick.R;
import com.example.joakes.xbox_sidekick.custom_views.ImageTextView;

import butterknife.ButterKnife;
import butterknife.InjectView;

// TODO place with adapter
public class GameViewHolder extends RecyclerView.ViewHolder {
    @InjectView(R.id.game_name)
    TextView gameNameTextView;
    @InjectView(R.id.game_achievements)
    ImageTextView achievementsImageTextView;
    @InjectView(R.id.game_score)
    ImageTextView scoreImageTextView;
    @InjectView(R.id.game_imageview)
    ImageView gameImageView;

    public GameViewHolder(View view) {
        super(view);
        ButterKnife.inject(this, view);
    }
}
