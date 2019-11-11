package com.wave.mvpexample.root;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
/*
* keep track of dependencies, create module for every feature we build
*
* */
@Module
public class AppModule {

    private Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    /*
    * singleton signal instance should only create once in app
    * provides: part of dependency list
    * */
    @Provides
    @Singleton
    public Context provideContext() {
        return application;
    }


}
