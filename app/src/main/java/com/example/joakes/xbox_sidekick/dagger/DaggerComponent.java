package com.example.joakes.xbox_sidekick.dagger;

import com.example.joakes.xbox_sidekick.fragments.AchievementHelpFragment;
import com.example.joakes.xbox_sidekick.fragments.AchievementListFragment;
import com.example.joakes.xbox_sidekick.fragments.GameListFragment;
import com.example.joakes.xbox_sidekick.activities.AchievementHelpActivity;
import com.example.joakes.xbox_sidekick.activities.AchievementsActivity;
import com.example.joakes.xbox_sidekick.activities.GameActivity;
import com.example.joakes.xbox_sidekick.fragments.VideosFragment;
import com.example.joakes.xbox_sidekick.presenters.Presenter;
import com.example.joakes.xbox_sidekick.presenters.ProfilePresenter;

public interface DaggerComponent {
//    TODO cleanup
    void inject(AchievementHelpActivity activity);
    void inject(AchievementsActivity activity);
    void inject(GameActivity activity);
    void inject(GameListFragment fragment);
    void inject(AchievementListFragment fragment);
    void inject(VideosFragment fragment);
    void inject(AchievementHelpFragment fragment);

    void inject(ProfilePresenter presenter);
}
