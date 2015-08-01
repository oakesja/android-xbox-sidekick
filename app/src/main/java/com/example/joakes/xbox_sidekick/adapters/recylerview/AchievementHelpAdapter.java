package com.example.joakes.xbox_sidekick.adapters.recylerview;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.joakes.xbox_sidekick.R;
import com.example.joakes.xbox_sidekick.models.AchievementHelp;
import com.example.joakes.xbox_sidekick.requests.WebService;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class AchievementHelpAdapter extends RecyclerView.Adapter<AchievementHelpAdapter.AchievmentHelpViewHolder> {
    private ArrayList<AchievementHelp> help;
    private Context context;
    private WebService webService;

    public AchievementHelpAdapter(Context context, WebService webService) {
        this.context = context;
        this.webService = webService;
        help = new ArrayList<>();
    }

    @Override
    public AchievmentHelpViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.achievement_help, parent, false);
        return new AchievmentHelpViewHolder(v);
    }

    @Override
    public void onBindViewHolder(AchievmentHelpViewHolder holder, int position) {
        final AchievementHelp help = getHelpAt(position);
        holder.helpTitle.setText(help.getName());
        webService.loadImageFromUrl(holder.helpIcon, help.getIconUrl());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(help.getUrl()));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return help.size();
    }

    public void addHelp(List<AchievementHelp> helpList) {
        help.addAll(helpList);
        notifyDataSetChanged();
    }

    public AchievementHelp getHelpAt(int position) {
        return help.get(position);
    }

    protected class AchievmentHelpViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.help_icon)
        ImageView helpIcon;
        @InjectView(R.id.help_title)
        TextView helpTitle;

        public AchievmentHelpViewHolder(View view) {
            super(view);
            ButterKnife.inject(this, view);
        }
    }
}
