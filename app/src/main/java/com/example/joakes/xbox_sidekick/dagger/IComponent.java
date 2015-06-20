package com.example.joakes.xbox_sidekick.dagger;

import com.example.joakes.xbox_sidekick.activities.AchievementHelpActivity;
import com.example.joakes.xbox_sidekick.activities.AchievementsActivity;

/**
 * Created by joakes on 6/19/15.
 */
public interface IComponent {
    void inject(AchievementHelpActivity activity);
    void inject(AchievementsActivity activity);
}
