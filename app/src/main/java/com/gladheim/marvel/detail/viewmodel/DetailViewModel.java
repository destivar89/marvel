package com.gladheim.marvel.detail.viewmodel;

import android.databinding.BaseObservable;

import com.gladheim.marvel.series.model.Serie;

/**
 * Created by destivar on 12/07/16.
 */
public class DetailViewmodel extends BaseObservable{

    private Serie model;

    public DetailViewmodel(Serie serie) {
        model = serie;
    }
}
