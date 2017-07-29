package com.example.okhttptest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by crowd on 2017/7/29.
 */

public interface GaeApi {
    @GET("content")
    Call<List<TodayMovie>> getMovieList(@Query("type") String type);
}
