package com.gladheim.marvel.characters.di;

import com.gladheim.marvel.characters.viewmodel.CharactersViewmodel;

import dagger.Module;
import dagger.Provides;

/**
 * Created by destivar on 12/07/16.
 */
@Module
public class CharactersModule {

    private CharactersViewmodel viewmodel;

    public CharactersModule() {
        this.viewmodel = new CharactersViewmodel();
    }

    @Provides
    CharactersViewmodel provideChannelsViewModel(){
        return viewmodel;
    }

}
