package com.example.joakes.xbox_sidekick.scrapers;

import android.util.Log;

import com.example.joakes.xbox_sidekick.models.AchievementHelp;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public abstract class AchievementHelpScraper {
    private final String TAG = getClass().getName();

    public AchievementHelp scrapeAchievementHelp(String achievement) {
        String url = getUrl(achievement);
        return url.isEmpty() ? null : new AchievementHelp(getName(), url, getIconUrl());
    }

    protected String getUrl(String achievement) {
        Document doc;
        try {
            doc = Jsoup.connect(getGameUrl()).get();
        } catch (IOException e) {
            Log.e(TAG, "Could not connect to the url: " + getGameUrl());
            return "";
        }
        return findUrl(achievement, doc);
    }


    protected String findUrl(String achievement, Document doc) {
        String url = "";
        String selector = getSelector(achievement);
        Elements links = doc.select(selector);
        if (links.size() > 0) {
            url = getAchievementUrl(links.first().attr("href"));
        } else {
            Log.e(TAG, String.format("no links found matching achievement: %s : %s", achievement, getGameUrl()));
        }
        return url;
    }

    protected abstract String getSelector(String achievement);

    protected abstract String getAchievementUrl(String path);

    protected abstract String getGameUrl();

    protected abstract String getName();

    protected abstract String getIconUrl();
}
