package com.gladheim.marvel.series.di;

import com.gladheim.marvel.series.viewmodel.SeriesViewmodel;

import dagger.Module;
import dagger.Provides;

/**
 * Created by destivar on 17/06/16.
 */
@Module
public class SeriesModule {

    private SeriesViewmodel viewmodel;

    public SeriesModule() {
        this.viewmodel = new SeriesViewmodel();
    }

    @Provides
    SeriesViewmodel provideChannelsViewModel(){
        return viewmodel;
    }

}
