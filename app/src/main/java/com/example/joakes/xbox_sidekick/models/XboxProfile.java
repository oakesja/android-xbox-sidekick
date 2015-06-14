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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        XboxProfile profile = (XboxProfile) o;

        if (getGamerscore() != profile.getGamerscore()) return false;
        if (getGamertag() != null ? !getGamertag().equals(profile.getGamertag()) : profile.getGamertag() != null)
            return false;
        return !(getGamerPictureUrl() != null ? !getGamerPictureUrl().equals(profile.getGamerPictureUrl()) : profile.getGamerPictureUrl() != null);

    }

    @Override
    public int hashCode() {
        int result = getGamertag() != null ? getGamertag().hashCode() : 0;
        result = 31 * result + getGamerscore();
        result = 31 * result + (getGamerPictureUrl() != null ? getGamerPictureUrl().hashCode() : 0);
        return result;
    }
}
