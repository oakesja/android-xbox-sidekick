package com.example.joakes.xbox_sidekick.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bartoszlipinski.recyclerviewheader.RecyclerViewHeader;
import com.example.joakes.xbox_sidekick.GameListFilter;
import com.example.joakes.xbox_sidekick.R;
import com.example.joakes.xbox_sidekick.adapters.XboxGameAdapter;
import com.example.joakes.xbox_sidekick.models.Game;
import com.example.joakes.xbox_sidekick.models.Profile;
import com.example.joakes.xbox_sidekick.requests.utils.WebService;
import com.example.joakes.xbox_sidekick.views.ImageTextView;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import de.greenrobot.event.EventBus;

public class GameActivity extends AppCompatActivity {
    @InjectView(R.id.games_list)
    RecyclerView gamesList;
    @Inject
    WebService webService;
    public ImageView profilePicture;
    public ImageTextView profileGamerscore;
    public TextView profileName;
    private final String REQUEST_TAG = getClass().getName();
    private XboxGameAdapter adapter;
    private EventBus eventBus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupActivity();
        setupRecyclerView();
        setRecyclerViewHeader();
        makeRequests();
    }

    private void setupActivity() {
        setContentView(R.layout.activity_game);
        ButterKnife.inject(this);
        eventBus = EventBus.getDefault();
        webService = new WebService(this);
    }

    private void setupRecyclerView() {
        gamesList.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        gamesList.setLayoutManager(layoutManager);
        adapter = new XboxGameAdapter(this);
        gamesList.setAdapter(adapter);
    }

    private void setRecyclerViewHeader() {
        RecyclerViewHeader header = RecyclerViewHeader.fromXml(this, R.layout.profile);
        header.attachTo(gamesList);
        setProfileViews();
    }

    private void makeRequests() {
        webService.getProfile(REQUEST_TAG);
        webService.getGameList(REQUEST_TAG);
    }

    private void setProfileViews() {
        profilePicture = (ImageView) findViewById(R.id.gamer_picture);
        profileGamerscore = (ImageTextView) findViewById(R.id.profile_gamerscore);
        profileName = (TextView) findViewById(R.id.profile_name);
    }

    public void onEvent(Profile profile) {
        webService.loadImageFromUrl(profilePicture, profile.getGamerPictureUrl());
        ensureStringForTextView(profileName, profile.getGamertag());
        profileGamerscore.setImageAndTextIfValid(profile.getGamerscore(), R.drawable.ic_gamerscore);
    }

    private void ensureStringForTextView(TextView textView, String string) {
        if (string != null && !string.isEmpty()) {
            textView.setText(string);
        } else {
            textView.setVisibility(View.INVISIBLE);
        }
    }

    public void onEvent(ArrayList<Game> games) {
        games = new GameListFilter(games).filter();
        adapter.addGames(games);
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