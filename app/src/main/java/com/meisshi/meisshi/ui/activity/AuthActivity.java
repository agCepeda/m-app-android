package com.meisshi.meisshi.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.meisshi.meisshi.R;
import com.meisshi.meisshi.ui.fragment.BaseFragment;
import com.meisshi.meisshi.ui.fragment.LoginFragment;
import com.meisshi.meisshi.ui.fragment.RegisterFragment;

public class AuthActivity extends AppCompatActivity {

    public static final String VIEW_OPTION = "VIEW_OPTION";

    public static final int VIEW_OPTION_REGISTER = 1;
    public static final int VIEW_OPTION_LOGIN = 2;
    private RegisterFragment mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        setup();
    }

    private void setup() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.register_title);

        mFragment = new RegisterFragment();
/*
        if (getIntent().getIntExtra(VIEW_OPTION, VIEW_OPTION_LOGIN) == VIEW_OPTION_LOGIN ) {
            fragment = new LoginFragment();
        } else {
        }
        */

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, mFragment)
                .commit();
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mFragment.mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
