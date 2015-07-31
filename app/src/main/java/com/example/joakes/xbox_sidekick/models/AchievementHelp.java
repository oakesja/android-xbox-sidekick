package com.example.joakes.xbox_sidekick.models;

/**
 * Created by joakes on 7/30/15.
 */
public class AchievementHelp {
    private String name;
    private String url;
    private String iconUrl;

    public AchievementHelp(String name, String url, String iconUrl) {
        this.name = name;
        this.url = url;
        this.iconUrl = iconUrl;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AchievementHelp that = (AchievementHelp) o;
        if (!name.equals(that.name)) return false;
        if (!url.equals(that.url)) return false;
        return iconUrl.equals(that.iconUrl);

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + url.hashCode();
        result = 31 * result + iconUrl.hashCode();
        return result;
    }
}
