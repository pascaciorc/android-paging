package com.timo.certificationpaging.ui.list;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.timo.certificationpaging.data.GithubRepoRepository;

public class GithubRepoListViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private final GithubRepoRepository mRepository;
    private String mQuery;

    public GithubRepoListViewModelFactory(GithubRepoRepository repository, String query) {
        mRepository = repository;
        mQuery = query;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new GithubRepoListActivityViewModel(mRepository, mQuery);
    }
}
