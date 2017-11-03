package com.meisshi.meisshi.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.meisshi.meisshi.R;
import com.meisshi.meisshi.presenter.SplashPresenter;
import com.meisshi.meisshi.view.ISplashView;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class SplashActivity extends BaseActivity
    implements ISplashView {

    private SplashPresenter mPresenter;
    private EditText mEtPassword;
    private EditText mEtUsername;
    private Button mBtnLogin;
    private View mOptionsContainer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        setup();
    }

    @Override
    public void showLoginView() {
        Log.d("Splash", "Register Click");
        Intent i = new Intent(this, AuthActivity.class);

        Bundle args = new Bundle();
        args.putInt(AuthActivity.VIEW_OPTION, AuthActivity.VIEW_OPTION_LOGIN);

        i.putExtras(args);
        startActivity(i, args);
    }

    @Override
    public void showRegisterView() {
        Log.d("Splash", "Register Click");
        Intent i = new Intent(this, AuthActivity.class);

        Bundle args = new Bundle();
        args.putInt(AuthActivity.VIEW_OPTION, AuthActivity.VIEW_OPTION_REGISTER);

        i.putExtras(args);
        startActivity(i, args);

    }

    @Override
    public void showOptions() {
        mOptionsContainer.setVisibility(View.VISIBLE);
    }

    @Override
    public void showMainView() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);

        finish();
    }

    @Override
    public void unlockLogin() {

    }

    @Override
    public void lockLogin() {

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
    public void setup() {
        mPresenter = new SplashPresenter(this);
        mApplicationComponent.inject(mPresenter);

        // ButterKnife.bind(this);
        /*
        (findViewById(R.id.btn_register))
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showRegisterView();
                    }
                });
        (findViewById(R.id.btn_login))
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showLoginView();
                    }
                });
       */
        mEtUsername = (EditText) findViewById(R.id.et_username);
        mEtPassword = (EditText) findViewById(R.id.et_password);
        mBtnLogin   = (Button) findViewById(R.id.btn_login);
        mOptionsContainer = findViewById(R.id.options_container);

        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.login();
            }
        });

        mPresenter.checkSession();
    }

    @Override
    public void showErrorMessage(String message) {

    }
}
