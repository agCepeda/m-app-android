package com.meisshi.meisshi.presenter;

import android.util.Log;

import com.meisshi.meisshi.model.User;
import com.meisshi.meisshi.view.ISplashView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashPresenter extends BasePresenter {

    private ISplashView mSplashView;

    public SplashPresenter(ISplashView splashView) {
        this.mSplashView = splashView;
    }

    public void checkSession() {
        this.mApi.checkSession().enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    User user = response.body();
                    mApplication.setUser(user);
                    mSplashView.showMainView();
                } else {
                    mSplashView.showOptions();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                //mSplashView.showError(R.string.app_name);
                Log.d("SplashPresenter", t.getMessage(), t);
            }
        });
    }
}
