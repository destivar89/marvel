package com.gladheim.marvel.series.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.gladheim.marvel.R;
import com.gladheim.marvel.api.MarvelApiManager;
import com.gladheim.marvel.databinding.ActivitySeriesBinding;
import com.gladheim.marvel.detail.view.DetailActivity;
import com.gladheim.marvel.series.adapter.SeriesAdapter;
import com.gladheim.marvel.series.di.DaggerSeriesComponent;
import com.gladheim.marvel.series.di.SeriesComponent;
import com.gladheim.marvel.series.di.SeriesModule;
import com.gladheim.marvel.series.model.Serie;
import com.gladheim.marvel.series.viewmodel.SeriesViewmodel;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SeriesActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, SeriesAdapter.OnItemClickListener {

    private static final int PAGE_SIZE = 20;
    private static final String EXTRA_IMAGE = "extra_image";

    @Inject
    SeriesViewmodel viewmodel;

    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;
    @Bind(R.id.swipe_container)
    SwipeRefreshLayout swipeRefreshLayout;

    private SeriesComponent component;
    private LinearLayoutManager layoutManager;

    private RecyclerView.OnScrollListener recyclerViewOnScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            int visibleItemCount = layoutManager.getChildCount();
            int totalItemCount = layoutManager.getItemCount();
            int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

            if (!viewmodel.isLoading() && !viewmodel.isLastPage()) {
                if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                        && firstVisibleItemPosition >= 0
                        && totalItemCount >= PAGE_SIZE) {
                    retrieveSeries();
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_series);

        component().inject(this);
        bindData();
        ButterKnife.bind(this);

        initRecyclerView();
        retrieveSeries();

    }

    private SeriesComponent component(){
        if(component == null){
            component = DaggerSeriesComponent.builder()
                    .seriesModule(new SeriesModule())
                    .build();
        }
        return component;
    }

    private void bindData() {
        ActivitySeriesBinding binding =  DataBindingUtil.setContentView(this, R.layout.activity_series);
        binding.setViewModel(viewmodel);
    }

    private void initRecyclerView() {

        viewmodel.setOnItemClick(this);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(viewmodel.getAdapter());
        recyclerView.addOnScrollListener(recyclerViewOnScrollListener);

    }

    private void retrieveSeries() {

        viewmodel.setLoading(true);
        MarvelApiManager apiManager = new MarvelApiManager();
        apiManager.retrieveSeries( viewmodel.getCurrentPage(), viewmodel );
        viewmodel.increaseCurrentPage();

    }

    @Override
    public void onRefresh() {
        viewmodel.setIsLastPage(false);
        viewmodel.setCurrentPage(0);
        retrieveSeries();
    }

    @Override
    public void onItemClick(View v, int position, Serie serie) {
        goToDetail(serie, v);
    }

    private void goToDetail(Serie serie, View v){
        Intent detailIntent = new Intent( this, DetailActivity.class );
        detailIntent.putExtra(getString(R.string.serializable_serie), serie);

        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation( this, v.findViewById(R.id.thumbnail), EXTRA_IMAGE );
        ActivityCompat.startActivity( this, detailIntent, options.toBundle() );
    }

}
