package com.example.joakes.xbox_sidekick.scrapers;

import com.example.joakes.xbox_sidekick.models.AchievementHelp;

import java.util.ArrayList;

/**
 * Created by joakes on 7/30/15.
 */
public class AchievementHelpGatherer {
    AchievementHelpScraper[] scrapers;

    public AchievementHelpGatherer(String gameName) {
        gameName = encodeGameName(gameName);
        scrapers = new AchievementHelpScraper[]{
                new TrueAchievementScraper(gameName),
                new XboxAchievementScraper(gameName),
        };
    }

    public AchievementHelp[] gatherHelpForAchievement(String achievement) {
        ArrayList<AchievementHelp> helpList = new ArrayList<>();
        for (int i = 0; i < scrapers.length; i++) {
            AchievementHelp help = scrapers[i].scrapeAchievementHelp(achievement);
            if (help != null) {
                helpList.add(help);
            }
        }
        return helpList.toArray(new AchievementHelp[helpList.size()]);
    }

    private String encodeGameName(String gameName) {
        gameName = gameName.replaceAll("[^\\w\\s]", "");
        gameName = gameName.replaceAll("[\\s]", "-");
        return gameName.toLowerCase();
    }
}
