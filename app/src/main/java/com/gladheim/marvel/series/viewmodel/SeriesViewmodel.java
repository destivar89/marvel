package com.gladheim.marvel.series.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.View;

import com.android.databinding.library.baseAdapters.BR;
import com.gladheim.marvel.global.listener.ModelUpdateListener;
import com.gladheim.marvel.series.adapter.SeriesAdapter;
import com.gladheim.marvel.series.model.Series;

/**
 * Created by destivar on 17/06/16.
 */
public class SeriesViewmodel extends BaseObservable implements ModelUpdateListener<Series>  {

    private SeriesAdapter adapter;
    private boolean loading;
    private boolean lastPage;
    private int currentPage;

    private int progressVisibility = View.VISIBLE;

    public SeriesViewmodel(){
        adapter = new SeriesAdapter();
        currentPage = 0;
    }

    @Override
    public void onModelChanged(Series model) {
        adapter.addSeries(model.getSeries());
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

    public SeriesAdapter getAdapter() {
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
}
