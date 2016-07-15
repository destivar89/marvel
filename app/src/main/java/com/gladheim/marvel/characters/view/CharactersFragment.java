package com.gladheim.marvel.characters.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gladheim.marvel.R;
import com.gladheim.marvel.api.MarvelApiManager;
import com.gladheim.marvel.characters.di.CharactersComponent;
import com.gladheim.marvel.characters.di.CharactersModule;
import com.gladheim.marvel.characters.di.DaggerCharactersComponent;
import com.gladheim.marvel.characters.viewmodel.CharactersViewmodel;
import com.gladheim.marvel.databinding.FragmentCharactersBinding;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * Created by destivar on 12/07/16.
 */
public class CharactersFragment extends Fragment {

    @Inject
    CharactersViewmodel viewmodel;

    private CharactersComponent component;
    private int id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_characters, container, false);

        component().inject(this);
        bindData(rootView);
        ButterKnife.bind(this, rootView);

        retrieveCharacters();

        return rootView;

    }

    private void bindData(View view) {
        FragmentCharactersBinding binding =  DataBindingUtil.bind(view);
        binding.setViewModel(viewmodel);
    }

    private CharactersComponent component(){
        if(component == null){
            component = DaggerCharactersComponent.builder()
                    .charactersModule(new CharactersModule())
                    .build();
        }
        return component;
    }

    public void retrieveCharacters(){

        viewmodel.setLoading(true);
        MarvelApiManager apiManager = new MarvelApiManager();
        apiManager.retrieveCharacters( id, viewmodel.getCurrentPage(), viewmodel );
        viewmodel.increaseCurrentPage();

    }

    public void setId(int id) {
        this.id = id;
    }
}
