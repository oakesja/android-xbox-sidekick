package com.example.joakes.xbox_sidekick;

import android.test.AndroidTestCase;

import com.example.joakes.xbox_sidekick.helpers.TestSetup;
import com.example.joakes.xbox_sidekick.models.Achievement;

import org.junit.Test;

import java.util.ArrayList;
import java.util.GregorianCalendar;

// TODO make junit test
public class AchievementSorterTest extends AndroidTestCase {
    private ArrayList<Achievement> achievements;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        achievements = new ArrayList<>();
    }

    public void testSortUnlockedByUnlockedTime() {
        Achievement achievement1 = TestSetup.createUnlockedAchievement();
        Achievement achievement2 = TestSetup.createUnlockedAchievement();
        Achievement achievement3 = TestSetup.createUnlockedAchievement();
        achievement2.getTimeUnlocked().add(GregorianCalendar.SECOND, 2);
        achievement3.getTimeUnlocked().add(GregorianCalendar.SECOND, 1);
        achievements.add(achievement1);
        achievements.add(achievement2);
        achievements.add(achievement3);
        ArrayList<Achievement> expected = new ArrayList<>();
        expected.add(achievement2);
        expected.add(achievement3);
        expected.add(achievement1);
        assertEquals(expected, AchievementSorter.sort(achievements));
    }

    public void testSortLockedById() {
        Achievement achievement1 = TestSetup.createLockedAchievement();
        Achievement achievement2 = TestSetup.createLockedAchievement();
        Achievement achievement3 = TestSetup.createLockedAchievement();
        achievement2.setId(3);
        achievement3.setId(2);
        achievements.add(achievement1);
        achievements.add(achievement2);
        achievements.add(achievement3);
        ArrayList<Achievement> expected = new ArrayList<>();
        expected.add(achievement1);
        expected.add(achievement3);
        expected.add(achievement2);
        assertEquals(expected, AchievementSorter.sort(achievements));
    }
}
