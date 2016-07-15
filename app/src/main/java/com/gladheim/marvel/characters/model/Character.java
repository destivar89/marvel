package com.gladheim.marvel.characters.model;

/**
 * Created by destivar on 12/07/16.
 */
public class Character {

    private String name;
    private String description;
    private String thumbnail;

    public String getThumbnail() {
        return thumbnail;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

}
