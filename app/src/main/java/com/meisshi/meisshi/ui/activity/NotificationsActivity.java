package com.meisshi.meisshi.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MenuItem;
import android.widget.ListView;

import com.meisshi.meisshi.R;
import com.meisshi.meisshi.model.Notification;
import com.meisshi.meisshi.model.Pagination;
import com.meisshi.meisshi.ui.adapter.NotificationsAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by agustin on 06/11/2017.
 */

public class NotificationsActivity extends BaseActivity {
    private List<Notification> mListNotifications;
    private ListView mLvNotifications;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        mLvNotifications = (ListView) findViewById(R.id.lv_notifications);

        setup();
    }

    private void setup() {
        mApplicationComponent.inject(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.notifications_title);

        mApi.getNotifications().enqueue(new Callback<Pagination<Notification>>() {
            @Override
            public void onResponse(Call<Pagination<Notification>> call, Response<Pagination<Notification>> response) {
                if (response.isSuccessful()) {
                    setmListNotifications(response.body().getItems());
                }
            }

            @Override
            public void onFailure(Call<Pagination<Notification>> call, Throwable t) {
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

    public void setmListNotifications(Notification[] listNotifications) {
        this.mListNotifications = Arrays.asList(listNotifications);
        NotificationsAdapter adapter = new NotificationsAdapter(mListNotifications, this);
        mLvNotifications.setAdapter(adapter);
    }
}
