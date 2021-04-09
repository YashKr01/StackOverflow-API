package com.example.stackoverflow.api;

import com.example.stackoverflow.model.MyResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("answers")
    Call<MyResponse> getResponse(@Query("page") int page,
                                 @Query("pagesize") int pageSize,
                                 @Query("site") String site);

}
