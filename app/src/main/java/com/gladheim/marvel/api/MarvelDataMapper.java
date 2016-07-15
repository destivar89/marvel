package com.gladheim.marvel.api;

import com.gladheim.data.MarvelAPICharacter;
import com.gladheim.data.MarvelAPICharacterLite;
import com.gladheim.data.MarvelAPICharacters;
import com.gladheim.data.MarvelAPICharactersData;
import com.gladheim.data.MarvelAPICharactersResponse;
import com.gladheim.data.MarvelAPISerie;
import com.gladheim.data.MarvelAPISeriesData;
import com.gladheim.data.MarvelAPISeriesResponse;
import com.gladheim.data.MarvelAPIThumbnail;
import com.gladheim.marvel.characters.model.Character;
import com.gladheim.marvel.characters.model.Characters;
import com.gladheim.marvel.series.model.Serie;
import com.gladheim.marvel.series.model.Series;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by destivar on 17/06/16.
 */
public class MarvelDataMapper {

    public static Series mapSeriesFromMarvelApi( MarvelAPISeriesResponse response ){

        Series series = new Series();
        MarvelAPISeriesData data = (MarvelAPISeriesData) response.getData();

        for (MarvelAPISerie serie : data.getResults() ){
            series.add(mapSerieFromMarvelApi(serie));
        }

        return series;

    }

    private static Serie mapSerieFromMarvelApi( MarvelAPISerie apiSerie ) {

        Serie serie = new Serie();

        serie.setTitle( apiSerie.getTitle() );
        serie.setThumbnail( mapThumbNailFromMarvelApi( apiSerie.getThumbnail() ) );
        serie.setStartYear( apiSerie.getStartYear() );
        serie.setEndYear( apiSerie.getEndYear() );
        serie.setDescription( apiSerie.getDescription() );
        serie.setCharacters( mapCharactersLiteFromMarvelApi( apiSerie.getCharacters() ) );
        serie.setId(apiSerie.getId());

        return serie;

    }

    public static Characters mapCharactersFromMarvelApi( MarvelAPICharactersResponse response ){

        Characters characters = new Characters();
        MarvelAPICharactersData data = (MarvelAPICharactersData) response.getData();

        for (MarvelAPICharacter character : data.getResults() ){
            characters.add(mapCharacterFromMarvelApi(character));
        }

        return characters;

    }

    private static Character mapCharacterFromMarvelApi( MarvelAPICharacter apiCharacter ) {

        Character character = new Character();

        character.setName(apiCharacter.getName());
        character.setDescription(apiCharacter.getDescription());
        character.setThumbnail( mapThumbNailFromMarvelApi( apiCharacter.getThumbnail() ) );

        return character;

    }

    private static List<String> mapCharactersLiteFromMarvelApi(MarvelAPICharacters apiCharacters ) {

        List<String> characters = new ArrayList<>();

        for ( MarvelAPICharacterLite character : apiCharacters.getItems() ) {
            characters.add( character.getName() );
        }

        return characters;
    }

    private static String mapThumbNailFromMarvelApi( MarvelAPIThumbnail thumbnail ) {

        return thumbnail.getPath() + "." + thumbnail.getExtension();

    }

}
