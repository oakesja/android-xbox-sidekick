package com.example.joakes.xbox_sidekick.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.joakes.xbox_sidekick.activities.AchievementHelpActivity;
import com.example.joakes.xbox_sidekick.R;
import com.example.joakes.xbox_sidekick.models.Achievement;
import com.example.joakes.xbox_sidekick.presenters.AchievementPresenter;
import com.example.joakes.xbox_sidekick.presenters.AchievementPresenter2;
import com.example.joakes.xbox_sidekick.requests.WebService;

import java.util.ArrayList;

/**
 * Created by joakes on 6/4/15.
 */
public class AchievementAdapter extends RecyclerView.Adapter<AchievementViewHolder> {
    private ArrayList<Achievement> achievements;
    private Context context;

    public AchievementAdapter(Context context) {
        this.context = context;
        achievements = new ArrayList<>();
    }

    @Override
    public AchievementViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.achievement, parent, false);
        return new AchievementViewHolder(v);
    }

    @Override
    public void onBindViewHolder(AchievementViewHolder holder, int position) {
        final Achievement achievement = getAchievementAt(position);
        WebService webService = new WebService(context);
        new AchievementPresenter(achievement, webService).present(holder);
        AchievementPresenter2 presenter = new AchievementPresenter2(achievement);
        holder.achievementUnlockedTextview.setText(presenter.unlockedTime());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, AchievementHelpActivity.class);
                intent.putExtra(AchievementHelpActivity.ACHIEVEMENT, achievement);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return achievements.size();
    }

    public void addAchievements(ArrayList<Achievement> achievements) {
        this.achievements.addAll(achievements);
        notifyDataSetChanged();
    }

    public Achievement getAchievementAt(int position) {
        return achievements.get(position);
    }
}
