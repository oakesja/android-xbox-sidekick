package com.example.joakes.xbox_sidekick.presenters;

import com.example.joakes.xbox_sidekick.DateConverter;
import com.example.joakes.xbox_sidekick.models.Achievement;

/**
 * Created by joakes on 6/9/15.
 */
public class AchievementPresenter2 {
    private Achievement achievement;
    private String tag = getClass().getName();

    public AchievementPresenter2(Achievement achievement) {
        this.achievement = achievement;
    }

    public String unlockedTime(){
        if(achievement.getTimeUnlocked() == null || achievement.isLocked()){
            return "";
        }
        return "Unlocked " + new DateConverter().toTimeAgo(achievement.getTimeUnlocked());
    }
}
