package com.meisshi.meisshi.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MenuItem;

import com.meisshi.meisshi.R;
import com.meisshi.meisshi.model.Notification;
import com.meisshi.meisshi.ui.adapter.NotificationsAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by agustin on 06/11/2017.
 */

public class NotificationsActivity extends BaseActivity {
    private ArrayList<Notification> mListNotifications;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        setup();
    }

    private void setup() {
        mApplicationComponent.inject(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.notifications_title);

        mApi.getNotifications().enqueue(new Callback<ArrayList<Notification>>() {
            @Override
            public void onResponse(Call<ArrayList<Notification>> call, Response<ArrayList<Notification>> response) {
                if (response.isSuccessful()) {
                    setmListNotifications(response.body());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Notification>> call, Throwable t) {
                t.printStackTrace();
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

    public void setmListNotifications(ArrayList<Notification> mListNotifications) {
        this.mListNotifications = mListNotifications;
        NotificationsAdapter adapter = new NotificationsAdapter(mListNotifications, this);
    }
}
