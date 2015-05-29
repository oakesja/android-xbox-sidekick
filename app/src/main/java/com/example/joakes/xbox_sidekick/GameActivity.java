package com.example.joakes.xbox_sidekick;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.joakes.xbox_sidekick.models.XboxGame;
import com.example.joakes.xbox_sidekick.models.XboxProfile;

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

    @InjectView(R.id.profile_name_textview)
    TextView profileNameTextView;
    @InjectView(R.id.gamerscore_textview)
    TextView gamerscoreTextView;
    @InjectView(R.id.gamer_picture)
    ImageView gamerPicture;
    @InjectView(R.id.gamerscore_imageview)
    ImageView gamerscoreImageView;
    @InjectView(R.id.games_list)
    RecyclerView gamesList;

    private XboxGameAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        ButterKnife.inject(this);

        ((BaseApplication) getApplication()).component().inject(this);
        webService.getProfile();
        webService.getGameList();

        gamesList.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        gamesList.setLayoutManager(layoutManager);
        mAdapter = new XboxGameAdapter(this);
        gamesList.setAdapter(mAdapter);
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
        setGamerScore(profile.getGamerscore());
    }

    public void onEvent(ArrayList<XboxGame> games) {
        mAdapter.addGames(games);
    }

    private void setGamerScore(int gamerScore){
        if(gamerScore == -1) {
            gamerscoreTextView.setVisibility(ImageView.INVISIBLE);
            gamerscoreImageView.setVisibility(ImageView.INVISIBLE);
        } else {
            gamerscoreTextView.setText("" + gamerScore);
            gamerscoreImageView.setVisibility(ImageView.VISIBLE);
        }
    }
}