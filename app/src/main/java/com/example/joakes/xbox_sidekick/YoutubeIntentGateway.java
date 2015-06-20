package com.example.joakes.xbox_sidekick;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.youtube.player.YouTubeIntents;

/**
 * Created by joakes on 6/20/15.
 */
public class YoutubeIntentGateway {
    public static void ensurePlayVideo(Context context, FragmentManager fragmentManager, String videoId) {
        if(YouTubeIntents.canResolvePlayVideoIntent(context)){
            Intent intent = YouTubeIntents.createPlayVideoIntent(context, videoId);
            context.startActivity(intent);
        } else {
            getNoYoutubeDialog(context).show(fragmentManager, null);
        }
    }

    private static DialogFragment getNoYoutubeDialog(final Context context) {
        return new DialogFragment() {
            @Override
            public Dialog onCreateDialog(Bundle savedInstanceState) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
//                builder.setTitle(context.getString(R.string.no_youtube_title));
//                builder.setMessage(context.getString(R.string.no_youtube_message));
                builder.setNegativeButton(context.getString(android.R.string.ok), null);
                return builder.create();
            }
        };
    }
}
