package com.example.joakes.xbox_sidekick;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.joakes.xbox_sidekick.models.XboxGame;
import com.example.joakes.xbox_sidekick.views.ImageTextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by joakes on 5/11/15.
 */
public class XboxGameAdapter extends RecyclerView.Adapter<XboxGameAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<XboxGame> mGames;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.game_name_textview)
        TextView gameNameTextView;
        @InjectView(R.id.game_achievements_image_textview)
        ImageTextView achievementsImageTextView;
        @InjectView(R.id.game_score_image_textview)
        ImageTextView scoreImageTextView;
        @InjectView(R.id.game_imageview)
        ImageView gameImageView;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.inject(this, view);
        }
    }

    public XboxGameAdapter(Context context) {
        mContext = context;
        mGames = new ArrayList<>();
    }

    @Override
    public XboxGameAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.game_card, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        XboxGame game = gameAt(position);
        holder.gameNameTextView.setText(game.getName());
        holder.achievementsImageTextView.setImageAndTextIfValid(game.getEarnedAchievements(), game.getTotalAchivements(), R.drawable.ic_trophy);
        holder.scoreImageTextView.setImageAndTextIfValid(game.getEarnedGamerscore(), game.getTotalGamerscore(), R.drawable.ic_gamerscore);
    }

    @Override
    public int getItemCount() {
        return mGames.size();
    }

    public void addGames(ArrayList<XboxGame> games) {
        mGames.addAll(games);
        Collections.sort(mGames, new Comparator<XboxGame>() {
            @Override
            public int compare(XboxGame game1, XboxGame game2) {
                if (game1.getType() == game2.getType()) {
                    return 0;
                } else if (game1.getType() < game2.getType()) {
                    return 1;
                }
                return -1;
            }
        });
        this.notifyDataSetChanged();
    }

    public XboxGame gameAt(int position) {
        return mGames.get(position);
    }
}
