package com.meisshi.meisshi.ui.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.meisshi.meisshi.R;
import com.meisshi.meisshi.presenter.LoginPresenter;
import com.meisshi.meisshi.presenter.SplashPresenter;
import com.meisshi.meisshi.view.ILoginView;
import com.meisshi.meisshi.view.ISplashView;

import java.util.Arrays;

public class SplashActivity extends BaseActivity
    implements ISplashView, ILoginView {

    private SplashPresenter mSplashPresenter;
    private LoginPresenter mLoginPresenter;
    private EditText mEtPassword;
    private EditText mEtUsername;
    private Button mBtnLogin;
    private View mOptionsContainer;
    private ProgressDialog mPdLogin;
    private View mLogoContainer;
    private Button mBtnSignUp;
    private LoginButton mLoginButton;
    private CallbackManager mCallbackManager;

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
    public void onLoginWithFacebook() {

    }

    @Override
    public void onLogin() {

    }

    @Override
    public void showMainView() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);

        finish();
    }

    @Override
    public void unlockLogin() {
        if (mPdLogin != null) {
            mPdLogin.dismiss();
        }
    }

    @Override
    public void showErrorMessage(String s, String s1) {
        new AlertDialog.Builder(this)
                .setTitle(s)
                .setMessage(s1)
                .create()
                .show();
    }

    @Override
    public void lockLogin() {
        mPdLogin = new ProgressDialog(this);
        mPdLogin.setIndeterminate(false);
        mPdLogin.setCancelable(false);
        mPdLogin.setCanceledOnTouchOutside(false);
        mPdLogin.setMessage("Authenticating...");
        mPdLogin.show();
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
    public void setup() {
        mSplashPresenter = new SplashPresenter(this);
        mLoginPresenter = new LoginPresenter(this);
        mApplicationComponent.inject(mSplashPresenter);
        mApplicationComponent.inject(mLoginPresenter);

        mEtUsername = (EditText) findViewById(R.id.et_username);
        mEtPassword = (EditText) findViewById(R.id.et_password);
        mBtnLogin   = (Button) findViewById(R.id.btn_login);
        mBtnSignUp  = (Button) findViewById(R.id.btn_sign_up);

        mOptionsContainer = findViewById(R.id.options_container);
        mLogoContainer = findViewById(R.id.logo_container);

        View.OnClickListener containerListener = new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                closeKeyboard();
            }
        };

        mOptionsContainer.setOnClickListener(containerListener);
        mLogoContainer.setOnClickListener(containerListener);

        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLoginPresenter.login();
            }
        });
        mBtnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showRegisterView();
            }
        });

        mLoginButton = (LoginButton) findViewById(R.id.btn_log_in_facebook);
        mLoginButton.setReadPermissions("email");
        // If using in a fragment
        // mLoginButton.setFragment(this);

        // Other app specific specialization

        // Callback registration

        mCallbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile"));
        LoginManager.getInstance().registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
                return;
            }

            @Override
            public void onCancel() {
                // App code
                return;
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
            }
        });

        mSplashPresenter.checkSession();
    }

    private void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public void showErrorMessage(int titleRes, int messageRes) {
        new AlertDialog.Builder(this)
                .setTitle(titleRes)
                .setMessage(messageRes)
                .create()
                .show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
