package com.example.joakes.xbox_sidekick;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
        // create web service
    }

    @Override
    protected void onStop() {
        super.onStop();
        eventBus.unregister(this);
        // stop web service
    }

    public void onEvent(XboxProfile profile) {
        profileNameTextView.setText(profile.getGamertag() + profile.getGamerscore() + profile.getGamerPictureUrl());
    }
}