package com.meisshi.meisshi.presenter;

import android.content.SharedPreferences;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.meisshi.meisshi.MeisshiApp;
import com.meisshi.meisshi.R;
import com.meisshi.meisshi.model.Session;
import com.meisshi.meisshi.util.Text;
import com.meisshi.meisshi.view.ILoginView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
        List<String> errors = validate();

        if (errors.isEmpty()) {
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

                        updateDeviceToken();
                    } else {
                        mView.showErrorMessage(
                                R.string.login_error_login_title,
                                R.string.login_error_login_message
                        );
                    }
                }

                @Override
                public void onFailure(Call<Session> call, Throwable t) {
                    t.printStackTrace();
                    Log.d("LoginPresenter", t.getMessage(), t);
                    mView.unlockLogin();
                    mView.showErrorMessage(
                            R.string.error_server_title,
                            R.string.error_server_message
                    );
                }
            });
        } else {
            StringBuilder builder = new StringBuilder();
            for (String s: errors) {
                builder.append(s).append("\n");
            }
            mView.showErrorMessage("Error", builder.toString());
        }
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

    private List<String> validate() {
        List<String> errors = new ArrayList<>();

        if (! Text.isEmail(mView.getUsername())) {
            errors.add("The username is not valid.");
        }

        return errors;
    }
}
