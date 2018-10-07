package com.timo.certificationpaging.ui.list;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.timo.certificationpaging.R;

public class RepoListViewHolder extends RecyclerView.ViewHolder {
    TextView titleText;
    TextView descText;

    public RepoListViewHolder(View itemView) {
        super(itemView);

        titleText = itemView.findViewById(R.id.title_text);
        descText = itemView.findViewById(R.id.desc_text);
    }
}
