package com.example.joakes.xbox_sidekick.scrapers;

import android.test.AndroidTestCase;

import com.example.joakes.xbox_sidekick.models.AchievementHelp;

import java.util.Arrays;

public class AchievementHelpGathererTest extends AndroidTestCase {

    private String game;
    private AchievementHelp[] expected;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        game = "The Witcher 3: Wild Hunt";
        AchievementHelp help1 = new AchievementHelp(
                "TrueAchievements",
                "http://www.trueachievements.com/a200112/lets-cook-achievement.htm",
                "http://www.trueachievements.com/images/TA_podcast.png");
        AchievementHelp help2 = new AchievementHelp(
                "XboxAchievements",
                "http://www.xboxachievements.com/game/the-witcher-3-wild-hunt/achievement/100610-Let-s-Cook-.html",
                "http://www.xboxachievements.com/apple-touch-icon.png");
        expected = new AchievementHelp[]{help1, help2};
    }

    public void testGather() {
        String achievement = "Let's Cook!";
        AchievementHelpGatherer gatherer = new AchievementHelpGatherer(game);
        AchievementHelp[] actual = gatherer.gatherHelpForAchievement(achievement);
        assertTrue(Arrays.equals(expected, actual));
    }

    public void testGatherScraperFail() {
        String achievement = "fdsafdsf";
        AchievementHelpGatherer gatherer = new AchievementHelpGatherer(game);
        AchievementHelp[] actual = gatherer.gatherHelpForAchievement(achievement);
        assertTrue(Arrays.equals(new AchievementHelp[0], actual));
    }
}
