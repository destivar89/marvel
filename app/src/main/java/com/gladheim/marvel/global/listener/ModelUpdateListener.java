package com.gladheim.marvel.global.listener;

/**
 * Created by destivar on 17/06/16.
 */
public interface ModelUpdateListener <T>{

    void onModelChanged( T model );
    void onModelError( String error );

}
