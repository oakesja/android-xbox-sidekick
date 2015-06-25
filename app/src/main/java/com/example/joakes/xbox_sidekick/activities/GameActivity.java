package com.example.joakes.xbox_sidekick.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.joakes.xbox_sidekick.R;
import com.example.joakes.xbox_sidekick.adapters.pager.GamePagerAdapter;
import com.example.joakes.xbox_sidekick.dagger.BaseApplication;
import com.example.joakes.xbox_sidekick.models.Profile;
import com.example.joakes.xbox_sidekick.requests.WebService;
import com.example.joakes.xbox_sidekick.views.ImageTextView;
import com.github.florent37.materialviewpager.MaterialViewPager;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import de.greenrobot.event.EventBus;

public class GameActivity extends AppCompatActivity {
    @InjectView(R.id.game_view_pager)
    MaterialViewPager gameViewPager;
    @InjectView(R.id.profile_gamerscore)
    ImageTextView profileGamerscore;
    @InjectView(R.id.profile_name)
    TextView profileName;
    @InjectView(R.id.gamer_picture)
    ImageView profilePicture;
    @Inject
    WebService webService;

    private final String REQUEST_TAG = getClass().getName();
    private EventBus eventBus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupActivity();
        setupViewPager();
        makeRequest();
    }

    private void setupActivity() {
        setContentView(R.layout.activity_game);
        ButterKnife.inject(this);
        ((BaseApplication)getApplication()).component().inject(this);
        eventBus = EventBus.getDefault();
    }

    private void setupViewPager() {
        gameViewPager.getViewPager().setAdapter(new GamePagerAdapter(this));
        gameViewPager.getViewPager().setOffscreenPageLimit(gameViewPager.getViewPager().getAdapter().getCount());
        gameViewPager.getPagerTitleStrip().setViewPager(gameViewPager.getViewPager());
    }

    private void makeRequest() {
        webService.getProfile(REQUEST_TAG);
    }

    public void onEvent(Profile profile) {
        webService.loadImageFromUrl(profilePicture, profile.getGamerPictureUrl());
        profileName.setText(profile.getGamertag());
        profileGamerscore.setImageAndTextIfValid(profile.getGamerscore(), R.drawable.ic_gamerscore);
    }

    @Override
    protected void onStart() {
        super.onStart();
        eventBus.register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        eventBus.unregister(this);
        webService.stop(REQUEST_TAG);
    }

}