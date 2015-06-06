package com.example.joakes.xbox_sidekick.modules;

import android.location.LocationManager;

import com.android.volley.RequestQueue;
import com.example.joakes.xbox_sidekick.AchievementsActivity;
import com.example.joakes.xbox_sidekick.GameActivity;
import com.example.joakes.xbox_sidekick.requests.AchievementRequest;

/**
 * Created by joakes on 5/3/15.
 */
public interface IComponent {
    void inject(GameActivity activity);

    void inject(AchievementsActivity activity);

    void inject(AchievementRequest request);

}
