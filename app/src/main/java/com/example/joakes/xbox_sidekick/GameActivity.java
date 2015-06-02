package com.example.joakes.xbox_sidekick;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bartoszlipinski.recyclerviewheader.RecyclerViewHeader;
import com.example.joakes.xbox_sidekick.models.XboxGame;
import com.example.joakes.xbox_sidekick.models.XboxProfile;
import com.example.joakes.xbox_sidekick.views.ImageTextView;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import de.greenrobot.event.EventBus;

public class GameActivity extends AppCompatActivity {
    @Inject
    EventBus eventBus;
    @Inject
    WebService webService;
    @InjectView(R.id.games_list)
    RecyclerView gamesList;

    private XboxGameAdapter mAdapter;
    public ImageView gamerPicture;
    public ImageTextView gamerscoreImageTextView;
    public TextView profileNameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupActivity();
        makeRequests();
        setupRecylerView();
        setRecyclerViewHeader();
    }

    private void setupActivity() {
        setContentView(R.layout.activity_game);
        ButterKnife.inject(this);
        ((BaseApplication) getApplication()).component().inject(this);
    }

    private void setupRecylerView() {
        gamesList.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        gamesList.setLayoutManager(layoutManager);
        mAdapter = new XboxGameAdapter(this);
        gamesList.setAdapter(mAdapter);
    }

    private void makeRequests() {
        webService.getProfile();
        webService.getGameList();
    }

    private void setRecyclerViewHeader() {
        RecyclerViewHeader header = RecyclerViewHeader.fromXml(this, R.layout.profile_view);
        header.attachTo(gamesList);
        setProfileViews();
    }

    private void setProfileViews() {
        gamerPicture = (ImageView) findViewById(R.id.gamer_picture);
        gamerscoreImageTextView = (ImageTextView) findViewById(R.id.gamerscore_image_textview);
        profileNameTextView = (TextView) findViewById(R.id.profile_name_textview);
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
        webService.stop(GameActivity.class.toString());
    }

    public void onEvent(XboxProfile profile) {
        webService.loadImageFromUrl(gamerPicture, profile.getGamerPictureUrl());
        profileNameTextView.setText(profile.getGamertag());
        gamerscoreImageTextView.setImageAndTextIfValid(profile.getGamerscore(), R.drawable.ic_gamerscore);
    }

    public void onEvent(ArrayList<XboxGame> games) {
        mAdapter.addGames(games);
    }
}