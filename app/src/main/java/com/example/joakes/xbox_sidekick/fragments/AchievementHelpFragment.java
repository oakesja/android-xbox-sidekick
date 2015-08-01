package com.example.joakes.xbox_sidekick.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.joakes.xbox_sidekick.R;
import com.example.joakes.xbox_sidekick.adapters.pager.AchievementHelpPagerAdapter;
import com.example.joakes.xbox_sidekick.adapters.recylerview.AchievementHelpAdapter;
import com.example.joakes.xbox_sidekick.adapters.recylerview.DividerItemDecoration;
import com.example.joakes.xbox_sidekick.dagger.BaseApplication;
import com.example.joakes.xbox_sidekick.models.Achievement;
import com.example.joakes.xbox_sidekick.models.AchievementHelp;
import com.example.joakes.xbox_sidekick.requests.WebService;
import com.example.joakes.xbox_sidekick.scrapers.AchievementHelpGatherer;

import java.util.Arrays;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class AchievementHelpFragment extends Fragment {
    @InjectView(R.id.list)
    RecyclerView recyclerView;
    @Inject
    WebService webService;
    private final String TAG = getClass().getName();
    private AchievementHelpAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.list, container, false);
        ButterKnife.inject(this, v);
        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        ((BaseApplication) getActivity().getApplication()).component().inject(this);
        super.onViewCreated(view, savedInstanceState);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new AchievementHelpAdapter(getActivity(), webService);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity()));
        makeRequest();
    }

    private void makeRequest() {
        Achievement achievement = getArguments().getParcelable(AchievementHelpPagerAdapter.ACHIEVEMENT);
        if (achievement != null) {
            new GetHelp(achievement.getName(), achievement.getGameName()).execute();
        } else {
            Log.e(TAG, "makeRequest could not get achievement from arguments");
        }
    }

    private class GetHelp extends AsyncTask<Void, Void, AchievementHelp[]> {
        private String achievementName;
        private String gameName;

        public GetHelp(String achievementName, String gameName) {
            this.achievementName = achievementName;
            this.gameName = gameName;
        }

        @Override
        protected AchievementHelp[] doInBackground(Void... params) {
            return new AchievementHelpGatherer(gameName).gatherHelpForAchievement(achievementName);
        }

        @Override
        protected void onPostExecute(AchievementHelp[] help) {
            adapter.addHelp(Arrays.asList(help));
        }
    }
}
