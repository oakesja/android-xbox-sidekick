package com.example.joakes.xbox_sidekick;

import android.view.View;
import android.widget.TextView;

import com.example.joakes.xbox_sidekick.models.XboxGame;
import com.example.joakes.xbox_sidekick.views.CircularProgressBar;
import com.example.joakes.xbox_sidekick.views.ImageTextView;

/**
 * Created by joakes on 6/9/15.
 */
public class GameInfoPresenter {
    private XboxGame mGame;

    public GameInfoPresenter(XboxGame game){
        mGame = game;
    }

    public void presentGameInfo(TextView gameNameTextView, ImageTextView gameAchievementsImageTextview,
                                       ImageTextView gamerscoreImageTextview, CircularProgressBar gamerscoreProgressBar) {
        gameNameTextView.setText(mGame.getName());
        gameAchievementsImageTextview.setImageAndTextIfValid(mGame.getEarnedAchievements(), mGame.getTotalAchivements(), R.drawable.ic_trophy);
        gamerscoreImageTextview.setImageAndTextIfValid(mGame.getEarnedGamerscore(), mGame.getTotalGamerscore(), R.drawable.ic_gamerscore);

        if(mGame.getEarnedGamerscore() < 0 || mGame.getTotalGamerscore() < 0){
            gamerscoreProgressBar.setVisibility(View.GONE);
        } else {
            int progress = (int)(100f * mGame.getEarnedGamerscore() / mGame.getTotalGamerscore());
            gamerscoreProgressBar.setProgress(progress);
        }
    }
}
