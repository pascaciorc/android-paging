package com.timo.certificationpaging.data.network;

import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.timo.certificationpaging.AppExecutors;
import com.timo.certificationpaging.data.database.GithubRepoEntry;
import com.timo.certificationpaging.data.database.GithubReposResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GithubRepoNetworkDataSource {
    private static final String TAG = GithubRepoNetworkDataSource.class.getSimpleName();

    private static final Object LOCK = new Object();
    private static GithubRepoNetworkDataSource sInstance;
    private final Context mContext;

    private final AppExecutors mAppExecutors;

    private final MutableLiveData<List<GithubRepoEntry>> mDownloadedRepos;


    public GithubRepoNetworkDataSource(Context context, AppExecutors appExecutors) {
        mContext = context;
        mAppExecutors = appExecutors;
        mDownloadedRepos = new MutableLiveData<>();
    }

    public static GithubRepoNetworkDataSource getInstance(Context context, AppExecutors appExecutors) {
        Log.d(TAG, "Starting the network data source");
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new GithubRepoNetworkDataSource(context.getApplicationContext(),appExecutors);
                Log.d(TAG,"Made new network data source");
            }
        }
        return sInstance;
    }

    public MutableLiveData<List<GithubRepoEntry>> getDownloadedRepos() {
        return mDownloadedRepos;
    }

    public void startFetchReposService() {
        Intent intentToFetch = new Intent(mContext, SyncService.class);
        mContext.startService(intentToFetch);
        Log.d(TAG, "Service created");
    }

    public void fetchRepos(String query, int page, int items) {
        Log.d(TAG,"Fetch repos started");
        mAppExecutors.networkIO().execute(() -> {
            /*NetworkUtils.getAPIService().getGithubRepos(query,page,items).enqueue(new Callback<GithubRepoEntry[]>() {
                @Override
                public void onResponse(Call<GithubRepoEntry[]> call, Response<GithubRepoEntry[]> response) {
                    mDownloadedRepos.postValue(response.body());
                }

                @Override
                public void onFailure(Call<GithubRepoEntry[]> call, Throwable t) {
                    Log.e(TAG,t.getLocalizedMessage());
                }
            });*/

            NetworkUtils.getAPIService().getGithubRepos(query,page,items).enqueue(new Callback<GithubReposResponse>() {
                @Override
                public void onResponse(Call<GithubReposResponse> call, Response<GithubReposResponse> response) {
                    mDownloadedRepos.postValue(response.body().getItems());
                }

                @Override
                public void onFailure(Call<GithubReposResponse> call, Throwable t) {
                    Log.e(TAG,t.getLocalizedMessage());
                }
            });
        });
    }
}
