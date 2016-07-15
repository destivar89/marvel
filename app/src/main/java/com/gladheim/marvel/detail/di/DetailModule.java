package com.gladheim.marvel.detail.di;

import com.gladheim.marvel.detail.viewmodel.DetailViewmodel;
import com.gladheim.marvel.series.model.Serie;

import dagger.Module;
import dagger.Provides;

/**
 * Created by destivar on 12/07/16.
 */
@Module
public class DetailModule {

    private DetailViewmodel viewmodel;

    public DetailModule(Serie serie) {
        this.viewmodel = new DetailViewmodel(serie);
    }

    @Provides
    DetailViewmodel provideChannelsViewModel(){
        return viewmodel;
    }

}



