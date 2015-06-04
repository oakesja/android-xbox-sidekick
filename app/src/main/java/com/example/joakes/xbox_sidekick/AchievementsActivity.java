package com.example.joakes.xbox_sidekick;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.joakes.xbox_sidekick.models.XboxGame;
import com.example.joakes.xbox_sidekick.views.ImageTextView;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class AchievementsActivity extends AppCompatActivity {
    public static final String GAME = "com.example.joakes.xbox_sidekick.game";

    @InjectView(R.id.game_name_textview)
    TextView gameNameTextView;
    @InjectView(R.id.game_achievements_image_textview)
    ImageTextView gameAchievementsImageTextview;
    @InjectView(R.id.gamerscore_image_textview)
    ImageTextView gamerscoreImageTextview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achievements);
        ButterKnife.inject(this);
        
        XboxGame game = getIntent().getParcelableExtra(GAME);
        gameNameTextView.setText(game.getName());
        gameAchievementsImageTextview.setImageAndTextIfValid(game.getEarnedAchievements(), game.getTotalAchivements(), R.drawable.ic_trophy);
        gamerscoreImageTextview.setImageAndTextIfValid(game.getEarnedGamerscore(), game.getTotalGamerscore(), R.drawable.ic_gamerscore);

    }
}
