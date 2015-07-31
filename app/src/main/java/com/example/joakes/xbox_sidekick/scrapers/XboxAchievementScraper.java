package com.example.joakes.xbox_sidekick.scrapers;

public class XboxAchievementScraper extends AchievementHelpScraper {
    private final String BASE_URL = "http://www.xboxachievements.com";
    private String gameName;

    public XboxAchievementScraper(String gameName) {
        this.gameName = gameName;
    }

    @Override
    protected String getGameUrl() {
        return String.format("%s/game/%s/achievements/", BASE_URL, gameName);
    }

    @Override
    protected String getName() {
        return "XboxAchievements";
    }

    @Override
    protected String getIconUrl() {
        return "http://www.xboxachievements.com/apple-touch-icon.png";
    }

    @Override
    protected String getSelector(String achievement) {
        return String.format("a:contains(%s)", achievement);
    }

    @Override
    protected String getAchievementUrl(String path) {
        return BASE_URL + path;
    }
}
