package com.example.joakes.xbox_sidekick.scrapers;

import android.test.AndroidTestCase;

import com.example.joakes.xbox_sidekick.models.AchievementHelp;

public class XboxAchievementScraperTest extends AndroidTestCase {

    public void testScrapeAchievementHelp() {
        String achievement = "Let's Cook!";
        String name = "XboxAchievements";
        String url = "http://www.xboxachievements.com/game/the-witcher-3-wild-hunt/achievement/100610-Let-s-Cook-.html";
        String icon = "http://www.xboxachievements.com/apple-touch-icon.png";
        AchievementHelp expected = new AchievementHelp(name, url, icon);
        assertEquals(expected, validScraper().scrapeAchievementHelp(achievement));
    }

    public void testScrapeAchievementHelpUnknownAchievement() {
        String achievement = "fjdsaoifj";
        assertNull(validScraper().scrapeAchievementHelp(achievement));
    }

    public void testScrapeAchievementHelpUnknownGameName() {
        String achievement = "Let's Cook!";
        assertNull(invalidScraper().scrapeAchievementHelp(achievement));
    }

    private XboxAchievementScraper validScraper() {
        return new XboxAchievementScraper("the-witcher-3-wild-hunt");
    }

    private XboxAchievementScraper invalidScraper() {
        return new XboxAchievementScraper("fasdfdsf");
    }
}
