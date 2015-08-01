package com.example.joakes.xbox_sidekick.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
    public static final String ACHIEVEMNT_IS_LOCKED = "ACHIEVEMENT_IS_LOCKED";
    @InjectView(R.id.list)
    RecyclerView list;
    @InjectView(R.id.empty_message)
    TextView emptyMessage;
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
        View v = inflater.inflate(R.layout.list_with_empty_message, container, false);
        ButterKnife.inject(this, v);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        list.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        list.setLayoutManager(layoutManager);
        list.addItemDecoration(new DividerItemDecoration(getActivity()));
    }

    public void onEvent(ArrayList<Achievement> achievements) {
        ArrayList<Achievement> filteredAchievements = filterAchievements(achievements);
        if(filteredAchievements.size() > 0){
            createAndSetAdapter(filteredAchievements);
            emptyMessage.setVisibility(View.GONE);
        } else {
            int text = isLocked ? R.string.no_locked_achievements_message : R.string.no_unlocked_achievements_message;
            emptyMessage.setText(text);
            emptyMessage.setVisibility(View.VISIBLE);
            list.setVisibility(View.GONE);
        }

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

    private void createAndSetAdapter(ArrayList<Achievement> filteredAchievements) {
        final AchievementAdapter adapter = new AchievementAdapter(getActivity(), filteredAchievements);
        list.post(new Runnable() {
            @Override
            public void run() {
                list.setAdapter(adapter);
            }
        });
    }

    @Override
    public void onStop() {
        super.onStop();
        eventBus.unregister(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}

