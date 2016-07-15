package com.gladheim.marvel.api;

import com.gladheim.MarvelAPI;
import com.gladheim.data.MarvelAPICharactersResponse;
import com.gladheim.data.MarvelAPISeriesResponse;
import com.gladheim.marvel.characters.model.Characters;
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

        Call<MarvelAPISeriesResponse> call = marvelAPI.series( offset );
        call.enqueue(new Callback<MarvelAPISeriesResponse>() {
            @Override
            public void onResponse(Call<MarvelAPISeriesResponse> call, Response<MarvelAPISeriesResponse> response) {
                seriesModelUpdateListener.onModelChanged( MarvelDataMapper.mapSeriesFromMarvelApi( response.body() ) );
            }

            @Override
            public void onFailure(Call<MarvelAPISeriesResponse> call, Throwable t) {
                seriesModelUpdateListener.onModelError( t.getMessage() );
            }
        });

    }

    public void retrieveCharacters(int id, int offset, final ModelUpdateListener<Characters> charactersModelUpdateListener ){

        Call<MarvelAPICharactersResponse> call = marvelAPI.characters( id, offset );
        call.enqueue(new Callback<MarvelAPICharactersResponse>() {
            @Override
            public void onResponse(Call<MarvelAPICharactersResponse> call, Response<MarvelAPICharactersResponse> response) {
                charactersModelUpdateListener.onModelChanged( MarvelDataMapper.mapCharactersFromMarvelApi( response.body() ) );
            }

            @Override
            public void onFailure(Call<MarvelAPICharactersResponse> call, Throwable t) {
                charactersModelUpdateListener.onModelError( t.getMessage() );
            }
        });

    }

}
