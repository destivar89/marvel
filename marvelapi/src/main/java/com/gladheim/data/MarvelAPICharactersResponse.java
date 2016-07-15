package com.gladheim.data;

/**
 * Created by destivar on 13/07/16.
 */
public class MarvelAPICharactersResponse {

    private int code;
    private String status;
    private MarvelAPICharactersData data;

    public MarvelAPICharactersData getData() {
        return data;
    }

}