package com.meisshi.meisshi.presenter;

import com.meisshi.meisshi.view.IMyCardView;

/**
 * Created by DevAg on 02/11/2017.
 */

public class MyCardPresenter extends BasePresenter {

    private IMyCardView mView;

    public MyCardPresenter(IMyCardView view) {
        mView = view;
    }
}
