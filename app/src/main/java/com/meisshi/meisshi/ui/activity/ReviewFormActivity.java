package com.meisshi.meisshi.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuItem;

import com.meisshi.meisshi.R;
import com.meisshi.meisshi.model.User;

/**
 * Created by agustin on 06/11/2017.
 */

public class ReviewFormActivity extends BaseActivity {

    public static final String ARG_USER = "ARG_USER";
    private User mUser;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_form);

        mUser = (User) getIntent().getExtras().getSerializable(ARG_USER);

        setup();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.review_form_menu, menu);
        return true;
    }

    private void setup() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.review_form_title);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == android.R.id.home) {
            close();
            return true;
        }
        if (menuItem.getItemId() == R.id.action_save) {
            save();
            return true;
        }
        return false;// super.onOptionsItemSelected(menuItem);
    }

    private void save() {

    }

    private void close() {
        finish();
    }
}
