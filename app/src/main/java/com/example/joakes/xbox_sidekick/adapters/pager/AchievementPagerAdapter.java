package com.example.joakes.xbox_sidekick.adapters.pager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.joakes.xbox_sidekick.R;
import com.example.joakes.xbox_sidekick.activities.AchievementsActivity;
import com.example.joakes.xbox_sidekick.activities.GameActivity;
import com.example.joakes.xbox_sidekick.fragments.AchievementListFragment;
import com.example.joakes.xbox_sidekick.fragments.GameListFragment;
import com.example.joakes.xbox_sidekick.models.Game;

/**
 * Created by joakes on 6/23/15.
 */
public class AchievementPagerAdapter extends FragmentStatePagerAdapter {
    private final String[] tabNames;

    public AchievementPagerAdapter(AchievementsActivity activity) {
        super(activity.getSupportFragmentManager());
//        tabNames = activity.getResources().getStringArray(R.array.achievement_tabs);
        tabNames = new String[0];
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = new AchievementListFragment();
        boolean isLocked = position == 1;
        Bundle bundle = new Bundle();
        bundle.putBoolean(AchievementListFragment.ACHIEVEMNT_IS_LOCKED, isLocked);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return tabNames.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabNames[position];
    }
}
