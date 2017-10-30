package com.meisshi.meisshi.presenter;

import com.meisshi.meisshi.model.User;
import com.meisshi.meisshi.view.ICardHolderView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by agustin on 30/10/2017.
 */

public class CardHolderPresenter extends BasePresenter {

    private ICardHolderView mView;

    public CardHolderPresenter(ICardHolderView view) {
        this.mView = view;
    }

    public void loadContacts() {
        mApi.getContacts().enqueue(new Callback<ArrayList<User>>() {
            @Override
            public void onResponse(Call<ArrayList<User>> call, Response<ArrayList<User>> response) {
                if (response.isSuccessful()) {
                    mView.setContacts(response.body());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<User>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
