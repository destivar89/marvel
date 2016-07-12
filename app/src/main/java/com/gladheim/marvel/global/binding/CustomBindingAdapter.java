package com.gladheim.marvel.global.binding;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by destivar on 20/06/16.
 */
public class CustomBindingAdapter {

    @BindingAdapter("bind:imageUrl")
    public static void loadImage(ImageView imageView, String thumbUrl){
        if ( thumbUrl == null || thumbUrl.isEmpty() ) return;
        Picasso.with(imageView.getContext())
                .load( thumbUrl )
                .noFade()
                .into(imageView);
    }

}
