package com.meisshi.meisshi.presenter;

import android.util.Log;

import com.meisshi.meisshi.model.Session;
import com.meisshi.meisshi.view.IRegisterView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by DevAg on 21/08/2017.
 */

public class RegisterPresenter extends BasePresenter {

    private IRegisterView mView;

    public RegisterPresenter(IRegisterView view) {
        this.mView = view;
    }

    public void signUp() {
        Log.d("RegisterPresenter", "Sign Up");
        mView.lockRegister();
        mApi.signUp(
                mView.getEmail(),
                mView.getPassword(),
                mView.getName(),
                mView.getLastName()
        ).enqueue(new Callback<Session>() {
            @Override
            public void onResponse(Call<Session> call, Response<Session> response) {
                if (response.isSuccessful()) {
                    Session session = response.body();

                    mView.showMainView();
                } else {
                    mView.showErrorMessage("");
                }
                mView.unlockRegister();
            }

            @Override
            public void onFailure(Call<Session> call, Throwable t) {
                mView.showErrorMessage("");
                mView.unlockRegister();
            }
        });
    }
}
