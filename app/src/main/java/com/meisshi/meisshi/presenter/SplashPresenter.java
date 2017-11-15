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
        this.mApi.checkSession().enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    User user = response.body();
                    mApplication.setUser(user);

                    updateDeviceToken();
                } else {
                    mView.showOptions();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                //mSplas
                //
                //
                // hView.showError(R.string.app_name);
                Log.d("SplashPresenter", t.getMessage(), t);
            }
        });
    }


    public void login() {
        mView.lockLogin();
        mApi.login(
                mView.getUsername(),
                mView.getPassword()
        ).enqueue(new Callback<Session>() {
            @Override
            public void onResponse(Call<Session> call, Response<Session> response) {
                mView.unlockLogin();
                if (response.isSuccessful()) {
                    Session session = response.body();

                    SharedPreferences.Editor editor = mSharedPreferences.edit();
                    editor.putString("SESSION_TOKEN", session.getToken());
                    editor.commit();

                    updateDeviceToken();
                } else {

                    try {
                        String error = response.errorBody().string();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<Session> call, Throwable t) {
                mView.unlockLogin();
                t.printStackTrace();
                Log.d("LoginPresenter", t.getMessage(), t);
            }
        });
    }

    private void updateDeviceToken() {
        if (FirebaseInstanceId.getInstance() != null && FirebaseInstanceId.getInstance().getToken() != null) {
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
