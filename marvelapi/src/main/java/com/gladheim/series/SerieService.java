package com.gladheim.series;

import com.gladheim.data.MarvelAPIResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by destivar on 15/06/16.
 */
public interface SerieService {

    @GET("/v1/public/series")
    Call<MarvelAPIResponse> series(@Query("offset") String offset, @Query("apikey") String apikey, @Query("ts") String ts, @Query("hash") String hash );

}
