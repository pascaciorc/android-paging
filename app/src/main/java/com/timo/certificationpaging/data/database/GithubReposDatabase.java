package com.timo.certificationpaging.data.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {GithubRepoEntry.class}, version = 1)
public abstract class GithubReposDatabase extends RoomDatabase {
    private final static String DATABASE_NAME = "repos_db";

    private static final Object LOCK = new Object();
    private static volatile GithubReposDatabase sInstance;

    public static GithubReposDatabase getInstance(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                if (sInstance == null) {
                    sInstance = Room.databaseBuilder(context.getApplicationContext(),
                            GithubReposDatabase.class,
                            DATABASE_NAME).build();
                }
            }
        }
        return sInstance;
    }

    public abstract GithubRepoDao githubRepoDao();
}
