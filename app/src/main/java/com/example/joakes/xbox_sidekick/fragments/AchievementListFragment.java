package com.example.joakes.xbox_sidekick.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.joakes.xbox_sidekick.R;
import com.example.joakes.xbox_sidekick.adapters.recylerview.AchievementAdapter;
import com.example.joakes.xbox_sidekick.adapters.recylerview.DividerItemDecoration;
import com.example.joakes.xbox_sidekick.models.Achievement;

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
    private AchievementAdapter adapter;
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
        adapter = new AchievementAdapter(getActivity());
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity()));
    }

    public void onEvent(ArrayList<Achievement> achievements) {
        ArrayList<Achievement> filteredAchievements = filterAchievements(achievements);
        adapter = new AchievementAdapter(getActivity(), filteredAchievements);
        recyclerView.post(new Runnable() {
            @Override
            public void run() {
                recyclerView.setAdapter(adapter);
            }
        });
    }

    private ArrayList<Achievement> filterAchievements(ArrayList<Achievement> achievements) {
        ArrayList<Achievement> filteredAchievements = new ArrayList<>();
        for (Achievement achievement : achievements) {
            if (achievement.isLocked() == isLocked) {
                filteredAchievements.add(achievement);
            }
        }
        return filteredAchievements;
    }

    @Override
    public void onStop() {
        super.onStop();
        eventBus.unregister(this);
    }
}

