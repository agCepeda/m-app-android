package com.meisshi.meisshi.presenter;

import com.meisshi.meisshi.model.Pagination;
import com.meisshi.meisshi.model.User;
import com.meisshi.meisshi.view.ISearchView;

import java.util.HashMap;

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
    private boolean isLoading;

    public SearchPresenter(ISearchView view) {
        this.mView = view;
    }

    public void search() {
        mQuery = mView.getFilter();
        mSize = 20;
        mPage = 1;
        mView.clearUsers();
        this.loadUsers();
    }

    //https://www.xvideos.com/video29181459/redhead_milf_homewrecker_fucks_her_neighbor
    //https://www.xvideos.com/video13895003/no_se_lo_digas_a_tu_madre

    public void loadUsers() {
        if (! isLoading) {
            isLoading = true;
            HashMap<String, Object> params = new HashMap<>();

            params.put("q", mQuery);
            params.put("size", mSize);
            params.put("page", mPage);

            mApi.search(mQuery, mSize, mPage).enqueue(new Callback<Pagination<User>>() {
                @Override
                public void onResponse(Call<Pagination<User>> call, Response<Pagination<User>> response) {
                    if (response.isSuccessful()) {
                        mPage++;
                        mView.addUsers(response.body().getItems());
                    }

                    isLoading = false;
                }

                @Override
                public void onFailure(Call<Pagination<User>> call, Throwable t) {
                    t.printStackTrace();
                    isLoading = false;
                }
            });
        }
    }
}
