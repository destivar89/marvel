package com.gladheim.marvel.detail.di;

import com.gladheim.marvel.detail.view.DetailActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by destivar on 12/07/16.
 */
@Singleton
@Component(modules = DetailModule.class)
public interface DetailComponent {
    void inject(DetailActivity activity);
}