package com.example.joakes.xbox_sidekick.presenters;

import android.support.annotation.NonNull;

import com.example.joakes.xbox_sidekick.models.Profile;
import com.example.joakes.xbox_sidekick.requests.adapters.ProfileJsonAdapter;
import com.example.joakes.xbox_sidekick.views.ProfileView;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.exceptions.OnErrorThrowable;
import rx.schedulers.Schedulers;

public class ProfilePresenter implements Presenter<ProfileView> {

    private ProfileView profileView;
    private Subscription subscription;

    @Override
    public void attachToView(ProfileView v) {
        this.profileView = v;
        getProfile();
    }

    private void getProfile() {
        subscription = profileView.getXboxApiService().getProfile()
                .subscribeOn(Schedulers.io())
                .map(this::toJsonObject)
                .map(jsonObject -> new ProfileJsonAdapter().toProfile(jsonObject))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::presentProfile, throwable -> profileView.handleError());
    }


    private void presentProfile(Profile profile) {
        profileView.showGamertag(profile.gamertag);
        profileView.showGamerscore("" + profile.gamerscore);
        profileView.showGamerPicture(iv -> {
            Picasso.with(profileView.getContext()).load(profile.gamerPictureUrl).into(iv);
            return null;
        });
    }

    @NonNull
    private JSONObject toJsonObject(String s) {
        try {
            return new JSONObject(s);
        } catch (JSONException e) {
            e.printStackTrace();
            throw OnErrorThrowable.from(e);
        }
    }

    @Override
    public void detachFromView() {
        if (subscription != null) {
            subscription.unsubscribe();
        }
    }
}
