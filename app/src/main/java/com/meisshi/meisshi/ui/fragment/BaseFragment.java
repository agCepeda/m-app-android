package com.meisshi.meisshi.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.meisshi.meisshi.MeisshiApp;
import com.meisshi.meisshi.di.ApplicationComponent;

/**
 * Created by DevAg on 20/08/2017.
 */

public class BaseFragment extends Fragment {

    protected ApplicationComponent mApplicationComponent;
    protected MeisshiApp mApplication;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApplication = ((MeisshiApp) getActivity().getApplication());
        mApplicationComponent = mApplication.getApplicationComponent();
    }
}
