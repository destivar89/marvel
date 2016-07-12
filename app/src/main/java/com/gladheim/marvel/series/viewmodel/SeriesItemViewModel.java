package com.gladheim.marvel.series.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.gladheim.marvel.series.model.Serie;

/**
 * Created by destivar on 20/06/16.
 */
public class SeriesItemViewModel extends BaseObservable {

    private Serie model;

    public SeriesItemViewModel( Serie model ) {
        this.model = model;
    }

    @Bindable
    public String getThumbUrl(){
        return model.getThumbnail();
    }

    @Bindable
    public String getTitle() {
        return model.getTitle();
    }

}
