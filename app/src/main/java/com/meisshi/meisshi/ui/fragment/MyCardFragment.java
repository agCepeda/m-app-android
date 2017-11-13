package com.meisshi.meisshi.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.meisshi.meisshi.MeisshiApp;
import com.meisshi.meisshi.R;
import com.meisshi.meisshi.model.User;
import com.meisshi.meisshi.presenter.MyCardPresenter;
import com.meisshi.meisshi.ui.activity.EditProfileActivity;
import com.meisshi.meisshi.ui.view.MeisshiCard;
import com.meisshi.meisshi.view.IMyCardView;
import com.squareup.picasso.Picasso;

/**
 * Created by DevAg on 05/09/2017.
 */

public class MyCardFragment extends BaseFragment
    implements IMyCardView {

    private MeisshiCard mMcCard;
    private MyCardPresenter mPresenter;
    private ImageView mImvProfile;
    private TextView mTvUsername;
    private TextView mTvProfession;
    private Button mBtnShare;
    private View mContainerCard;
    private View mContainerNew;
    private Button mBtnCreate;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_card, null);

        mMcCard = (MeisshiCard) view.findViewById(R.id.meisshi_card);
        mTvUsername = (TextView) view.findViewById(R.id.tvUsername);
        mTvProfession = (TextView) view.findViewById(R.id.tvProfession);
        mImvProfile = (ImageView) view.findViewById(R.id.imvProfile);
        mBtnShare = (Button) view.findViewById(R.id.btnShare);

        mContainerCard = view.findViewById(R.id.container_card);
        mContainerNew = view.findViewById(R.id.container_new);

        mBtnCreate = (Button) view.findViewById(R.id.btn_create_card);

        mBtnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                share();
            }
        });
        mBtnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showEditProfile();
            }
        });

        return view;
    }

    private void showEditProfile() {
        Intent i = new Intent(getContext(), EditProfileActivity.class);
        startActivity(i);
    }

    private void share() {
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String shareBody = "Take my meisshi and i hope to serve you. \n\n " + MeisshiApp.MEISSHI_API_END_POINT + "profile/" + mApplication.getUser().getId();
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Meisshi");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }

    @Override
    public void onStart() {
        super.onStart();
        setup();
    }

    @Override
    public void setup() {
        User user = mApplication.getUser();
        mMcCard.setCardData(
                user.getCard(),
                user
        );

        mTvUsername.setText(user.getShowName());
        mTvProfession.setText(user.getProfession());

        Picasso.with(getContext()).load(user.getProfilePicture()).into(mImvProfile);

        mPresenter = new MyCardPresenter(this);

        if (user.getCard() == null) {
            mContainerNew.setVisibility(View.VISIBLE);
            mContainerCard.setVisibility(View.INVISIBLE);
        } else {
            mContainerNew.setVisibility(View.GONE);
            mContainerCard.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void showErrorMessage(int titleRes, int messageRes) {

    }
}

