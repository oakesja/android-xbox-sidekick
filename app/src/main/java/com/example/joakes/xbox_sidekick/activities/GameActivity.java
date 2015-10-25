package com.example.joakes.xbox_sidekick.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.example.joakes.xbox_sidekick.R;
import com.example.joakes.xbox_sidekick.XboxApiService;
import com.example.joakes.xbox_sidekick.adapters.pager.GamePagerAdapter;
import com.example.joakes.xbox_sidekick.dagger.BaseApplication;
import com.example.joakes.xbox_sidekick.presenters.ProfilePresenter;
import com.example.joakes.xbox_sidekick.views.ProfileView;
import com.google.common.base.Function;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class GameActivity extends AppCompatActivity implements ProfileView {

    @InjectView(R.id.view_pager)
    ViewPager viewPager;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.collapsing_tool_bar)
    public CollapsingToolbarLayout cToolbar;
    @InjectView(R.id.tabs)
    TabLayout tabs;
    @InjectView(R.id.header_image)
    ImageView headerImage;
    @Inject
    XboxApiService xboxApiService;

    private ProfilePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupActivity();
        setupToolbar();
        setupViewPager();
    }

    private void setupActivity() {
        setContentView(R.layout.activity_game);
        ButterKnife.inject(this);
        ((BaseApplication) getApplication()).component().inject(this);
        presenter = new ProfilePresenter();
        presenter.attachToView(this);
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
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachFromView();
    }

    @Override
    public void showGamertag(String gamertag) {
        cToolbar.setTitle(gamertag);
    }

    @Override
    public void showGamerscore(String gamerscore) {

    }

    @Override
    public void showGamerPicture(Function<ImageView, Void> setGamerPicture) {
        setGamerPicture.apply(headerImage);
    }

    @Override
    public void handleError() {

    }

    @Override
    public XboxApiService getXboxApiService() {
        return xboxApiService;
    }

    @Override
    public Context getContext() {
        return getApplicationContext();
    }
}