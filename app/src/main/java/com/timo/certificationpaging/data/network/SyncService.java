package com.timo.certificationpaging.data.network;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

import com.timo.certificationpaging.utilities.InjectorUtils;

public class SyncService extends IntentService {
    private static final String TAG = SyncService.class.getSimpleName();

    public SyncService() {
        super("SyncService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.d(TAG, "Intent service started");
        GithubRepoNetworkDataSource networkDataSource =
                InjectorUtils.provideNetworkDataSource(this.getApplicationContext());

        //networkDataSource.fetchRepos("android",1,20);
    }
}
