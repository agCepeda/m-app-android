package com.meisshi.meisshi.presenter;

import com.meisshi.meisshi.R;
import com.meisshi.meisshi.model.Pagination;
import com.meisshi.meisshi.model.Review;
import com.meisshi.meisshi.model.User;
import com.meisshi.meisshi.view.IProfileView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by DevAg on 27/10/2017.
 */

public class ProfilePresenter extends BasePresenter {

    private IProfileView mView;

    public ProfilePresenter(IProfileView view) {
        this.mView = view;
    }

    public void loadProfile() {
        User user = mView.getUser();
        mApi.getUser(user.getId(), "my_review").enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    mView.setUser(response.body());
                    loadReviews();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                t.printStackTrace();
                //mView.showErrorMessage("", );
            }
        });
    }

    public void loadReviews() {
        User user = mView.getUser();

        mApi.loadReviews(user.getId(), 10, 1).enqueue(new Callback<Pagination<Review>>() {
            @Override
            public void onResponse(Call<Pagination<Review>> call, Response<Pagination<Review>> response) {
                if (response.isSuccessful()) {
                    mView.addReviews(response.body().getItems());
                }
            }

            @Override
            public void onFailure(Call<Pagination<Review>> call, Throwable t) {

                t.printStackTrace();
                //mView.showErrorMessage("", );
            }
        });
    }

    public void addContact() {
        mView.lockAddRemove();
        User user = mView.getUser();
        mApi.addContact(user.getId()).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    mView.setToolText(
                            R.string.profile_remove_contact
                    );
                    mView.getUser().setContact(true);
                }
                mView.unlockAddRemove();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                //mView.showErrorMessage("", );
                mView.unlockAddRemove();

                t.printStackTrace();
            }
        });
    }

    public void removeContact() {
        mView.lockAddRemove();
        User user = mView.getUser();
        mApi.removeContact(user.getId()).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    mView.setToolText(
                            R.string.profile_add_contact
                    );
                    mView.getUser().setContact(false);
                }
                mView.unlockAddRemove();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                //mView.showErrorMessage("", );
                mView.unlockAddRemove();

                t.printStackTrace();
            }
        });
    }
}
