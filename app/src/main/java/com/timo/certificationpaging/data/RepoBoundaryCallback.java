package com.timo.certificationpaging.data;

import android.arch.paging.PagedList;
import android.arch.paging.PagedListAdapter;
import android.support.annotation.NonNull;
import android.util.Log;

import com.timo.certificationpaging.AppExecutors;
import com.timo.certificationpaging.data.database.GithubRepoDao;
import com.timo.certificationpaging.data.database.GithubRepoEntry;
import com.timo.certificationpaging.data.database.GithubReposResponse;
import com.timo.certificationpaging.data.network.GithubRepoNetworkDataSource;
import com.timo.certificationpaging.data.network.NetworkUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepoBoundaryCallback extends PagedList.BoundaryCallback<GithubRepoEntry> {
    private static final int NETWORK_PAGE_SIZE = 50;
    private int lastPage = 1;
    private String query;
    private boolean isRequestInProgress = false;
    private final GithubRepoDao mGithubRepoDao;
    private final GithubRepoNetworkDataSource mGithubRepoNetworkDataSource;
    private final AppExecutors mAppExecutors;

    public RepoBoundaryCallback(String query, GithubRepoDao githubRepoDao, GithubRepoNetworkDataSource githubRepoNetworkDataSource, AppExecutors appExecutors) {
        this.query = query;
        mGithubRepoDao = githubRepoDao;
        mGithubRepoNetworkDataSource = githubRepoNetworkDataSource;
        mAppExecutors = appExecutors;
    }

    @Override
    public void onZeroItemsLoaded() {
        requestAndSaveData(query);
    }

    @Override
    public void onItemAtEndLoaded(@NonNull GithubRepoEntry itemAtEnd) {
        requestAndSaveData(query);
    }

    private void requestAndSaveData(String query) {
        if (isRequestInProgress) return;
        isRequestInProgress = true;

        /*mAppExecutors.networkIO().execute(() -> {

        });*/
        mGithubRepoNetworkDataSource.fetchRepos(query,lastPage,NETWORK_PAGE_SIZE);
        lastPage++;
        isRequestInProgress = false;
    }


}
