package com.example.joakes.xbox_sidekick.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.joakes.xbox_sidekick.R;
import com.example.joakes.xbox_sidekick.models.Achievement;
import com.example.joakes.xbox_sidekick.presenters.AchievementPresenter;

import java.util.ArrayList;

/**
 * Created by joakes on 6/4/15.
 */
public class AchievementAdapter extends RecyclerView.Adapter<AchievementViewHolder> {
    private ArrayList<Achievement> mAchievements;

    public AchievementAdapter() {
        mAchievements = new ArrayList<>();
    }

    @Override
    public AchievementViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.achievement_card, parent, false);
        return new AchievementViewHolder(v);
    }

    @Override
    public void onBindViewHolder(AchievementViewHolder holder, int position) {
        Achievement achievement = getAchievementAt(position);
        new AchievementPresenter(achievement).present(holder);
    }

    @Override
    public int getItemCount() {
        return mAchievements.size();
    }

    public void addAchievements(ArrayList<Achievement> achievements) {
        mAchievements.addAll(achievements);
        notifyDataSetChanged();
    }

    public Achievement getAchievementAt(int position) {
        return mAchievements.get(position);
    }
}
