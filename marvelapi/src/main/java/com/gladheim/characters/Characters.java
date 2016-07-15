package com.gladheim.characters;

import com.gladheim.data.MarvelAPICharactersResponse;

import retrofit2.Call;

/**
 * Created by destivar on 15/06/16.
 */
public interface Characters {

    Call<MarvelAPICharactersResponse> characters( int id, int offset );

}
