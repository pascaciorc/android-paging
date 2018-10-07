package com.timo.certificationpaging.data.network;

public class NetworkUtils {
    private static final String BASE_URL = "https://api.github.com/";

    public static ApiService getAPIService() {
        return RetrofitClient.getClient(BASE_URL).create(ApiService.class);
    }
}
