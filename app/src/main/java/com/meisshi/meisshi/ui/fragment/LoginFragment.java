package com.meisshi.meisshi.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.meisshi.meisshi.R;
import com.meisshi.meisshi.presenter.LoginPresenter;
import com.meisshi.meisshi.view.ILoginView;

import javax.inject.Inject;

/**
 * Created by DevAg on 21/08/2017.
 */

public class LoginFragment extends BaseFragment
    implements ILoginView {

    @Inject
    LoginPresenter mPresenter;

    private EditText mEtUsername;
    private EditText mEtPassword;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_login, null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setup();
    }

    @Override
    public void setup() {
        mEtUsername = (EditText) getView().findViewById(R.id.et_username);
        mEtPassword = (EditText) getView().findViewById(R.id.et_password);

        getView().findViewById(R.id.btn_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.login();
            }
        });

        mPresenter = new LoginPresenter(this);
        mApplicationComponent.inject(mPresenter);

    }

    @Override
    public void showErrorMessage(String message) {

    }

    @Override
    public void onLoginWithFacebook() {

    }

    @Override
    public void onLogin() {

    }

    @Override
    public void showMainView() {

    }

    @Override
    public String getUsername() {
        return mEtUsername.getText().toString();
    }

    @Override
    public String getPassword() {
        return mEtPassword.getText().toString();
    }

    @Override
    public void setUsername(String username) {
    }

    @Override
    public void setPassword(String password) {
    }

    @Override
    public void lockLogin() {

    }

    @Override
    public void unlockLogin() {

    }
}
