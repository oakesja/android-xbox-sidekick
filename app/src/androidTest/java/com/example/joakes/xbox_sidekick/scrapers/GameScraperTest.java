package com.example.joakes.xbox_sidekick.scrapers;

import android.test.AndroidTestCase;

/**
 * Created by joakes on 7/3/15.
 */
public class GameScraperTest extends AndroidTestCase {
    private final String GAME_NAME = "AC Liberation HD";
    private final String ACHIEVEMENT_NAME = "Swamp Queen";
    private GameScraper scraper;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        scraper = new GameScraper();
    }

    public void testGetName() {
        assertEquals("Assassin's Creed Liberation HD", scraper.getFullGameName(GAME_NAME));
    }

    public void testGetGameCoverUrl() {
        String expected = "http://assets2.ignimgs.com/2013/09/10/aclibhdxblajpg-8832a7_160h.jpg";
        assertEquals(expected, scraper.getGameCoverUrl(GAME_NAME));
    }

    public void testGetIgnWiki() {
        String expected = "http://www.ign.com/wikis/assassins-creed-3-liberation";
        assertEquals(expected, scraper.getIgnWiki(GAME_NAME));
    }

    public void testGetXbox360AchievementsGameUrl() {
        String expected = "http://www.xboxachievements.com/game/assassins-creed-liberation-hd/achievements/";
        assertEquals(expected, scraper.getXbox360AchievementsGameUrl(GAME_NAME));
    }

    public void testGetXbox360AchievementsAchievementUrl() {
        String expected = "http://www.xboxachievements.com/game/assassins-creed-liberation-hd/achievement/85371-Swamp-Queen.html";
        assertEquals(expected, scraper.getXbox360AchievementsAchievementUrl(GAME_NAME, ACHIEVEMENT_NAME));
    }

    public void testGetTrueAchievementsGameUrl() {
        String expected = "http://www.trueachievements.com/assassins-creed-liberation-hd/achievements.htm";
        assertEquals(expected, scraper.getTrueAchievementsGameUrl(GAME_NAME));
    }

    public void testGetTrueAchievementsAchievementUrl() {
        String expected = "http://www.trueachievements.com/a183734/swamp-queen-achievement.htm";
        assertEquals(expected, scraper.getTrueAchievementsAchievementUrl(GAME_NAME, ACHIEVEMENT_NAME));
    }
}
