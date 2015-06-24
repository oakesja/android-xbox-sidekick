package com.example.joakes.xbox_sidekick.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.GregorianCalendar;

/**
 * Created by joakes on 5/11/15.
 */
public class Game implements Parcelable {
    public static final int XBOX_360 = 360;
    public static final int XBOX_ONE = 720;

    private long titleId;
    private String name;
    private int earnedAchievements;
    private int totalAchivements;
    private int earnedGamerscore;
    private int totalGamerscore;
    private int type;
    private GregorianCalendar lastPlayedTime;

    public Game() {
    }

    public Game(long titleId, String name, int earnedAchievements, int totalAchivements,
                int earnedGamerscore, int totalGamerscore, GregorianCalendar lastPlayedTime, int type) {
        this.titleId = titleId;
        this.name = name;
        this.earnedAchievements = earnedAchievements;
        this.totalAchivements = totalAchivements;
        this.earnedGamerscore = earnedGamerscore;
        this.totalGamerscore = totalGamerscore;
        this.type = type;
        this.lastPlayedTime = lastPlayedTime;
    }

    private Game(Parcel in) {
        titleId = in.readLong();
        name = in.readString();
        earnedAchievements = in.readInt();
        totalAchivements = in.readInt();
        earnedGamerscore = in.readInt();
        totalGamerscore = in.readInt();
        type = in.readInt();
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTimeInMillis(in.readLong());
        lastPlayedTime = calendar;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(titleId);
        dest.writeString(name);
        dest.writeInt(earnedAchievements);
        dest.writeInt(totalAchivements);
        dest.writeInt(earnedGamerscore);
        dest.writeInt(totalGamerscore);
        dest.writeInt(type);
        if(lastPlayedTime != null){
            dest.writeLong(lastPlayedTime.getTimeInMillis());
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Game> CREATOR = new Creator<Game>() {
        @Override
        public Game createFromParcel(Parcel in) {
            return new Game(in);
        }

        @Override
        public Game[] newArray(int size) {
            return new Game[size];
        }
    };

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Game game = (Game) o;

        if (titleId != game.titleId) return false;
        if (earnedAchievements != game.earnedAchievements) return false;
        if (totalAchivements != game.totalAchivements) return false;
        if (earnedGamerscore != game.earnedGamerscore) return false;
        if (totalGamerscore != game.totalGamerscore) return false;
        if (type != game.type) return false;
        if (name != null ? !name.equals(game.name) : game.name != null) return false;
        return !(lastPlayedTime != null ? !lastPlayedTime.equals(game.lastPlayedTime) : game.lastPlayedTime != null);

    }

    @Override
    public int hashCode() {
        int result = (int) (titleId ^ (titleId >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + earnedAchievements;
        result = 31 * result + totalAchivements;
        result = 31 * result + earnedGamerscore;
        result = 31 * result + totalGamerscore;
        result = 31 * result + type;
        result = 31 * result + (lastPlayedTime != null ? lastPlayedTime.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Game{" +
                "titleId=" + titleId +
                ", name='" + name + '\'' +
                ", earnedAchievements=" + earnedAchievements +
                ", totalAchivements=" + totalAchivements +
                ", earnedGamerscore=" + earnedGamerscore +
                ", totalGamerscore=" + totalGamerscore +
                ", type=" + type +
                ", lastPlayedTime=" + lastPlayedTime +
                '}';
    }
}
