package com.meisshi.meisshi.presenter;

import com.meisshi.meisshi.model.User;
import com.meisshi.meisshi.view.IFollowersView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by agustin on 31/10/2017.
 */

public class FollowersPresenter extends BasePresenter {

    private IFollowersView mView;

    public FollowersPresenter(IFollowersView view) {
        this.mView = view;
    }

    public void load() {
        if (mView.isForFollowers()) {
            loadFollowers();
        } else {
            loadFollowed();
        }
    }

    private void loadFollowers() {
        User user = mView.getUser();
        mApi.getFollowers(user.getId()).enqueue(new Callback<ArrayList<User>>() {
            @Override
            public void onResponse(Call<ArrayList<User>> call, Response<ArrayList<User>> response) {
                if (response.isSuccessful()) {
                    mView.setUsers(response.body());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<User>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void loadFollowed() {
        User user = mView.getUser();
        mApi.getFollowed(user.getId()).enqueue(new Callback<ArrayList<User>>() {
            @Override
            public void onResponse(Call<ArrayList<User>> call, Response<ArrayList<User>> response) {
                if (response.isSuccessful()) {
                    mView.setUsers(response.body());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<User>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
