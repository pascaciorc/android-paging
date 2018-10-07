package com.timo.certificationpaging.data.database;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GithubReposResponse {
    @SerializedName("total_count") @Expose private int total;
    @SerializedName("items") @Expose private List<GithubRepoEntry> items;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<GithubRepoEntry> getItems() {
        return items;
    }

    public void setItems(List<GithubRepoEntry> items) {
        this.items = items;
    }
}
