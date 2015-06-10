package com.example.joakes.xbox_sidekick.presenters;

import com.example.joakes.xbox_sidekick.R;
import com.example.joakes.xbox_sidekick.adapters.AchievementViewHolder;
import com.example.joakes.xbox_sidekick.models.Achievement;
import com.example.joakes.xbox_sidekick.requests.utils.WebService;

/**
 * Created by joakes on 6/9/15.
 */
public class AchievementPresenter {
    private Achievement mAchievement;

    public AchievementPresenter(Achievement achievement) {
        mAchievement = achievement;
    }

    public void present(AchievementViewHolder holder) {
        holder.achievementNameTextview.setText(mAchievement.getName());
        holder.achievementDescriptionTextview.setText(mAchievement.getDescription());
        holder.achievementScoreImageTextview.setImageAndTextIfValid(mAchievement.getValue(), R.drawable.ic_gamerscore);
        getImageForAchievement(holder);
    }

    private void getImageForAchievement(AchievementViewHolder holder) {
        WebService webService = new WebService(holder.achievementImageview.getContext());
        webService.loadImageFromUrl(holder.achievementImageview, mAchievement.getIconUrl());
    }
}
