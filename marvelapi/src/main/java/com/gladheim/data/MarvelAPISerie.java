package com.gladheim.data;

/**
 * Created by destivar on 15/06/16.
 */
public class MarvelAPISerie {

    private String title;
    private String description;
    private int startYear;
    private int endYear;
    private String type;
    private MarvelAPIThumbnail thumbnail;
    private MarvelAPICharacters characters;
    private int id;

    public String getTitle() {
        return title;
    }

    public MarvelAPIThumbnail getThumbnail() {
        return thumbnail;
    }

    public int getStartYear() {
        return startYear;
    }

    public int getEndYear() {
        return endYear;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public MarvelAPICharacters getCharacters() {
        return characters;
    }

    public int getId() {
        return id;
    }
}
