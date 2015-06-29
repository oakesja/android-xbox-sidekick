package com.example.joakes.xbox_sidekick.presenters;

import android.content.Context;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.view.View;
import android.widget.ImageView;

import com.example.joakes.xbox_sidekick.R;
import com.example.joakes.xbox_sidekick.adapters.recylerview.AchievementViewHolder;
import com.example.joakes.xbox_sidekick.models.Achievement;
import com.example.joakes.xbox_sidekick.requests.WebService;

/**
 * Created by joakes on 6/9/15.
 */
// TODO move all of this to AchievementPresenter2 minus the views
public class AchievementPresenter {
    private Achievement mAchievement;
    private WebService mWebService;

    public AchievementPresenter(Achievement achievement, WebService webService) {
        mAchievement = achievement;
        mWebService = webService;
    }

    public void present(AchievementViewHolder holder) {
        Context context = holder.achievementImageview.getContext();
        holder.achievementScoreImageTextview.setImageAndTextIfValid(mAchievement.getValue(), R.drawable.ic_gamerscore);
        holder.lockImageView.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_lock));
        handleAchievementState(holder, context);
    }

    private void handleAchievementState(AchievementViewHolder holder, Context context) {
        if (!mAchievement.isLocked()) {
            handleUnlocked(holder);
        } else if (mAchievement.isSecret()) {
            handleLockedAndSecret(holder, context);
        } else {
            handleLocked(holder);
        }
    }

    private void handleUnlocked(AchievementViewHolder holder) {
        holder.achievementNameTextview.setText(mAchievement.getName());
        holder.achievementDescriptionTextview.setText(mAchievement.getDescription());
        holder.lockImageView.setVisibility(View.INVISIBLE);
        holder.achievementImageview.setColorFilter(null);
        getImageForAchievement(holder.achievementImageview);
    }

    private void handleLockedAndSecret(AchievementViewHolder holder, Context context) {
        holder.achievementNameTextview.setText(context.getString(R.string.secret_achievement));
        holder.achievementDescriptionTextview.setText(context.getString(R.string.secret_achievement_description));
        holder.lockImageView.setVisibility(View.VISIBLE);
        holder.achievementImageview.setColorFilter(grayScaleFilter());
        holder.achievementImageview.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_lock));
    }

    private void handleLocked(AchievementViewHolder holder) {
        holder.achievementNameTextview.setText(mAchievement.getName());
        holder.achievementDescriptionTextview.setText(mAchievement.getLockedDescription());
        holder.lockImageView.setVisibility(View.VISIBLE);
        holder.achievementImageview.setColorFilter(grayScaleFilter());
        getImageForAchievement(holder.achievementImageview);
    }

    private void getImageForAchievement(ImageView imageView) {
        mWebService.loadImageFromUrl(imageView, mAchievement.getIconUrl());
    }

    private ColorMatrixColorFilter grayScaleFilter() {
        ColorMatrix matrix = new ColorMatrix();
        matrix.setSaturation(0);
        return new ColorMatrixColorFilter(matrix);
    }
}
