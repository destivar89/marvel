package com.gladheim.marvel.api;

import com.gladheim.MarvelAPI;
import com.gladheim.data.MarvelAPIResponse;
import com.gladheim.marvel.global.listener.ModelUpdateListener;
import com.gladheim.marvel.series.model.Series;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by destivar on 17/06/16.
 */
public class MarvelApiManager {

    private MarvelAPI marvelAPI;

    public MarvelApiManager(){
        marvelAPI = new MarvelAPI();
    }

    public void retrieveSeries(int offset, final ModelUpdateListener<Series> seriesModelUpdateListener ){

        Call<MarvelAPIResponse> call = marvelAPI.series( offset );
        call.enqueue(new Callback<MarvelAPIResponse>() {
            @Override
            public void onResponse(Call<MarvelAPIResponse> call, Response<MarvelAPIResponse> response) {
                seriesModelUpdateListener.onModelChanged( MarvelDataMapper.mapSeriesFromMarvelApi( response.body() ) );
            }

            @Override
            public void onFailure(Call<MarvelAPIResponse> call, Throwable t) {
                seriesModelUpdateListener.onModelError( t.getMessage() );
            }
        });

    }

}
