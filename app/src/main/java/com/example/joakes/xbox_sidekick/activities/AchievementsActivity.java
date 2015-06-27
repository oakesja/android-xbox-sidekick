package com.example.joakes.xbox_sidekick.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.joakes.xbox_sidekick.R;
import com.example.joakes.xbox_sidekick.dagger.BaseApplication;
import com.example.joakes.xbox_sidekick.models.Game;
import com.example.joakes.xbox_sidekick.requests.WebService;
//import com.github.florent37.materialviewpager.MaterialViewPager;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class AchievementsActivity extends AppCompatActivity {
//    @InjectView(R.id.achievement_view_pager)
//    MaterialViewPager achievementViewPager;
    @InjectView(R.id.game_name)
    TextView gameName;
//    @InjectView(R.id.game_achievements)
//    ImageTextView gameAchievements;
//    @InjectView(R.id.game_score)
//    ImageTextView gameScore;
//    @InjectView(R.id.gamerscore_progress)
//    CircularProgressBar gamerscoreProgress;
    @Inject
    WebService webService;
    public static final String GAME = "com.example.joakes.xbox_sidekick.game";
    private final String REQUEST_TAG = getClass().getName();
    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupActivity();
//        setupViewPager();
        makeRequest();
        setupHeader();
    }

    private void setupActivity() {
        setContentView(R.layout.activity_achievements);
        ButterKnife.inject(this);
        ((BaseApplication) getApplication()).component().inject(this);
        game = getIntent().getParcelableExtra(GAME);
        webService = new WebService(this);
    }

//    private void setupViewPager() {
//        achievementViewPager.getViewPager().setAdapter(new AchievementPagerAdapter(this));
//        achievementViewPager.getViewPager().setOffscreenPageLimit(achievementViewPager.getViewPager().getAdapter().getCount());
//        achievementViewPager.getPagerTitleStrip().setViewPager(achievementViewPager.getViewPager());
//    }

    private void makeRequest() {
        webService.getAchievementsFor(game, REQUEST_TAG);
    }

    private void setupHeader() {
//        new GameInfoPresenter(game).present(gameName, gameAchievements,
//                gameScore, gamerscoreProgress);
    }

    @Override
    protected void onStop() {
        super.onStop();
        webService.stop(REQUEST_TAG);
    }
}
