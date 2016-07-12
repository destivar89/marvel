package com.gladheim.characters;

import com.gladheim.data.MarvelAPICharactersData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


/**
 * Created by destivar on 15/06/16.
 */
public interface CharacterService {

    @GET("/v1/public/characters/{id}")
    Call<MarvelAPICharactersData> character(@Path("id") String id, @Query("apikey") String apikey );

}
