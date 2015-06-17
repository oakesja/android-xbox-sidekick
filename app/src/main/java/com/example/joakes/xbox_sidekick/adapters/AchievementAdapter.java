package com.example.joakes.xbox_sidekick.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.joakes.xbox_sidekick.AchievementHelpActivity;
import com.example.joakes.xbox_sidekick.R;
import com.example.joakes.xbox_sidekick.models.Achievement;
import com.example.joakes.xbox_sidekick.presenters.AchievementPresenter;
import com.example.joakes.xbox_sidekick.requests.utils.WebService;

import java.util.ArrayList;

/**
 * Created by joakes on 6/4/15.
 */
public class AchievementAdapter extends RecyclerView.Adapter<AchievementViewHolder> {
    private ArrayList<Achievement> mAchievements;
    private Context mContext;

    public AchievementAdapter(Context context) {
        mContext = context;
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
        final Achievement achievement = getAchievementAt(position);
        WebService webService = new WebService(mContext);
        new AchievementPresenter(achievement, webService).present(holder);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, AchievementHelpActivity.class);
                intent.putExtra(AchievementHelpActivity.ACHIEVEMENT, achievement);
                mContext.startActivity(intent);
            }
        });
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
