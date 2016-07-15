package com.gladheim.marvel.characters.di;

import com.gladheim.marvel.characters.view.CharactersFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by destivar on 12/07/16.
 */
@Singleton
@Component(modules = CharactersModule.class)
public interface CharactersComponent {
    void inject(CharactersFragment fragment);
}
