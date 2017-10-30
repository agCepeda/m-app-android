package com.meisshi.meisshi.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.meisshi.meisshi.MeisshiApp;
import com.meisshi.meisshi.di.ApplicationComponent;

public abstract class BaseActivity extends AppCompatActivity {

    protected ApplicationComponent mApplicationComponent;
    protected MeisshiApp mApplication;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApplication = (MeisshiApp) getApplication();
        mApplicationComponent = mApplication.getApplicationComponent();
    }
}
