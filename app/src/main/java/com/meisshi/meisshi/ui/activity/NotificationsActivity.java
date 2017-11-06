package com.meisshi.meisshi.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MenuItem;

import com.meisshi.meisshi.R;

/**
 * Created by agustin on 06/11/2017.
 */

public class NotificationsActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        setup();
    }

    private void setup() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.notifications_title);
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
}
