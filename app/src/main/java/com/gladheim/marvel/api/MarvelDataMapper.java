package com.gladheim.marvel.api;

import com.gladheim.data.MarvelAPICharacterLite;
import com.gladheim.data.MarvelAPICharacters;
import com.gladheim.data.MarvelAPIResponse;
import com.gladheim.data.MarvelAPISerie;
import com.gladheim.data.MarvelAPISeriesData;
import com.gladheim.data.MarvelAPIThumbnail;
import com.gladheim.marvel.series.model.Serie;
import com.gladheim.marvel.series.model.Series;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by destivar on 17/06/16.
 */
public class MarvelDataMapper {

    public static Series mapSeriesFromMarvelApi( MarvelAPIResponse response ){

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
        serie.setCharacters( mapCharactersFromMarvelApi( apiSerie.getCharacters() ) );

        return serie;

    }

    private static List<String> mapCharactersFromMarvelApi( MarvelAPICharacters apiCharacters ) {

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
