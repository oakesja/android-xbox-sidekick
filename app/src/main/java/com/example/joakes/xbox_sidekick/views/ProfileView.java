package com.example.joakes.xbox_sidekick.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import com.example.joakes.xbox_sidekick.R;

/**
 * Created by joakes on 5/27/15.
 */
public class ProfileView extends RelativeLayout {
    public ProfileView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.profile_view, this);
    }
}
