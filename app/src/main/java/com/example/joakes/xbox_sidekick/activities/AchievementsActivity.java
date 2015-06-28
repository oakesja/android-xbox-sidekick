package com.example.joakes.xbox_sidekick.activities;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.joakes.xbox_sidekick.R;
import com.example.joakes.xbox_sidekick.adapters.pager.AchievementPagerAdapter;
import com.example.joakes.xbox_sidekick.dagger.BaseApplication;
import com.example.joakes.xbox_sidekick.models.Game;
import com.example.joakes.xbox_sidekick.presenters.GameInfoPresenter;
import com.example.joakes.xbox_sidekick.requests.WebService;
import com.example.joakes.xbox_sidekick.views.CircularProgressBar;
import com.example.joakes.xbox_sidekick.views.ImageTextView;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class AchievementsActivity extends AppCompatActivity {
    @InjectView(R.id.view_pager)
    ViewPager viewPager;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.tabs)
    TabLayout tabs;
    @InjectView(R.id.game_name)
    TextView gameName;
    @InjectView(R.id.game_achievements)
    ImageTextView gameAchievements;
    @InjectView(R.id.game_score)
    ImageTextView gameScore;
    @InjectView(R.id.gamerscore_progress)
    CircularProgressBar gamerscoreProgress;
    @Inject
    WebService webService;

    public static final String GAME = "com.example.joakes.xbox_sidekick.game";
    private final String REQUEST_TAG = getClass().getName();
    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupActivity();
        setupViewPager();
        setupToolbar();
        makeRequest();
        setupHeader();
    }

    private void setupActivity() {
        setContentView(R.layout.activity_achievements);
        ButterKnife.inject(this);
        ((BaseApplication) getApplication()).component().inject(this);
        game = getIntent().getParcelableExtra(GAME);
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        }
    }

    private void setupViewPager() {
        viewPager.setAdapter(new AchievementPagerAdapter(this, getSupportFragmentManager()));
        viewPager.setOffscreenPageLimit(viewPager.getAdapter().getCount());
        tabs.setupWithViewPager(viewPager);
    }

    private void makeRequest() {
        webService.getAchievementsFor(game, REQUEST_TAG);
    }

    private void setupHeader() {
        new GameInfoPresenter(game).present(gameName, gameAchievements,
                gameScore, gamerscoreProgress);
        CollapsingToolbarLayout cToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_tool_bar);
        cToolbar.setTitle(game.getName());
    }

    @Override
    protected void onStop() {
        super.onStop();
        webService.stop(REQUEST_TAG);
    }
}
