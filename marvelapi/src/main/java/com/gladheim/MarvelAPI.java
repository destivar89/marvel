package com.gladheim;

import com.gladheim.characters.CharacterService;
import com.gladheim.characters.Characters;
import com.gladheim.data.MarvelAPICharactersResponse;
import com.gladheim.data.MarvelAPISeriesResponse;
import com.gladheim.series.SerieService;
import com.gladheim.series.Series;
import com.gladheim.utils.CipherUtils;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MarvelAPI implements Series, Characters{

    public static final String ENDPOINT_MARVEL_API = "http://gateway.marvel.com/";
    public static final String APIKEY = "c89270d0e29b9285ba06a21186afd697";
    public static final String SECRETKEY = "03ce0e9e2337eaea36c4b66b43eae9d06e6cd5d8";

    private SerieService seriesService;
    private CharacterService charactersService;

    private int ts = 1;

    public MarvelAPI(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ENDPOINT_MARVEL_API)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        seriesService = retrofit.create(SerieService.class);
        charactersService = retrofit.create(CharacterService.class);

    }

    @Override
    public Call<MarvelAPISeriesResponse> series(int offset) {

        String hash = CipherUtils.md5(ts+SECRETKEY+APIKEY);
        String timestamp = String.valueOf(ts);
        ts++;
        return seriesService.series(String.valueOf(offset), APIKEY, timestamp, hash);

    }

    @Override
    public Call<MarvelAPICharactersResponse> characters(int id, int offset) {

        String hash = CipherUtils.md5(ts+SECRETKEY+APIKEY);
        String timestamp = String.valueOf(ts);
        ts++;
        return charactersService.characters(String.valueOf(id) ,String.valueOf(offset), APIKEY, timestamp, hash);

    }
}
