package com.mytime.exercise;


import com.mytime.exercise.network.pojo.Deal;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface MyTimeService {
    String BASE_URL = "http://www.mytime.com";

    @GET("/api/v1/deals.json")
    Observable<List<Deal>> getDeals(@Query("what") String what, @Query("when") String when, @Query("where") String latLon);
}
