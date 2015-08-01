package com.example.joakes.xbox_sidekick.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.joakes.xbox_sidekick.R;
import com.example.joakes.xbox_sidekick.adapters.pager.AchievementHelpPagerAdapter;
import com.example.joakes.xbox_sidekick.adapters.recylerview.YoutubeVideoAdapter;
import com.example.joakes.xbox_sidekick.dagger.BaseApplication;
import com.example.joakes.xbox_sidekick.models.Achievement;
import com.example.joakes.xbox_sidekick.presenters.AchievementPresenter2;
import com.example.joakes.xbox_sidekick.requests.WebService;
import com.example.joakes.xbox_sidekick.views.ImageTextView;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import de.greenrobot.event.EventBus;

public class AchievementHelpActivity extends AppCompatActivity {
    @InjectView(R.id.view_pager)
    ViewPager viewPager;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.tabs)
    TabLayout tabs;
    @InjectView(R.id.header_image)
    ImageView headerImage;
    @Inject
    WebService webService;

    public static final String ACHIEVEMENT = "com.example.joakes.xbox_sidekick.achievement";
    private Achievement achievement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupActivity();
        setupViewPager();
        setupToolbar();
        setHeaderViews();
    }

    private void setupActivity() {
        setContentView(R.layout.activity_achievement_help);
        ButterKnife.inject(this);
        ((BaseApplication) getApplication()).component().inject(this);
        achievement = getIntent().getParcelableExtra(ACHIEVEMENT);
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
        viewPager.setAdapter(new AchievementHelpPagerAdapter(achievement, this, getSupportFragmentManager()));
        viewPager.setOffscreenPageLimit(viewPager.getAdapter().getCount());
        tabs.setupWithViewPager(viewPager);
    }

    private void setHeaderViews() {
        AchievementPresenter2 presenter = new AchievementPresenter2(achievement);
        ImageTextView achievementValue = (ImageTextView) findViewById(R.id.achievement_value);
        TextView achievementName = (TextView) findViewById(R.id.achievement_name);
        TextView achievementDescription = (TextView) findViewById(R.id.achievement_description);
        TextView achievementTime = (TextView) findViewById(R.id.achievement_unlocked);
        achievementValue.setImageAndTextIfValid(achievement.getValue(), R.drawable.ic_gamerscore);
        achievementName.setText(achievement.getName());
        achievementDescription.setText(presenter.descriptionIgnoreSecret());
        achievementTime.setText(presenter.unlockedTime());
        webService.loadImageFromUrl(headerImage, achievement.getIconUrl());
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
