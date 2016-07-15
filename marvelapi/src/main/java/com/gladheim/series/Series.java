package com.gladheim.series;

import com.gladheim.data.MarvelAPISeriesResponse;

import retrofit2.Call;

/**
 * Created by destivar on 15/06/16.
 */
public interface Series {

    Call<MarvelAPISeriesResponse> series( int offset );

}
