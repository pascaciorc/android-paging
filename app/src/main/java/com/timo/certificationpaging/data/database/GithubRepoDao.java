package com.timo.certificationpaging.data.database;

import android.arch.lifecycle.LiveData;
import android.arch.paging.DataSource;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface GithubRepoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void bulkInsert(List<GithubRepoEntry> entries);

    @Query("SELECT * FROM repos")
    //@Query("SELECT * FROM repos WHERE name LIKE :queryString ORDER BY stars DESC, name ASC")
    //LiveData<List<GithubRepoEntry>> getGithubRepos();
    DataSource.Factory<Integer,GithubRepoEntry> getGithubRepos(/*String queryString*/);

    @Query("SELECT * FROM repos WHERE id = :id")
    LiveData<GithubRepoEntry> getGithubRepo(long id);

    @Query("SELECT COUNT(id) FROM repos")
    int getGithubReposCount();
}
