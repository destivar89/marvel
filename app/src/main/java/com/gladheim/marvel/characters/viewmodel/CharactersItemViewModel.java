package com.gladheim.marvel.characters.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.gladheim.marvel.characters.model.Character;


/**
 * Created by destivar on 12/07/16.
 */
public class CharactersItemViewModel extends BaseObservable {

    private Character model;

    public CharactersItemViewModel(Character model){
        this.model = model;
    }

    @Bindable
    public String getThumbUrl(){
        return model.getThumbnail();
    }
}
