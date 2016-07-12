package com.gladheim.marvel.series.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by destivar on 17/06/16.
 */
public class Series {

    private List<Serie> series;

    public Series(){
        series = new ArrayList<>();
    }

    public void add( Serie serie ) {
        series.add( serie );
    }

    public List<Serie> getSeries() {
        return series;
    }
}
