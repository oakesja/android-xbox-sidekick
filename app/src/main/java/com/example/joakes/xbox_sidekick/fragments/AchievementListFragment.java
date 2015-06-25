package com.example.joakes.xbox_sidekick.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.joakes.xbox_sidekick.R;
import com.example.joakes.xbox_sidekick.adapters.AchievementAdapter;
import com.example.joakes.xbox_sidekick.models.Achievement;
import com.github.florent37.materialviewpager.MaterialViewPagerHelper;
import com.github.florent37.materialviewpager.adapter.RecyclerViewMaterialAdapter;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import de.greenrobot.event.EventBus;

/**
 * Created by joakes on 6/23/15.
 */
public class AchievementListFragment extends Fragment {
    @InjectView(R.id.list)
    RecyclerView recyclerView;
    public static final String ACHIEVEMNT_IS_LOCKED = "ACHIEVEMNT_IS_LOCKED";
    private AchievementAdapter recylerAdapter;
    private RecyclerViewMaterialAdapter materialAdapter;
    private EventBus eventBus;
    private boolean isLocked;

    @Override
    public void onStart() {
        super.onStart();
        setupFragment();
    }

    private void setupFragment() {
        eventBus = EventBus.getDefault();
        eventBus.register(this);
        isLocked = getArguments().getBoolean(ACHIEVEMNT_IS_LOCKED);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.list, container, false);
        ButterKnife.inject(this, v);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recylerAdapter = new AchievementAdapter(getActivity());
        materialAdapter = new RecyclerViewMaterialAdapter(recylerAdapter);
        recyclerView.setAdapter(materialAdapter);
        MaterialViewPagerHelper.registerRecyclerView(getActivity(), recyclerView, null);
    }

    public void onEvent(ArrayList<Achievement> achievements) {
        ArrayList<Achievement> filteredAchievements = new ArrayList<>();
        for (Achievement achievement: achievements) {
            if(achievement.isLocked() == isLocked){
                filteredAchievements.add(achievement);
            }
        }
        recylerAdapter = new AchievementAdapter(getActivity(), filteredAchievements);
        materialAdapter = new RecyclerViewMaterialAdapter(recylerAdapter);
        recyclerView.post(new Runnable() {
            @Override
            public void run() {
                recyclerView.setAdapter(materialAdapter);
            }
        });
    }

    @Override
    public void onStop() {
        super.onStop();
        eventBus.unregister(this);
    }
}

