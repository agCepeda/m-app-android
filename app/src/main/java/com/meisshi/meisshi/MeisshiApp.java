package com.meisshi.meisshi;

import android.app.Application;

import com.meisshi.meisshi.di.ApplicationComponent;
import com.meisshi.meisshi.di.ApplicationModule;
import com.meisshi.meisshi.di .DaggerApplicationComponent;
import com.meisshi.meisshi.di.NetworkModule;
import com.meisshi.meisshi.model.User;

public class MeisshiApp extends Application {

    //public static final String MEISSHI_API_END_POINT = "http://localhost/MeisshiApi/";
    public static final String MEISSHI_API_END_POINT = "http://meisshi.com/api4/";
    public static final String PREFERENCE_DEVICE_TOKEN = "DEVICE_TOKEN";

    private ApplicationComponent mAppComponent;
    private User user;

    @Override public void onCreate() {
        super.onCreate();
        mAppComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .networkModule(new NetworkModule(MEISSHI_API_END_POINT))
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
