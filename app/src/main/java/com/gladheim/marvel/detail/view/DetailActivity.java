package com.gladheim.marvel.detail.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.gladheim.marvel.R;
import com.gladheim.marvel.characters.view.CharactersFragment;
import com.gladheim.marvel.databinding.ActivityDetailBinding;
import com.gladheim.marvel.detail.di.DaggerDetailComponent;
import com.gladheim.marvel.detail.di.DetailComponent;
import com.gladheim.marvel.detail.di.DetailModule;
import com.gladheim.marvel.detail.viewmodel.DetailViewmodel;
import com.gladheim.marvel.series.model.Serie;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * Created by destivar on 12/07/16.
 */
public class DetailActivity extends AppCompatActivity {

    @Inject
    DetailViewmodel viewmodel;

    private DetailComponent component;

    private Serie serie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        serie = (Serie) getIntent().getSerializableExtra(getString(R.string.serializable_serie));

        component().inject(this);
        bindData();
        ButterKnife.bind(this);

        initCharacterts();

    }

    private void initCharacterts() {

        CharactersFragment fragment = new CharactersFragment();
        fragment.setId(serie.getId());

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content, fragment, "0" ).commit();

    }

    private DetailComponent component(){
        if(component == null){
            component = DaggerDetailComponent.builder()
                    .detailModule(new DetailModule(serie))
                    .build();
        }
        return component;
    }

    private void bindData() {
        ActivityDetailBinding binding =  DataBindingUtil.setContentView(this, R.layout.activity_detail);
        binding.setViewModel(viewmodel);
    }

}
