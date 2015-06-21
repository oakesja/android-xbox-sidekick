package com.example.joakes.xbox_sidekick.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.bartoszlipinski.recyclerviewheader.RecyclerViewHeader;
import com.example.joakes.xbox_sidekick.R;
import com.example.joakes.xbox_sidekick.adapters.AchievementAdapter;
import com.example.joakes.xbox_sidekick.dagger.BaseApplication;
import com.example.joakes.xbox_sidekick.models.Achievement;
import com.example.joakes.xbox_sidekick.models.XboxGame;
import com.example.joakes.xbox_sidekick.presenters.GameInfoPresenter;
import com.example.joakes.xbox_sidekick.requests.utils.WebService;
import com.example.joakes.xbox_sidekick.views.CircularProgressBar;
import com.example.joakes.xbox_sidekick.views.ImageTextView;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import de.greenrobot.event.EventBus;

public class AchievementsActivity extends AppCompatActivity {
    @InjectView(R.id.achievement_list)
    RecyclerView achievementList;
    @Inject
    WebService webService;
    public static final String GAME = "com.example.joakes.xbox_sidekick.game";
    public TextView gameName;
    public ImageTextView gameAchievements;
    public ImageTextView gameScore;
    public CircularProgressBar gameProgress;
    private AchievementAdapter adapter;
    private EventBus eventBus;
    private XboxGame game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupActivity();
        setupRecyclerView();
        setRecyclerViewHeader();
        webService.getAchievementsFor(game, getClass().getName());
    }

    private void setupActivity() {
        setContentView(R.layout.activity_achievements);
        ButterKnife.inject(this);
        ((BaseApplication) getApplication()).component().inject(this);
        game = getIntent().getParcelableExtra(GAME);
        eventBus = EventBus.getDefault();
        webService = new WebService(this);
    }

    private void setupRecyclerView() {
        achievementList.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        achievementList.setLayoutManager(layoutManager);
        adapter = new AchievementAdapter(this);
        achievementList.setAdapter(adapter);
    }

    private void setRecyclerViewHeader() {
        RecyclerViewHeader header = RecyclerViewHeader.fromXml(this, R.layout.game_info);
        header.attachTo(achievementList);
        setGameInfoViews();
        new GameInfoPresenter(game).present(gameName, gameAchievements,
                gameScore, gameProgress);
    }

    private void setGameInfoViews() {
        gameName = (TextView) findViewById(R.id.game_name_textview);
        gameAchievements = (ImageTextView) findViewById(R.id.game_achievements_image_textview);
        gameScore = (ImageTextView) findViewById(R.id.gamerscore_image_textview);
        gameProgress = (CircularProgressBar) findViewById(R.id.gamerscore_progress_bar);
    }

    public void onEvent(ArrayList<Achievement> achievements) {
        adapter.addAchievements(achievements);
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
        webService.stop(getClass().toString());
    }
}
