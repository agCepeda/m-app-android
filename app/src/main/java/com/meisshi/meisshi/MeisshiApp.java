package com.meisshi.meisshi;

import android.app.Application;

import com.meisshi.meisshi.di.ApplicationComponent;
import com.meisshi.meisshi.di.ApplicationModule;
import com.meisshi.meisshi.di .DaggerApplicationComponent;
import com.meisshi.meisshi.di.NetworkModule;
import com.meisshi.meisshi.model.Session;
import com.meisshi.meisshi.model.User;

public class MeisshiApp extends Application {

    //public static final String MEISSHI_API_END_POINT = "http://192.168.199.2/m-app-api/";
    public static final String MEISSHI_API_END_POINT = "http://meisshi.com/api_v2/";
    public static final String PREFERENCE_DEVICE_TOKEN = "DEVICE_TOKEN";

    private ApplicationComponent mAppComponent;
    private Session session;

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

    public User getUser() {
        return session != null ? session.getUser() : null;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Session getSession()
    {
        return this.session;
    }
}
