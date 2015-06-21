package com.example.joakes.xbox_sidekick.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.joakes.xbox_sidekick.activities.AchievementsActivity;
import com.example.joakes.xbox_sidekick.R;
import com.example.joakes.xbox_sidekick.models.XboxGame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by joakes on 5/11/15.
 */
public class XboxGameAdapter extends RecyclerView.Adapter<GameViewHolder> {
    private ArrayList<XboxGame> mGames;
    private Context mContext;

    public XboxGameAdapter(Context context) {
        mGames = new ArrayList<>();
        mContext = context;
    }

    @Override
    public GameViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.game, parent, false);
        return new GameViewHolder(v);
    }

    @Override
    public void onBindViewHolder(GameViewHolder holder, int position) {
        final XboxGame game = gameAt(position);
        holder.gameNameTextView.setText(game.getName());
        holder.achievementsImageTextView.setImageAndTextIfValid(game.getEarnedAchievements(), game.getTotalAchivements(), R.drawable.ic_trophy);
        holder.scoreImageTextView.setImageAndTextIfValid(game.getEarnedGamerscore(), game.getTotalGamerscore(), R.drawable.ic_gamerscore);
        // TODO find better way of handling on click listener, consider gesture detector
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, AchievementsActivity.class);
                intent.putExtra(AchievementsActivity.GAME, game);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mGames.size();
    }

    public void addGames(ArrayList<XboxGame> games) {
        mGames.addAll(games);
        // TODO extract to game manager that takes care of filtering and sorting and add tests
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
        notifyDataSetChanged();
    }

    public XboxGame gameAt(int position) {
        return mGames.get(position);
    }
}
