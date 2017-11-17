package com.meisshi.meisshi.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MenuItem;
import android.widget.ListView;

import com.meisshi.meisshi.R;
import com.meisshi.meisshi.model.User;
import com.meisshi.meisshi.presenter.FollowersPresenter;
import com.meisshi.meisshi.ui.adapter.FollowersAdapter;
import com.meisshi.meisshi.view.IFollowersView;

import java.util.ArrayList;

/**
 * Created by DevAg on 01/11/2017.
 */

public class FollowersActivity extends BaseActivity
    implements IFollowersView {

    public static final String OPTION_FOLLOW_TYPE = "FOLLOW_TYPE";
    public static final String FOLLOW_TYPE_FOLLOWER = "FOLLOW_TYPE_FOLLOWER";
    public static final String FOLLOW_TYPE_FOLLOWED = "FOLLOW_TYPE_FOLLOWED";
    public static final String OPTION_USER = "USER";

    private ListView mLvUsers;
    private FollowersPresenter mPresenter;
    private boolean isForFollowers;
    private User mUser;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_followers);
        mLvUsers = (ListView) findViewById(R.id.lvFollowers);
        setup();
    }

    @Override
    public void setup() {
        mPresenter = new FollowersPresenter(this);
        mApplicationComponent.inject(mPresenter);

        Bundle args = getIntent().getExtras();

        isForFollowers = args.getString(OPTION_FOLLOW_TYPE, FOLLOW_TYPE_FOLLOWER)
                .equals(FOLLOW_TYPE_FOLLOWER);

        mUser = (User) args.getSerializable(OPTION_USER);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(
                isForFollowers ? R.string.followers_title : R.string.followed_title
        );

        mPresenter.load();
    }

    @Override
    public void showErrorMessage(int titleRes, int messageRes) {

    }

    @Override
    public void setUsers(ArrayList<User> listUsers) {
        FollowersAdapter adapter = new FollowersAdapter(listUsers, this);
        mLvUsers.setAdapter(adapter);
    }

    @Override
    public boolean isForFollowers() {
        return isForFollowers;
    }

    @Override
    public User getUser() {
        return mUser;
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
