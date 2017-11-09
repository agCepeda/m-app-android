package com.meisshi.meisshi.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.meisshi.meisshi.R;
import com.meisshi.meisshi.presenter.RegisterPresenter;
import com.meisshi.meisshi.view.IRegisterView;

/**
 * Created by DevAg on 21/08/2017.
 */

public class RegisterFragment extends BaseFragment
    implements IRegisterView {

    EditText mEtEmail;
    EditText mEtPassword;
    EditText mEtName;
    EditText mEtLastName;

    RegisterPresenter mPresenter;

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        View view  = inflater.inflate(R.layout.fragment_register, null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setup();
    }

    @Override
    public void setup() {
        mEtEmail = (EditText) getView().findViewById(R.id.et_email);
        mEtPassword = (EditText) getView().findViewById(R.id.et_password);
        mEtName = (EditText) getView().findViewById(R.id.et_name);
        mEtLastName = (EditText) getView().findViewById(R.id.et_last_name);

        mPresenter = new RegisterPresenter(this);

        mApplicationComponent.inject(mPresenter);

        getView().findViewById(R.id.btn_register)
                .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.signUp();
            }
        });

    }

    @Override
    public void showErrorMessage(int titleRes, int messageRes) {

    }

    @Override
    public void onRegisterWithFacebook() {

    }

    @Override
    public void onRegister() {

    }

    @Override
    public String getEmail() {
        return mEtEmail.getText().toString();
    }

    @Override
    public String getName() {
        return mEtName.getText().toString();
    }

    @Override
    public String getLastName() {
        return mEtLastName.getText().toString();
    }

    @Override
    public String getPassword() {
        return mEtPassword.getText().toString();
    }

    @Override
    public void setEmail(String email) {

    }

    @Override
    public void setPassword(String password) {

    }

    @Override
    public void setName(String name) {

    }

    @Override
    public void setLastName(String lastName) {

    }

    @Override
    public void lockRegister() {

    }

    @Override
    public void unlockRegister() {

    }

    @Override
    public void showMainView() {

    }
}
