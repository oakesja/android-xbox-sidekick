package com.example.joakes.xbox_sidekick.presenters_old;

import android.test.AndroidTestCase;

import com.example.joakes.xbox_sidekick.helpers.TestSetup;
import com.example.joakes.xbox_sidekick.models.Achievement;

public class AchievementPresenter2Test extends AndroidTestCase {
    private Achievement achievement;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        achievement = TestSetup.createAchievement();
    }

    public void testUnlockedTimeUnlocked(){
        achievement.setLocked(false);
        String expected = "Unlocked on 10-16-2014";
        assertEquals(expected, createPresenter().unlockedTime());
    }

    public void testUnlockedTimeNull(){
        achievement.setTimeUnlocked(null);
        String expected = "";
        assertEquals(expected, createPresenter().unlockedTime());
    }

    public void testUnlockedTimeLocked(){
        achievement.setLocked(true);
        String expected = "";
        assertEquals(expected, createPresenter().unlockedTime());
    }

    public void testUnlockedDescriptionIgnoreSecret(){
        achievement.setLocked(false);
        achievement.setIsSecret(true);
        assertEquals(achievement.getDescription(), createPresenter().descriptionIgnoreSecret());
    }

    public void testLockedDescriptionIgnoreSecret(){
        achievement.setLocked(true);
        achievement.setIsSecret(true);
        assertEquals(achievement.getLockedDescription(), createPresenter().descriptionIgnoreSecret());
    }

    private AchievementPresenter2 createPresenter() {
        return new AchievementPresenter2(achievement);
    }
}
