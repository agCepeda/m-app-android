package com.meisshi.meisshi.ui.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.meisshi.meisshi.MeisshiApp;
import com.meisshi.meisshi.api.MeisshiApi;
import com.meisshi.meisshi.di.ApplicationComponent;

import javax.inject.Inject;

public abstract class BaseActivity extends AppCompatActivity {

    protected ApplicationComponent mApplicationComponent;
    protected MeisshiApp mApplication;

    @Inject
    MeisshiApi mApi;
    @Inject
    SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApplication = (MeisshiApp) getApplication();
        mApplicationComponent = mApplication.getApplicationComponent();
    }
}
