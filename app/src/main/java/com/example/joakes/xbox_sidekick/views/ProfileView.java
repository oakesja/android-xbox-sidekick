package com.example.joakes.xbox_sidekick.views;

import android.widget.ImageView;

import com.example.joakes.xbox_sidekick.XboxApiService;
import com.google.common.base.Function;

public interface ProfileView extends View {

    void showGamertag(String gamertag);

    void showGamerscore(String gamerscore);

    void showGamerPicture(Function<ImageView, Void> setGamerPicture);

    void handleError();

    XboxApiService getXboxApiService();
}
