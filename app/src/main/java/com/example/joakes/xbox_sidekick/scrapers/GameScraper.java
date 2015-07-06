package com.example.joakes.xbox_sidekick.scrapers;

import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by joakes on 7/3/15.
 */

// TODO handle null pointer exceptions
// TODO cache game name
public class GameScraper {
    private final String SEARCH_URL_FORMATTED = "http://www.ign.com/search?q=%s&page=0&count=1&filter=games";
    private final String XBOX_360_ACHIEVEMENTS_URL_FORMATTED = "http://www.xboxachievements.com/game/%s/achievements/";
    private final String TRUE_ACHIVEMENTS_URL_FORMATTED = "http://www.trueachievements.com/%s/achievements.htm";
    private final String TAG = "GAMESCRAPER";

    public GameScraper() {
    }

    public String getFullGameName(String gameName) {
        Document doc = getIgnSearchPage(gameName);
        return doc.select(".search-item-title > a").text();
    }

    public String getGameCoverUrl(String gameName) {
        Document doc = getIgnGamePage(gameName);
        return doc.select("img.highlight-boxArt").first().attr("src");
    }

    public String getIgnWiki(String gameName) {
        Document doc = getIgnGamePage(gameName);
        return doc.select("a[title*=wiki]").first().attr("href");
    }

    public String getXbox360AchievementsGameUrl(String gameName) {
        String name = getGameNameUrlEncoded(gameName);
        return String.format(XBOX_360_ACHIEVEMENTS_URL_FORMATTED, name);
    }

    public String getXbox360AchievementsAchievementUrl(String gameName, String achievementName) {
        String url = getXbox360AchievementsGameUrl(gameName);
        Document doc = getPage(url);
        String selector = String.format("a:contains(%s)", achievementName);
        return "http://www.xboxachievements.com" + doc.select(selector).first().attr("href");
    }

    public String getTrueAchievementsGameUrl(String gameName) {
        String name = getGameNameUrlEncoded(gameName);
        return String.format(TRUE_ACHIVEMENTS_URL_FORMATTED, name);
    }

    public String getTrueAchievementsAchievementUrl(String gameName, String achievementName) {
        String url = getTrueAchievementsGameUrl(gameName);
        Document doc = getPage(url);
        String selector = String.format("a[href*=%s]", stringReplaceNonLetter(achievementName));
        return "http://www.trueachievements.com" + doc.select(selector).first().attr("href");
    }

    private Document getIgnSearchPage(String gameName) {
        gameName = encode(gameName);
        String url = String.format(SEARCH_URL_FORMATTED, gameName);
        return getPage(url);
    }

    private Document getIgnGamePage(String gameName) {
        Document doc = getIgnSearchPage(gameName);
        Elements links = doc.select(".search-item-sub-title > a:contains(xbox 360)");
        String url = links.first().attr("href");
        return getPage(url);
    }

    private Document getPage(String url) {
        try {
            return Jsoup.connect(url).get();
        } catch (IOException e) {
            Log.e(TAG, "getPage failed for " + url + " : " + e.getMessage());
            return new Document("");
        }
    }

    private String encode(String param) {
        try {
            return URLEncoder.encode(param, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            Log.e(TAG, "encoding " + param + "failed: " + e.getMessage());
            return "";
        }
    }

    private String getGameNameUrlEncoded(String gameName) {
        String name = getFullGameName(gameName);
        return stringReplaceNonLetter(name);
    }

    private String stringReplaceNonLetter(String param) {
        return param.replaceAll("[^\\w\\s]", "").replaceAll("\\s", "-").toLowerCase();
    }
}
