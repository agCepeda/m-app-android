package com.meisshi.meisshi.presenter;

import android.util.Log;

import com.meisshi.meisshi.model.User;
import com.meisshi.meisshi.view.ISearchView;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by DevAg on 06/09/2017.
 */

public class SearchPresenter extends BasePresenter {

    private ISearchView mView;

    public SearchPresenter(ISearchView view) {
        this.mView = view;
    }

    public void search() {
        String q = mView.getFilter();
        /*
        mApi.search(q).enqueue(new Callback<ArrayList<User>>() {
            @Override
            public void onResponse(Call<ArrayList<User>> call, Response<ArrayList<User>> response) {
                if (response.isSuccessful()) {
                    ArrayList<User> usersList = response.body();
                    mView.setUsersList(usersList);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<User>> call, Throwable t) {
                Log.e("SearchPresenter", t.getMessage(), t);
            }
        });
        */

        mApi.search(q).enqueue(new Callback<HashMap<String, Object>>() {
            @Override
            public void onResponse(Call<HashMap<String, Object>> call, Response<HashMap<String, Object>> response) {
                if (response.isSuccessful()) {
                    HashMap<String, Object> body = response.body();
                    
                    Log.d("SearchPresenter", "Test");
                }
            }

            @Override
            public void onFailure(Call<HashMap<String, Object>> call, Throwable t) {

            }
        });
    }
}
