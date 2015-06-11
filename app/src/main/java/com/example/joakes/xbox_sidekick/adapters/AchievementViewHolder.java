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
public class AchievementViewHolder extends RecyclerView.ViewHolder {
    @InjectView(R.id.achievement_imageview)
    public ImageView achievementImageview;
    @InjectView(R.id.achievement_score_image_textview)
    public ImageTextView achievementScoreImageTextview;
    @InjectView(R.id.achievement_name_textview)
    public TextView achievementNameTextview;
    @InjectView(R.id.achievement_description_textview)
    public TextView achievementDescriptionTextview;
    @InjectView(R.id.achievement_unlocked_textview)
    public TextView achievementUnlockedTextview;
    @InjectView(R.id.achievement_lock_imageview)
    public ImageView lockImageView;

    public AchievementViewHolder(View itemView) {
        super(itemView);
        ButterKnife.inject(this, itemView);
    }
}
