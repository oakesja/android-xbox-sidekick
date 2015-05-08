package com.example.joakes.xbox_sidekick;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.joakes.xbox_sidekick.models.XboxProfile;

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
    @InjectView(R.id.games_listview)
    ListView gamesListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        ButterKnife.inject(this);

        ((BaseApplication) getApplication()).component().inject(this);
        webService.getProfile();
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
        profileNameTextView.setText(profile.getGamertag());
        gamerscoreTextView.setText("" + profile.getGamerscore());
        webService.loadImageFromUrl(gamerPicture, profile.getGamerPictureUrl());
        gamerscoreImageView.setVisibility(ImageView.VISIBLE);
    }
}