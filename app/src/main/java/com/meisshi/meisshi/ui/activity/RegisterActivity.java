package com.meisshi.meisshi.ui.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.meisshi.meisshi.R;
import com.meisshi.meisshi.presenter.RegisterPresenter;
import com.meisshi.meisshi.view.IRegisterView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

/**
 * Created by agustin on 04/12/2017.
 */

public class RegisterActivity extends BaseActivity implements IRegisterView {

    private EditText mEtEmail;
    private EditText mEtPassword;
    private EditText mEtName;
    private EditText mEtLastName;
    private Button mBtnFacebook;
    private View mContainer;
    private ProgressDialog mPdLogin;
    RegisterPresenter mPresenter;
    private CallbackManager mCallbackManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_register);

        setupUI();
        setup();
    }

    private void setupUI() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.register_title);

        mContainer = findViewById(R.id.container);
        mEtEmail = (EditText) findViewById(R.id.et_email);
        mEtPassword = (EditText) findViewById(R.id.et_password);
        mEtName = (EditText) findViewById(R.id.et_name);
        mEtLastName = (EditText) findViewById(R.id.et_last_name);
        mBtnFacebook = (Button) findViewById(R.id.btn_log_in_facebook);

        mBtnFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onRegisterWithFacebook();
            }
        });

        mContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dissmissKeyboard();
            }
        });
        findViewById(R.id.btn_register)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mPresenter.signUp();
                    }
                });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == android.R.id.home) {
            close();
            return true;
        }
        return false;// super.onOptionsItemSelected(menuItem);
    }

    private void close() {
        finish();
    }

    private void dissmissKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public void setup() {
        mPresenter = new RegisterPresenter(this);
        mApplicationComponent.inject(mPresenter);
    }

    @Override
    public void showErrorMessage(int titleRes, int messageRes) {

    }

    @Override
    public void onRegisterWithFacebook() {

        mCallbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                getFacebookProfileData(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                // App code
                return;
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
                return;
            }
        });
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile"));
    }

    private void getFacebookProfileData(AccessToken accessToken) {
        GraphRequest request = GraphRequest.newMeRequest(
                accessToken,
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        try {
                            String email = object.getString("email");
                            String firstName = object.getString("first_name");
                            String lastName = object.getString("last_name");
                            mPresenter.loginFacebook(email, firstName, lastName);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "email,first_name,last_name");
        request.setParameters(parameters);
        request.executeAsync();
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
        mPdLogin = new ProgressDialog(this);
        mPdLogin.setIndeterminate(false);
        mPdLogin.setCancelable(false);
        mPdLogin.setCanceledOnTouchOutside(false);
        mPdLogin.setMessage("Sign up...");
        mPdLogin.show();
    }

    @Override
    public void unlockRegister() {
        if (mPdLogin != null) {
            mPdLogin.dismiss();
        }
    }

    @Override
    public void showMainView() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
