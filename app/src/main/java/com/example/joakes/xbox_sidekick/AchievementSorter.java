package com.example.joakes.xbox_sidekick;

import com.example.joakes.xbox_sidekick.models.Achievement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by joakes on 7/1/15.
 */
public class AchievementSorter {

    public static ArrayList<Achievement> sort(ArrayList<Achievement> achievements) {
        if (!achievements.get(0).isLocked()) {
            sortLocked(achievements);
        } else {
            sortUnlocked(achievements);
        }
        return achievements;
    }

    private static void sortLocked(ArrayList<Achievement> achievements){
        Collections.sort(achievements, new Comparator<Achievement>() {
            @Override
            public int compare(Achievement lhs, Achievement rhs) {
                return -lhs.getTimeUnlocked().compareTo(rhs.getTimeUnlocked());
            }
        });
    }

    private static void sortUnlocked(ArrayList<Achievement> achievements) {
        Collections.sort(achievements, new Comparator<Achievement>() {
            @Override
            public int compare(Achievement lhs, Achievement rhs) {
                return lhs.getId() < rhs.getId() ? -1 : (lhs.getId() == rhs.getId() ? 0 : 1);
            }
        });
    }
}
