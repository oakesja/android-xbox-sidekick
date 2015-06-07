package com.example.joakes.xbox_sidekick;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.joakes.xbox_sidekick.models.Achievement;
import com.example.joakes.xbox_sidekick.models.XboxGame;
import com.example.joakes.xbox_sidekick.recycler_view_adapters.AchievementAdapter;
import com.example.joakes.xbox_sidekick.views.ImageTextView;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import de.greenrobot.event.EventBus;


public class AchievementsActivity extends AppCompatActivity {
    @InjectView(R.id.game_name_textview)
    TextView gameNameTextView;
    @InjectView(R.id.game_achievements_image_textview)
    ImageTextView gameAchievementsImageTextview;
    @InjectView(R.id.gamerscore_image_textview)
    ImageTextView gamerscoreImageTextview;
    @InjectView(R.id.achievement_list)
    RecyclerView achievementList;

    public static final String GAME = "com.example.joakes.xbox_sidekick.game";
    private static String REQUEST_TAG = "ACHIEVEMENT_ACTIVITY";
    private AchievementAdapter mAdapter;
    private EventBus eventBus;
    private WebService mWebService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achievements);
        ((BaseApplication) getApplication()).component().inject(this);
        ButterKnife.inject(this);
        eventBus = EventBus.getDefault();

        XboxGame game = getIntent().getParcelableExtra(GAME);
        gameNameTextView.setText(game.getName());
        gameAchievementsImageTextview.setImageAndTextIfValid(game.getEarnedAchievements(), game.getTotalAchivements(), R.drawable.ic_trophy);
        gamerscoreImageTextview.setImageAndTextIfValid(game.getEarnedGamerscore(), game.getTotalGamerscore(), R.drawable.ic_gamerscore);
        setupRecyclerView();
        mWebService = new WebService(this);
        mWebService.getAchievementsFor(game, REQUEST_TAG);
    }

    private void setupRecyclerView() {
        achievementList.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        achievementList.setLayoutManager(layoutManager);
        mAdapter = new AchievementAdapter();
        achievementList.setAdapter(mAdapter);
//        achievementList.addOnItemTouchListener(this);
    }

    public void onEvent(ArrayList<Achievement> achievements) {
        mAdapter.addAchievements(achievements);
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
        mWebService.stop(getClass().toString());
    }
}
