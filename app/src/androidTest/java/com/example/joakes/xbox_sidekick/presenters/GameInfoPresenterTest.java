package com.example.joakes.xbox_sidekick.presenters;

import android.test.AndroidTestCase;
import android.widget.TextView;

import com.example.joakes.xbox_sidekick.helpers.TestSetup;
import com.example.joakes.xbox_sidekick.models.Game;
import com.example.joakes.xbox_sidekick.views.CircularProgressBar;
import com.example.joakes.xbox_sidekick.views.ImageTextView;

import junit.framework.Assert;

import static org.assertj.android.api.Assertions.assertThat;

/**
 * Created by joakes on 6/3/15.
 */
public class GameInfoPresenterTest extends AndroidTestCase {
    private TextView gameNameTextView;
    private ImageTextView gameAchievementsImageTextview;
    private ImageTextView gamerscoreImageTextview;
    private CircularProgressBar gamerscoreProgressBar;
    private Game xboxGame;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        xboxGame = TestSetup.createGame();
        gameNameTextView = new TextView(getContext());
        gameAchievementsImageTextview = new ImageTextView(getContext());
        gamerscoreImageTextview = new ImageTextView(getContext());
        gamerscoreProgressBar = new CircularProgressBar(getContext());
    }

    public void testGameMissingTotalGamerscore() {
        xboxGame.setTotalGamerscore(-1);
        presentGameInfo();
        assertThat(gamerscoreProgressBar).isGone();
    }

    public void testGameMissingEarnedGamerscore() {
        xboxGame.setEarnedGamerscore(-1);
        presentGameInfo();
        assertThat(gamerscoreProgressBar).isGone();
    }

    public void testGameWithWholeNumberGamerscoreProgress(){
        xboxGame.setEarnedGamerscore(100);
        xboxGame.setTotalGamerscore(1000);
        presentGameInfo();
        Assert.assertEquals(10, gamerscoreProgressBar.getProgress());
    }

    public void testGameWithFloatGamerscoreProgress(){
        xboxGame.setEarnedGamerscore(4);
        xboxGame.setTotalGamerscore(7);
        presentGameInfo();
        Assert.assertEquals(57, gamerscoreProgressBar.getProgress());
    }

    private void presentGameInfo() {
        new GameInfoPresenter(xboxGame).present(gameNameTextView, gameAchievementsImageTextview, gamerscoreImageTextview, gamerscoreProgressBar);
    }
}
