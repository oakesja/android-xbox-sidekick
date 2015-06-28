package com.example.joakes.xbox_sidekick.fragments;

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
import com.example.joakes.xbox_sidekick.requests.WebService;
import com.google.api.services.youtube.model.SearchResult;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import de.greenrobot.event.EventBus;

/**
 * Created by joakes on 6/23/15.
 */
public class VideosFragment extends Fragment {
    @InjectView(R.id.list)
    RecyclerView recyclerView;
    @Inject
    WebService webService;
    private final String TAG = getClass().getName();
    private AchievementHelpAdapter adapter;
    private EventBus eventBus;

    @Override
    public void onStart() {
        super.onStart();
        setupFragment();
        makeRequest();
    }

    private void setupFragment() {
        eventBus = EventBus.getDefault();
        eventBus.register(this);
    }

    private void makeRequest() {
        Achievement achievement = getArguments().getParcelable(AchievementHelpPagerAdapter.ACHIEVEMENT);
        if(achievement != null){
            String searchTerms = String.format("%s achievement xbox", achievement.getName());
            webService.searchForYoutubeVideos(searchTerms);
        } else {
            Log.e(TAG, "makeRequest could not get achievement from arguments");
        }
    }

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
        adapter = new AchievementHelpAdapter(getActivity(), getActivity().getFragmentManager(), webService);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity()));
    }

    public void onEvent(List<SearchResult> results) {
        adapter.addSearchResults(results);
    }

    @Override
    public void onStop() {
        super.onStop();
        eventBus.unregister(this);
    }
}

