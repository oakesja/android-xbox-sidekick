package com.example.joakes.xbox_sidekick.presenters;

import com.example.joakes.xbox_sidekick.views.View;

public interface Presenter<T extends View> {

    void attachToView(T v);

    void detachFromView();
}
