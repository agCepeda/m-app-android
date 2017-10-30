package com.meisshi.meisshi.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.meisshi.meisshi.R;
import com.meisshi.meisshi.ui.view.MeisshiCard;

/**
 * Created by DevAg on 05/09/2017.
 */

public class MyCardFragment extends BaseFragment {

    private MeisshiCard mMcCard;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_card, null);

        mMcCard = (MeisshiCard) view.findViewById(R.id.meisshi_card);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        mMcCard.setCardData(
                mApplication.getUser().getCard(),
                mApplication.getUser()
        );
    }
}

