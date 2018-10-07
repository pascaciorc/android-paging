package com.timo.certificationpaging.data.network;

import com.timo.certificationpaging.data.database.GithubRepoEntry;
import com.timo.certificationpaging.data.database.GithubReposResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("search/repositories?sort=stars")
    Call<GithubReposResponse> getGithubRepos(@Query("q") String query,
                                             @Query("page") int page,
                                             @Query("per_page") int itemsPerPage);
}
