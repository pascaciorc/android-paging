package com.timo.certificationpaging.ui.list;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.timo.certificationpaging.R;
import com.timo.certificationpaging.utilities.InjectorUtils;

public class GithubRepoListActivity extends AppCompatActivity {
    private final String TAG = GithubRepoListActivity.class.getSimpleName();

    private GithubRepoListActivityViewModel mViewModel;
    private RecyclerView mRecyclerView;
    private GithubRepoListAdapter mAdapter;

    private static final String q = "android";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recycler_view);

        LinearLayoutManager linearLayout =
                new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);

        mRecyclerView.setLayoutManager(linearLayout);
        //mRecyclerView.setHasFixedSize(true);

        mAdapter = new GithubRepoListAdapter();

        mRecyclerView.setAdapter(mAdapter);

        GithubRepoListViewModelFactory factory = InjectorUtils.provideGithubRepoListViewModelFactory(this.getApplicationContext(),q);
        mViewModel = ViewModelProviders.of(this,factory).get(GithubRepoListActivityViewModel.class);

        mViewModel.getRepos().observe(this, reposEntries -> {
            if (reposEntries != null && reposEntries.size() != 0) {
                Log.d(TAG,"YATTA!");
                //mAdapter.swapRepos(reposEntries);
                mAdapter.submitList(reposEntries);
            }
        });
    }
}
