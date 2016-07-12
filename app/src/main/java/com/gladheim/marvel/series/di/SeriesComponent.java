package com.gladheim.marvel.series.di;

import com.gladheim.marvel.series.view.SeriesActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by destivar on 17/06/16.
 */
@Singleton
@Component(modules = SeriesModule.class)
public interface SeriesComponent {
    void inject(SeriesActivity activity);
}
