package com.gladheim.data;

/**
 * Created by destivar on 15/06/16.
 */
public class MarvelAPICharacter {

    private String name;
    private String description;
    private MarvelAPIThumbnail thumbnail;
    private MarvelAPIComics comics;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public MarvelAPIThumbnail getThumbnail() {
        return thumbnail;
    }
}
