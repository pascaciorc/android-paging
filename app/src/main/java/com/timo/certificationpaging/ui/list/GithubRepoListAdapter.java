package com.timo.certificationpaging.ui.list;

import android.arch.paging.PagedListAdapter;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.timo.certificationpaging.R;
import com.timo.certificationpaging.data.database.GithubRepoEntry;

import java.util.List;

public class GithubRepoListAdapter extends PagedListAdapter<GithubRepoEntry, RepoListViewHolder> {
    //private List<GithubRepoEntry> mRepoList;

    protected GithubRepoListAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public RepoListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.repo_item, parent, false);
        return new RepoListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RepoListViewHolder holder, int position) {
        GithubRepoEntry entry = getItem(position);
        holder.titleText.setText(entry.getName());
        holder.descText.setText(entry.getDescription());
    }


    /*@NonNull
    @Override
    public RepoListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.repo_item, parent, false);
        return new RepoListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RepoListViewHolder holder, int position) {
        GithubRepoEntry entry = mRepoList.get(position);
        holder.titleText.setText(entry.getName());
        holder.descText.setText(entry.getDescription());
    }

    @Override
    public int getItemCount() {
        if (mRepoList == null) return 0;
        else return mRepoList.size();
    }

    void swapRepos(List<GithubRepoEntry> entries) {
        mRepoList = entries;
        notifyDataSetChanged();
    }*/

    private static DiffUtil.ItemCallback<GithubRepoEntry> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<GithubRepoEntry>() {
                // Concert details may have changed if reloaded from the database,
                // but ID is fixed.
                @Override
                public boolean areItemsTheSame(GithubRepoEntry oldRepo, GithubRepoEntry newRepo) {
                    return oldRepo.getId() == newRepo.getId();
                }

                @Override
                public boolean areContentsTheSame(GithubRepoEntry oldRepo,
                                                  GithubRepoEntry newRepo) {
                    return oldRepo.equals(newRepo);
                }
            };
}
