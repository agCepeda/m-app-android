package com.meisshi.meisshi.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.meisshi.meisshi.R;
import com.meisshi.meisshi.model.User;
import com.meisshi.meisshi.presenter.EditProfilePresenter;
import com.meisshi.meisshi.view.IEditProfileView;
import com.squareup.picasso.Picasso;

/**
 * Created by agustin on 02/11/2017.
 */

public class EditProfileActivity extends BaseActivity
    implements IEditProfileView {

    private ImageView mImvLogo;
    private ImageView mImvProfile;
    private EditProfilePresenter mPresenter;
    private Picasso mImageLoader;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        mImvProfile = (ImageView) findViewById(R.id.imvProfile);
        mImvLogo = (ImageView) findViewById(R.id.imvLogo);

        setup();
    }

    @Override
    public void setup() {
        mPresenter = new EditProfilePresenter(this);
        mApplicationComponent.inject(mPresenter);

        mImageLoader = Picasso.with(getApplicationContext());

        User user = mApplication.getUser();

        mImageLoader.load(user.getProfilePicture()).into(mImvProfile);
        mImageLoader.load(user.getLogo()).into(mImvLogo);
    }

    @Override
    public void showErrorMessage(String message) {

    }
}
