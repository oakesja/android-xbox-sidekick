package com.example.joakes.xbox_sidekick.models;

/**
 * Created by joakes on 5/11/15.
 */
public class XboxGame {
    public static final int XBOX_360 = 360;
    public static final int XBOX_ONE = 720;

    private long titleId;
    private String name;
    private int earnedAchievements;
    private int totalAchivements;
    private int earnedGamerscore;
    private int totalGamerscore;
    private int type;

    public XboxGame(long titleId, String name, int earnedAchievements, int totalAchivements, int earnedGamerscore, int totalGamerscore, int type) {
        this.titleId = titleId;
        this.name = name;
        this.earnedAchievements = earnedAchievements;
        this.totalAchivements = totalAchivements;
        this.earnedGamerscore = earnedGamerscore;
        this.totalGamerscore = totalGamerscore;
        this.type = type;
    }

    public long getTitleId() {
        return titleId;
    }

    public void setTitleId(long titleId) {
        this.titleId = titleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEarnedAchievements() {
        return earnedAchievements;
    }

    public void setEarnedAchievements(int earnedAchievements) {
        this.earnedAchievements = earnedAchievements;
    }

    public int getTotalAchivements() {
        return totalAchivements;
    }

    public void setTotalAchivements(int totalAchivements) {
        this.totalAchivements = totalAchivements;
    }

    public int getEarnedGamerscore() {
        return earnedGamerscore;
    }

    public void setEarnedGamerscore(int earnedGamerscore) {
        this.earnedGamerscore = earnedGamerscore;
    }

    public int getTotalGamerscore() {
        return totalGamerscore;
    }

    public void setTotalGamerscore(int totalGamerscore) {
        this.totalGamerscore = totalGamerscore;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
