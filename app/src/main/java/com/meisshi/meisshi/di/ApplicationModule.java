package com.meisshi.meisshi.di;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.meisshi.meisshi.MeisshiApp;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {
    MeisshiApp mApplication;

    public ApplicationModule(MeisshiApp application)
    {
        this.mApplication = application;
    }

    @Provides
    @Singleton
    MeisshiApp providesApplication() {
        return this.mApplication;
    }

    @Provides
    @Singleton
    SharedPreferences providesSharedPreferences(MeisshiApp application) {
        return PreferenceManager
                    .getDefaultSharedPreferences(application);
    }
}
