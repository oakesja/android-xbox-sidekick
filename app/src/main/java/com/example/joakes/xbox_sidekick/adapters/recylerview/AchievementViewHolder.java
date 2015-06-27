package com.example.joakes.xbox_sidekick.adapters.recylerview;

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
    @InjectView(R.id.achievement_icon)
    public ImageView achievementImageview;
    @InjectView(R.id.achievement_score)
    public ImageTextView achievementScoreImageTextview;
    @InjectView(R.id.achievement_name)
    public TextView achievementNameTextview;
    @InjectView(R.id.achievement_description)
    public TextView achievementDescriptionTextview;
    @InjectView(R.id.achievement_unlocked_time)
    public TextView achievementUnlockedTextview;
    @InjectView(R.id.achievement_lock)
    public ImageView lockImageView;

    public AchievementViewHolder(View itemView) {
        super(itemView);
        ButterKnife.inject(this, itemView);
    }
}
