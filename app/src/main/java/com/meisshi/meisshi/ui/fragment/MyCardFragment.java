package com.meisshi.meisshi.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.meisshi.meisshi.R;
import com.meisshi.meisshi.model.User;
import com.meisshi.meisshi.presenter.MyCardPresenter;
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_card, null);

        mMcCard = (MeisshiCard) view.findViewById(R.id.meisshi_card);
        mTvUsername = (TextView) view.findViewById(R.id.tvUsername);
        mTvProfession = (TextView) view.findViewById(R.id.tvProfession);
        mImvProfile = (ImageView) view.findViewById(R.id.imvProfile);

        return view;
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


    }

    @Override
    public void showErrorMessage(String message) {

    }
}

