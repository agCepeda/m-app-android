package com.meisshi.meisshi.presenter;

import android.content.SharedPreferences;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.meisshi.meisshi.MeisshiApp;
import com.meisshi.meisshi.model.Session;
import com.meisshi.meisshi.model.User;
import com.meisshi.meisshi.services.MyFirebaseInstanceIDService;
import com.meisshi.meisshi.view.ISplashView;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashPresenter extends BasePresenter {

    private ISplashView mView;

    public SplashPresenter(ISplashView splashView) {
        this.mView = splashView;
    }

    public void checkSession() {
        this.mApi.checkSession().enqueue(new Callback<Session>() {
            @Override
            public void onResponse(Call<Session> call, Response<Session> response) {
                if (response.isSuccessful()) {
                    Session session = response.body();
                    mApplication.setSession(session);

                    updateDeviceToken();
                } else {
                    mView.showOptions();
                }
            }

            @Override
            public void onFailure(Call<Session> call, Throwable t) {
                Log.d("SplashPresenter", t.getMessage(), t);
            }
        });
    }

    private void updateDeviceToken() {
        Log.d("FIREBASE Token", "");
        if (FirebaseInstanceId.getInstance() != null && FirebaseInstanceId.getInstance().getToken() != null) {
            Log.d("FIREBASE Token", FirebaseInstanceId.getInstance().getToken());
            mApi.updateDeviceToken(FirebaseInstanceId.getInstance().getToken())
                    .enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            if (response.isSuccessful()) {
                            }
                            mView.showMainView();
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            t.printStackTrace();
                        }
                    });
        } else {
            mView.showMainView();
        }
    }
}
