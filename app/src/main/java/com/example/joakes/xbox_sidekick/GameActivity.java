package com.example.joakes.xbox_sidekick;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bartoszlipinski.recyclerviewheader.RecyclerViewHeader;
import com.example.joakes.xbox_sidekick.adapters.XboxGameAdapter;
import com.example.joakes.xbox_sidekick.models.XboxGame;
import com.example.joakes.xbox_sidekick.models.XboxProfile;
import com.example.joakes.xbox_sidekick.requests.utils.WebService;
import com.example.joakes.xbox_sidekick.views.ImageTextView;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import de.greenrobot.event.EventBus;

public class GameActivity extends AppCompatActivity {
    @InjectView(R.id.games_list)
    RecyclerView gamesListView;
    public ImageView userPictureView;
    public ImageTextView userGamerscoreView;
    public TextView gamertagView;
    private static String REQUEST_TAG = "GAME_ACTIVITY";
    private XboxGameAdapter mAdapter;
    private EventBus mEventBus;
    private WebService mWebService;

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
        mEventBus = EventBus.getDefault();
        mWebService = new WebService(this);
    }

    private void setupRecyclerView() {
        gamesListView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        gamesListView.setLayoutManager(layoutManager);
        mAdapter = new XboxGameAdapter(this);
        gamesListView.setAdapter(mAdapter);
    }

    private void setRecyclerViewHeader() {
        RecyclerViewHeader header = RecyclerViewHeader.fromXml(this, R.layout.profile_view);
        header.attachTo(gamesListView);
        setProfileViews();
    }

    private void makeRequests() {
        mWebService.getProfile(REQUEST_TAG);
        mWebService.getGameList(REQUEST_TAG);
    }

    private void setProfileViews() {
        userPictureView = (ImageView) findViewById(R.id.gamer_picture);
        userGamerscoreView = (ImageTextView) findViewById(R.id.gamerscore_image_textview);
        gamertagView = (TextView) findViewById(R.id.profile_name_textview);
    }

    public void onEvent(XboxProfile profile) {
        mWebService.loadImageFromUrl(userPictureView, profile.getGamerPictureUrl());
        ensureStringForTextView(gamertagView, profile.getGamertag());
        userGamerscoreView.setImageAndTextIfValid(profile.getGamerscore(), R.drawable.ic_gamerscore);
    }

    private void ensureStringForTextView(TextView textView, String string) {
        if (string != null && !string.isEmpty()) {
            textView.setText(string);
        } else {
            textView.setVisibility(View.INVISIBLE);
        }
    }

    public void onEvent(ArrayList<XboxGame> games) {
        Log.i(REQUEST_TAG, "got game list" + games.toString());
        games = new GameListFilter(games).filter();
        mAdapter.addGames(games);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mEventBus.register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mEventBus.unregister(this);
        mWebService.stop(REQUEST_TAG);
    }
}