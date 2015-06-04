package com.example.joakes.xbox_sidekick;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
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

public class GameActivity extends AppCompatActivity
        implements RecyclerView.OnItemTouchListener {
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
        setupRecyclerView();
        setRecyclerViewHeader();
    }

    private void setupActivity() {
        setContentView(R.layout.activity_game);
        ButterKnife.inject(this);
        ((BaseApplication) getApplication()).component().inject(this);
    }

    private void setupRecyclerView() {
        gamesList.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        gamesList.setLayoutManager(layoutManager);
        mAdapter = new XboxGameAdapter(this);
        gamesList.setAdapter(mAdapter);
        gamesList.addOnItemTouchListener(this);
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

    public void onEvent(XboxProfile profile) {
        webService.loadImageFromUrl(gamerPicture, profile.getGamerPictureUrl());
        ensureStringForTextView(profileNameTextView, profile.getGamertag());
        gamerscoreImageTextView.setImageAndTextIfValid(profile.getGamerscore(), R.drawable.ic_gamerscore);
    }

    private void ensureStringForTextView(TextView textView, String string) {
        if (string != null && !string.isEmpty()) {
            textView.setText(string);
        } else {
            textView.setVisibility(View.INVISIBLE);
        }
    }

    public void onEvent(ArrayList<XboxGame> games) {
        games = new GameListFilter(games).filter();
        mAdapter.addGames(games);
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
        View v = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
        if (v != null) {
            int position = recyclerView.indexOfChild(v);
            XboxGame game = mAdapter.gameAt(position);
            Intent intent = new Intent(GameActivity.this, AchievementsActivity.class);
            intent.putExtra(AchievementsActivity.GAME, game);
            startActivity(intent);
        }
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
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
}