package com.timo.certificationpaging.utilities;

import android.content.Context;

import com.timo.certificationpaging.AppExecutors;
import com.timo.certificationpaging.data.GithubRepoRepository;
import com.timo.certificationpaging.data.database.GithubReposDatabase;
import com.timo.certificationpaging.data.network.GithubRepoNetworkDataSource;
import com.timo.certificationpaging.ui.list.GithubRepoListViewModelFactory;

public class InjectorUtils {
    public static GithubRepoRepository provideRepository(Context context) {
        GithubReposDatabase database = GithubReposDatabase.getInstance(context);
        AppExecutors appExecutors = AppExecutors.getInstance();
        GithubRepoNetworkDataSource networkDataSource =
                GithubRepoNetworkDataSource.getInstance(context.getApplicationContext(),appExecutors);
        return GithubRepoRepository.getInstance(database.githubRepoDao(),networkDataSource,appExecutors);
    }

    public static GithubRepoNetworkDataSource provideNetworkDataSource(Context context) {
        provideRepository(context);
        AppExecutors appExecutors = AppExecutors.getInstance();
        return GithubRepoNetworkDataSource.getInstance(context,appExecutors);
    }

    public static GithubRepoListViewModelFactory provideGithubRepoListViewModelFactory(Context context, String query) {
        GithubRepoRepository repository = provideRepository(context);
        return new GithubRepoListViewModelFactory(repository, query);
    }
}
