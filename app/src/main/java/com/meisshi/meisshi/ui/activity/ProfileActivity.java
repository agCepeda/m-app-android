package com.meisshi.meisshi.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.meisshi.meisshi.R;
import com.meisshi.meisshi.model.User;
import com.meisshi.meisshi.ui.fragment.ProfileFragment;

/**
 * Created by DevAg on 07/11/2017.
 */

public class ProfileActivity extends BaseActivity {

    public static final String ARG_USER = "ARG_USER";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        setup();
    }

    private void setup() {
        User user = (User) getIntent().getExtras().getSerializable(ARG_USER);

        ProfileFragment fragment = new ProfileFragment();

        Bundle args = new Bundle();
        args.putSerializable(ProfileFragment.ARG_USER, user);

        fragment.setArguments(args);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();

    }

}
