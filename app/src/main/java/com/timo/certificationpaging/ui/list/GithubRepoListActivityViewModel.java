package com.timo.certificationpaging.ui.list;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;

import com.timo.certificationpaging.data.GithubRepoRepository;
import com.timo.certificationpaging.data.database.GithubRepoEntry;

public class GithubRepoListActivityViewModel extends ViewModel {
    private final LiveData<PagedList<GithubRepoEntry>> mRepos;
    private final GithubRepoRepository mRepository;
    private String mQuery;

    public GithubRepoListActivityViewModel(GithubRepoRepository repository, String query) {
        mRepository = repository;
        mQuery = query;
        //mRepos = mRepository.getRepos();
        //mRepos = new LivePagedListBuilder<>(mRepository.getRepos(query),20).build();
        //mRepos = mRepository.getRepos(query);
        //mRepos = mRepository.getRepos(query)
        mRepos = mRepository.search(mQuery);
    }

    public LiveData<PagedList<GithubRepoEntry>> getRepos() {
        return mRepos;
    }
}
