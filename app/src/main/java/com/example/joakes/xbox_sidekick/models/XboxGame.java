package com.example.joakes.xbox_sidekick.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by joakes on 5/11/15.
 */
public class XboxGame implements Parcelable {
    public static final int XBOX_360 = 360;
    public static final int XBOX_ONE = 720;

    private long titleId;
    private String name;
    private int earnedAchievements;
    private int totalAchivements;
    private int earnedGamerscore;
    private int totalGamerscore;
    private int type;

    public XboxGame() {
    }

    public XboxGame(long titleId, String name, int earnedAchievements, int totalAchivements, int earnedGamerscore, int totalGamerscore, int type) {
        this.titleId = titleId;
        this.name = name;
        this.earnedAchievements = earnedAchievements;
        this.totalAchivements = totalAchivements;
        this.earnedGamerscore = earnedGamerscore;
        this.totalGamerscore = totalGamerscore;
        this.type = type;
    }

    private XboxGame(Parcel in) {
        titleId = in.readLong();
        name = in.readString();
        earnedAchievements = in.readInt();
        totalAchivements = in.readInt();
        earnedGamerscore = in.readInt();
        totalGamerscore = in.readInt();
        type = in.readInt();
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
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<XboxGame> CREATOR = new Creator<XboxGame>() {
        @Override
        public XboxGame createFromParcel(Parcel in) {
            return new XboxGame(in);
        }

        @Override
        public XboxGame[] newArray(int size) {
            return new XboxGame[size];
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
}
