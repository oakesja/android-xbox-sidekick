package com.example.joakes.xbox_sidekick.models;

/**
 * Created by joakes on 4/28/15.
 */
public class XboxProfile {
    private String gamertag;
    private int gamerscore;
    private String gamerPictureUrl;

    public XboxProfile(String gamertag, int gamerscore, String gamerPictureUrl) {
        this.gamertag = gamertag;
        this.gamerscore = gamerscore;
        this.gamerPictureUrl = gamerPictureUrl;
    }

    public String getGamertag() {
        return gamertag;
    }

    public void setGamertag(String gamertag) {
        this.gamertag = gamertag;
    }

    public int getGamerscore() {
        return gamerscore;
    }

    public void setGamerscore(int gamerscore) {
        this.gamerscore = gamerscore;
    }

    public String getGamerPictureUrl() {
        return gamerPictureUrl;
    }

    public void setGamerPictureUrl(String gamerPictureUrl) {
        this.gamerPictureUrl = gamerPictureUrl;
    }
}
