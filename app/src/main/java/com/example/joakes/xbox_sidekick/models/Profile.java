package com.example.joakes.xbox_sidekick.models;

public final class Profile {
    public final String gamertag;
    public final int gamerscore;
    public final String gamerPictureUrl;

    public Profile(String gamertag, int gamerscore, String gamerPictureUrl) {
        this.gamertag = gamertag;
        this.gamerscore = gamerscore;
        this.gamerPictureUrl = gamerPictureUrl;
    }
}
