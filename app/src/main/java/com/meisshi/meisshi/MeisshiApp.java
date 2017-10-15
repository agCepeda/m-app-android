package com.meisshi.meisshi;

import android.app.Application;

import com.meisshi.meisshi.di.ApplicationComponent;
import com.meisshi.meisshi.di.ApplicationModule;
import com.meisshi.meisshi.di.DaggerApplicationComponent;
import com.meisshi.meisshi.di.NetworkModule;
import com.meisshi.meisshi.model.User;

public class MeisshiApp extends Application {

    private ApplicationComponent mAppComponent;
    private User user;

    @Override public void onCreate() {
        super.onCreate();
        mAppComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .networkModule(new NetworkModule("http://meisshi.com/api4/"))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return this.mAppComponent;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
