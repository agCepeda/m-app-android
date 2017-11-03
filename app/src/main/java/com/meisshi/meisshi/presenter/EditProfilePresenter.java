package com.meisshi.meisshi.presenter;

import com.meisshi.meisshi.view.IEditProfileView;

/**
 * Created by agustin on 02/11/2017.
 */

public class EditProfilePresenter extends BasePresenter {

    private final IEditProfileView mView;

    public EditProfilePresenter(IEditProfileView view) {
        mView = view;
    }

}
