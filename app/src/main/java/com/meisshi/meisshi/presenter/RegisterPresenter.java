package com.meisshi.meisshi.presenter;

import android.content.SharedPreferences;
import android.util.Log;

import com.meisshi.meisshi.R;
import com.meisshi.meisshi.model.Session;
import com.meisshi.meisshi.util.Text;
import com.meisshi.meisshi.view.IRegisterView;

import java.util.ArrayList;
import java.util.List;

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
        List<String> errors = validate();
        if (errors.isEmpty()) {
            mView.lockRegister();
            mApi.signUp(
                    mView.getEmail(),
                    mView.getPassword(),
                    mView.getName(),
                    mView.getLastName()
            ).enqueue(new Callback<Session>() {
                @Override
                public void onResponse(Call<Session> call, Response<Session> response) {
                    mView.unlockRegister();
                    if (response.isSuccessful()) {
                        Session session = response.body();

                        SharedPreferences.Editor editor = mSharedPreferences.edit();
                        editor.putString("SESSION_TOKEN", session.getToken());

                        editor.commit();

                        mApplication.setUser(session.getUser());

                        mView.showMainView();
                    } else {
                        mView.showErrorMessage(R.string.register_error_register_title, R.string.register_error_register_message);
                    }
                }

                @Override
                public void onFailure(Call<Session> call, Throwable t) {
                    mView.unlockRegister();
                    mView.showErrorMessage(R.string.register_error_server_title, R.string.register_error_server_message);
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

    private List<String> validate() {
        List<String> errors = new ArrayList<>();

        if (!Text.isEmail(mView.getEmail())) {
            errors.add("The field email is incorrect");
        }

        if ( mView.getPassword().length() < 5) {
            errors.add("The password must contains at least 5 characters");
        }

        return  errors;
    }
}
