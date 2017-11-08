package com.meisshi.meisshi.presenter;

import android.content.SharedPreferences;
import android.util.Log;

import com.meisshi.meisshi.model.Session;
import com.meisshi.meisshi.view.ILoginView;

import java.io.IOException;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter extends BasePresenter {

    private ILoginView mView;

    public LoginPresenter(ILoginView view) {
        this.mView = view;
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

                    mApplication.setUser(session.getUser());

                    mView.showMainView();
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
}
