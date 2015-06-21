package com.example.joakes.xbox_sidekick.presenters;

import android.test.AndroidTestCase;
import android.view.LayoutInflater;
import android.view.View;

import com.example.joakes.xbox_sidekick.R;
import com.example.joakes.xbox_sidekick.adapters.AchievementViewHolder;
import com.example.joakes.xbox_sidekick.helpers.TestSetup;
import com.example.joakes.xbox_sidekick.models.Achievement;
import com.example.joakes.xbox_sidekick.requests.utils.WebService;

import static org.assertj.android.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

/**
 * Created by joakes on 6/3/15.
 */
public class AchievementPresenter2Test extends AndroidTestCase {
    private Achievement achievement;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        achievement = TestSetup.createAchievement();
    }

    public void testAchievementUnlockedNull(){
        achievement.setTimeUnlocked(null);
        AchievementPresenter2 presenter = new AchievementPresenter2(achievement);
        String expected = "";
        assertEquals(expected, presenter.unlockedTime());
    }

    public void testAchievementUnlocked(){
        achievement.setLocked(false);
        AchievementPresenter2 presenter = new AchievementPresenter2(achievement);
        String expected = "Unlocked on 10-16-2014";
        assertEquals(expected, presenter.unlockedTime());
    }

    public void testAchievementLocked(){
        achievement.setLocked(true);
        AchievementPresenter2 presenter = new AchievementPresenter2(achievement);
        String expected = "";
        assertEquals(expected, presenter.unlockedTime());
    }
}
