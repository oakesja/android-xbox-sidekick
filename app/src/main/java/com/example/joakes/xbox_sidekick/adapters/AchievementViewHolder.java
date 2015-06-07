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
    ImageView achievementImageview;
    @InjectView(R.id.achievement_score_image_textview)
    ImageTextView achievementScoreImageTextview;
    @InjectView(R.id.achievement_name_textview)
    TextView achievementNameTextview;
    @InjectView(R.id.achievement_description_textview)
    TextView achievementDescriptionTextview;
    @InjectView(R.id.achievement_unlocked_textview)
    TextView achievementUnlockedTextview;

    public AchievementViewHolder(View itemView) {
        super(itemView);
        ButterKnife.inject(this, itemView);
    }
}
