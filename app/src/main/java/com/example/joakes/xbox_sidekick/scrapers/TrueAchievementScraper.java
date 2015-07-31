package com.example.joakes.xbox_sidekick.scrapers;

public class TrueAchievementScraper extends AchievementHelpScraper {
    private final String BASE_URL = "http://www.trueachievements.com";
    private String gameName;

    public TrueAchievementScraper(String gameName) {
        this.gameName = gameName;
    }

    @Override
    protected String getGameUrl() {
        return String.format("%s/%s/achievements.htm", BASE_URL, gameName);
    }

    @Override
    protected String getName() {
        return "TrueAchievements";
    }

    @Override
    protected String getIconUrl() {
        return "http://www.trueachievements.com/images/TA_podcast.png";
    }

    @Override
    protected String getSelector(String achievement) {
        return String.format("a[href*=%s]", stringReplaceNonLetter(achievement));
    }

    @Override
    protected String getAchievementUrl(String path) {
        return BASE_URL + path;
    }

    private String stringReplaceNonLetter(String param) {
        return param.replaceAll("[^\\w\\s]", "").replaceAll("\\s", "-").toLowerCase();
    }
}
