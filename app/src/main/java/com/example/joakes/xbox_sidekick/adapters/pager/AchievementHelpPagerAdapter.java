package com.example.joakes.xbox_sidekick.adapters.pager;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.joakes.xbox_sidekick.R;
import com.example.joakes.xbox_sidekick.fragments.VideosFragment;
import com.example.joakes.xbox_sidekick.models.Achievement;

/**
 * Created by joakes on 6/27/15.
 */
public class AchievementHelpPagerAdapter extends FragmentStatePagerAdapter {
    private final String[] tabNames;
    public static final String ACHIEVEMENT = "ACHIEVEMENT";
    private Achievement achievement;

    public AchievementHelpPagerAdapter(Achievement achievement, Context context, FragmentManager fm) {
        super(fm);
        this.achievement = achievement;
        tabNames = context.getResources().getStringArray(R.array.achievement_help_tabs);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = getFragment(position);
        Bundle bundle = new Bundle();
        bundle.putParcelable(ACHIEVEMENT, achievement);
        fragment.setArguments(bundle);
        return fragment;
    }

    private Fragment getFragment(int position) {
        switch (position) {
            case 1:
                return new VideosFragment();
            default:
                return new Fragment();
        }
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
