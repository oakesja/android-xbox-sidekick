package com.example.joakes.xbox_sidekick.scrapers;

import android.test.AndroidTestCase;

import com.example.joakes.xbox_sidekick.models.AchievementHelp;

public class TrueAchievementScraperTest extends AndroidTestCase {

    public void testScrapeAchievementHelp() {
        String achievement = "Let's Cook!";
        String name = "TrueAchievements";
        String url = "http://www.trueachievements.com/a200112/lets-cook-achievement.htm";
        String icon = "http://www.trueachievements.com/images/TA_podcast.png";
        AchievementHelp expected = new AchievementHelp(name, url, icon);
        AchievementHelp actual = validScraper().scrapeAchievementHelp(achievement);
        assertEquals(expected, actual);
    }

    public void testScrapeAchievementHelpUnknownAchievement() {
        String achievement = "fjdsaoifj";
        assertNull(validScraper().scrapeAchievementHelp(achievement));
    }

    public void testScrapeAchievementHelpUnknownGameName() {
        String achievement = "Let's Cook!";
        assertNull(invalidScraper().scrapeAchievementHelp(achievement));
    }

    private TrueAchievementScraper validScraper() {
        return new TrueAchievementScraper("the-witcher-3-wild-hunt");
    }

    private TrueAchievementScraper invalidScraper() {
        return new TrueAchievementScraper("fasdfdsf");
    }
}
