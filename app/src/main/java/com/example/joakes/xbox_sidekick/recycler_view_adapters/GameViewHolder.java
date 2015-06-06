package com.example.joakes.xbox_sidekick.recycler_view_adapters;

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
public class GameViewHolder extends RecyclerView.ViewHolder {
    @InjectView(R.id.game_name_textview)
    TextView gameNameTextView;
    @InjectView(R.id.game_achievements_image_textview)
    ImageTextView achievementsImageTextView;
    @InjectView(R.id.game_score_image_textview)
    ImageTextView scoreImageTextView;
    @InjectView(R.id.game_imageview)
    ImageView gameImageView;

    public GameViewHolder(View view) {
        super(view);
        ButterKnife.inject(this, view);
    }
}
