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
import com.example.joakes.xbox_sidekick.adapters.XboxGameAdapter;
import com.example.joakes.xbox_sidekick.dagger.BaseApplication;
import com.example.joakes.xbox_sidekick.models.Game;
import com.example.joakes.xbox_sidekick.requests.utils.WebService;
import com.github.florent37.materialviewpager.MaterialViewPagerHelper;
import com.github.florent37.materialviewpager.adapter.RecyclerViewMaterialAdapter;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import de.greenrobot.event.EventBus;

/**
 * Created by joakes on 6/23/15.
 */
public class GameListFragment extends Fragment {
    @InjectView(R.id.games_list)
    RecyclerView recyclerView;
    @Inject
    WebService webService;
    public static final String GAME_TYPE = "GAME_TYPE";
    private final String REQUEST_TAG = getClass().getName();
    private XboxGameAdapter recylerAdapter;
    private RecyclerViewMaterialAdapter materialAdapter;
    private EventBus eventBus;
    private int type;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.game_list, container, false);
        ButterKnife.inject(this, v);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recylerAdapter = new XboxGameAdapter(getActivity());
        materialAdapter = new RecyclerViewMaterialAdapter(recylerAdapter);
        recyclerView.setAdapter(materialAdapter);
        MaterialViewPagerHelper.registerRecyclerView(getActivity(), recyclerView, null);
    }

    public void onEvent(ArrayList<Game> games) {
        if(games.size() < 1 || games.get(0).getType() != type) {
            return;
        }
        recylerAdapter = new XboxGameAdapter(getActivity(), games);
        materialAdapter = new RecyclerViewMaterialAdapter(recylerAdapter);
        recyclerView.post(new Runnable() {
            @Override
            public void run() {
                recyclerView.setAdapter(materialAdapter);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        setupFragment();
        makeRequests();
    }

    private void setupFragment() {
        eventBus = EventBus.getDefault();
        eventBus.register(this);
        ((BaseApplication) getActivity().getApplication()).component().inject(this);
        type = getArguments().getInt(GAME_TYPE);
    }

    private void makeRequests(){
        if(type == Game.XBOX_ONE){
            webService.getXboxOneList(REQUEST_TAG);
        } else {
            webService.getXbox360List(REQUEST_TAG);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        eventBus.unregister(this);
    }
}

