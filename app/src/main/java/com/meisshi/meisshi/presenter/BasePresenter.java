package com.meisshi.meisshi.presenter;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.meisshi.meisshi.MeisshiApp;
import com.meisshi.meisshi.api.MeisshiApi;

import javax.inject.Inject;

/**
 * Created by DevAg on 20/08/2017.
 */

public class BasePresenter {
    @Inject
    MeisshiApi mApi;
    @Inject
    SharedPreferences mSharedPreferences;
    @Inject
    MeisshiApp mApplication;
    @Inject
    Gson mGson;
}
