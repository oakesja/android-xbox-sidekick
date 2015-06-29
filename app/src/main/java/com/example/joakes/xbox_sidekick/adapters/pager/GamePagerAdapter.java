package com.example.joakes.xbox_sidekick.adapters.pager;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.joakes.xbox_sidekick.R;
import com.example.joakes.xbox_sidekick.fragments.GameListFragment;
import com.example.joakes.xbox_sidekick.models.Game;

/**
 * Created by joakes on 6/23/15.
 */
public class GamePagerAdapter extends FragmentStatePagerAdapter {
    private final String[] tabNames;

    public GamePagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        tabNames = context.getResources().getStringArray(R.array.game_tabs);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = new GameListFragment();
        int type = position == 0 ? Game.XBOX_ONE : Game.XBOX_360;
        Bundle bundle = new Bundle();
        bundle.putInt(GameListFragment.GAME_TYPE, type);
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
