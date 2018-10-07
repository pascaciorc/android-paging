package com.timo.certificationpaging.data;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;
import android.content.Intent;
import android.util.Log;

import com.timo.certificationpaging.AppExecutors;
import com.timo.certificationpaging.data.database.GithubRepoDao;
import com.timo.certificationpaging.data.database.GithubRepoEntry;
import com.timo.certificationpaging.data.network.GithubRepoNetworkDataSource;

import java.util.List;

public class GithubRepoRepository {
    private static final String TAG = GithubRepoRepository.class.getSimpleName();

    private static final Object LOCK = new Object();
    private static GithubRepoRepository sInstance;
    private final GithubRepoDao mGithubRepoDao;
    private final GithubRepoNetworkDataSource mGithubRepoNetworkDataSource;
    private final AppExecutors mAppExecutors;
    private boolean mInitialized = false;

    public GithubRepoRepository(GithubRepoDao githubRepoDao, GithubRepoNetworkDataSource githubRepoNetworkDataSource, AppExecutors appExecutors) {
        mGithubRepoDao = githubRepoDao;
        mGithubRepoNetworkDataSource = githubRepoNetworkDataSource;
        mAppExecutors = appExecutors;

        MutableLiveData<List<GithubRepoEntry>> networkData =
                mGithubRepoNetworkDataSource.getDownloadedRepos();

        networkData.observeForever(newRepoList -> {
            mAppExecutors.diskIO().execute(() -> {
                mGithubRepoDao.bulkInsert(newRepoList);
            });
        });
    }

    public synchronized static GithubRepoRepository getInstance(GithubRepoDao githubRepoDao, GithubRepoNetworkDataSource githubRepoNetworkDataSource, AppExecutors appExecutors) {
        Log.d(TAG,"Getting repository");
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new GithubRepoRepository(githubRepoDao,githubRepoNetworkDataSource,appExecutors);
                Log.d(TAG, "Made new repository");
            }
        }
        return sInstance;
    }

    public void startFetchReposService() {
        mGithubRepoNetworkDataSource.startFetchReposService();
    }

    private synchronized void initializeData(String query) {

        // Only perform initialization once per app lifetime. If initialization has already been
        // performed, we have nothing to do in this method.
        if (mInitialized) return;
        mInitialized = true;

        /*mAppExecutors.diskIO().execute(() -> {
                startFetchReposService();
        });*/

        //RepoBoundaryCallback boundaryCallback = new RepoBoundaryCallback(query);

        //LiveData
    }

    //public LiveData<List<GithubRepoEntry>> getRepos() {
    /*public DataSource.Factory<Integer,GithubRepoEntry> getRepos(String query) {
        //initializeData(query);
        RepoBoundaryCallback boundaryCallback = new RepoBoundaryCallback(query);
        return mGithubRepoDao.getGithubRepos(query);
    }*/

    public LiveData<PagedList<GithubRepoEntry>> search(String q) {
        DataSource.Factory<Integer,GithubRepoEntry> dataSource = mGithubRepoDao.getGithubRepos(/*q*/);
        RepoBoundaryCallback callback = new RepoBoundaryCallback(q,mGithubRepoDao,mGithubRepoNetworkDataSource,mAppExecutors);

         return new LivePagedListBuilder<>(dataSource,20)
                .setBoundaryCallback(callback)
                .build();
    }
}
