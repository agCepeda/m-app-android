package com.meisshi.meisshi.presenter;

import com.meisshi.meisshi.model.Pagination;
import com.meisshi.meisshi.model.User;
import com.meisshi.meisshi.view.ISearchView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by DevAg on 06/09/2017.
 */

public class SearchPresenter extends BasePresenter {

    private ISearchView mView;

    private int mPage;
    private int mSize;
    private String mQuery;

    public SearchPresenter(ISearchView view) {
        this.mView = view;
    }

    public void search() {
        mQuery = mView.getFilter();
        mSize = 20;
        mPage = 1;
        this.loadUsers();
    }

    public void loadUsers() {
        mApi.search(mQuery, mSize, mPage).enqueue(new Callback<Pagination<User>>() {
            @Override
            public void onResponse(Call<Pagination<User>> call, Response<Pagination<User>> response) {
                if (response.isSuccessful()) {
                    mView.addUsers(response.body().getItems());
                }
            }

            @Override
            public void onFailure(Call<Pagination<User>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
