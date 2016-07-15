package com.gladheim.marvel.characters.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.View;

import com.gladheim.marvel.BR;
import com.gladheim.marvel.characters.adapter.CharactersAdapter;
import com.gladheim.marvel.characters.model.Characters;
import com.gladheim.marvel.global.listener.ModelUpdateListener;

/**
 * Created by destivar on 12/07/16.
 */
public class CharactersViewmodel extends BaseObservable implements ModelUpdateListener<Characters> {

    private CharactersAdapter adapter;
    private boolean loading;
    private boolean lastPage;
    private int currentPage;

    private int progressVisibility = View.VISIBLE;

    public CharactersViewmodel(){
        adapter = new CharactersAdapter();
        currentPage = 0;
    }

    @Override
    public void onModelChanged(Characters model) {
        if ( model == null ) return;
        adapter.addCharacters(model.getCharacters());
        progressVisibility = View.GONE;
        notifyPropertyChanged(BR.progressVisibility);
    }

    @Bindable
    public int getProgressVisibility(){
        return progressVisibility;
    }

    @Override
    public void onModelError(String error) {
        error.equals(null);
    }

    public CharactersAdapter getAdapter() {
        return adapter;
    }

    public boolean isLoading() {
        return loading;
    }

    public void setLoading(boolean loading) {
        this.loading = loading;
    }

    public boolean isLastPage() {
        return lastPage;
    }

    public void setIsLastPage(boolean isLastPage) {
        this.lastPage = isLastPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void increaseCurrentPage() {
        currentPage++;
    }

    public void setOnItemClick(CharactersAdapter.OnItemClickListener onItemClick) {
        adapter.setOnItemClickListener(onItemClick);
    }

}
