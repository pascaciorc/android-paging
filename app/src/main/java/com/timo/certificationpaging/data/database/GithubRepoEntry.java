package com.timo.certificationpaging.data.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "repos")
public class GithubRepoEntry {
    @PrimaryKey @SerializedName("id") @Expose private long id;
    @SerializedName("name") @Expose private String name;
    @SerializedName("full_name") @Expose private String fullName;
    @SerializedName("description") @Expose private String description;
    @SerializedName("html_url") @Expose private String url;
    @SerializedName("stargazers_count") @Expose private int stars;
    @SerializedName("forks_count") @Expose private int forks;
    @SerializedName("language") @Expose private String language;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public int getForks() {
        return forks;
    }

    public void setForks(int forks) {
        this.forks = forks;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
