package com.meisshi.meisshi.ui.fragment;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.share.Sharer;
import com.meisshi.meisshi.R;
import com.meisshi.meisshi.presenter.RegisterPresenter;
import com.meisshi.meisshi.ui.activity.MainActivity;
import com.meisshi.meisshi.view.IRegisterView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import static com.facebook.FacebookSdk.getApplicationContext;

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
    private ProgressDialog mPdLogin;
    private View mContainer;
    private Button mBtnFacebook;
    public CallbackManager mCallbackManager;

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        View view  = inflater.inflate(R.layout.fragment_register, null);
        mContainer = view.findViewById(R.id.container);
        mEtEmail = (EditText) view.findViewById(R.id.et_email);
        mEtPassword = (EditText) view.findViewById(R.id.et_password);
        mEtName = (EditText) view.findViewById(R.id.et_name);
        mEtLastName = (EditText) view.findViewById(R.id.et_last_name);
        mBtnFacebook = (Button) view.findViewById(R.id.btn_log_in_facebook);

        mBtnFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginWithFacebook();
            }
        });

        mContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dissmissKeyboard();
            }
        });
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setup();
    }

    @Override
    public void setup() {

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
        mPdLogin = new ProgressDialog(getActivity());
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
        Intent i = new Intent(getContext(), MainActivity.class);
        startActivity(i);

        getActivity().finish();
    }

    @Override
    public void showErrorMessage(String s, String s1) {
        new AlertDialog.Builder(getActivity())
                .setTitle(s)
                .setMessage(s1)
                .create()
                .show();
    }

    private void dissmissKeyboard() {
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }


    public void loginWithFacebook()
    {
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

}
