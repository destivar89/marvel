package com.gladheim.marvel.characters.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by destivar on 12/07/16.
 */
public class Characters {

    private List<Character> characters;

    public Characters(){
        characters = new ArrayList<>();
    }

    public void add( Character character ) {
        characters.add( character );
    }

    public List<Character> getCharacters() {
        return characters;
    }

}
