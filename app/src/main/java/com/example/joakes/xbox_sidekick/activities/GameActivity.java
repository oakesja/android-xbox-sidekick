package com.example.joakes.xbox_sidekick.activities;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.example.joakes.xbox_sidekick.R;
import com.example.joakes.xbox_sidekick.adapters.pager.GamePagerAdapter;
import com.example.joakes.xbox_sidekick.dagger.BaseApplication;
import com.example.joakes.xbox_sidekick.models.Profile;
import com.example.joakes.xbox_sidekick.requests.WebService;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import de.greenrobot.event.EventBus;

public class GameActivity extends AppCompatActivity {
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

    private final String REQUEST_TAG = getClass().getName();
    private EventBus eventBus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupActivity();
        makeRequest();
        setupToolbar();
        setupViewPager();
    }

    private void setupActivity() {
        setContentView(R.layout.activity_game);
        ButterKnife.inject(this);
        ((BaseApplication) getApplication()).component().inject(this);
        eventBus = EventBus.getDefault();
    }

    private void makeRequest() {
        webService.getProfile(REQUEST_TAG);
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
    }

    private void setupViewPager() {
        viewPager.setAdapter(new GamePagerAdapter(this, getSupportFragmentManager()));
        viewPager.setOffscreenPageLimit(viewPager.getAdapter().getCount());
        tabs.setupWithViewPager(viewPager);
    }

    @Override
    protected void onStart() {
        super.onStart();
        eventBus.register(this);
    }

    public void onEvent(Profile profile) {
        webService.loadImageFromUrl(headerImage, profile.getGamerPictureUrl());
        CollapsingToolbarLayout cToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_tool_bar);
        cToolbar.setTitle(profile.getGamertag());
//        profileGamerscore.setImageAndTextIfValid(profile.getGamerscore(), R.drawable.ic_gamerscore);
    }

    @Override
    protected void onStop() {
        super.onStop();
        eventBus.unregister(this);
        webService.stop(REQUEST_TAG);
    }
}