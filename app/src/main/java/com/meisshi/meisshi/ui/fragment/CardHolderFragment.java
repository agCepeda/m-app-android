package com.meisshi.meisshi.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.meisshi.meisshi.R;
import com.meisshi.meisshi.model.User;
import com.meisshi.meisshi.presenter.CardHolderPresenter;
import com.meisshi.meisshi.ui.adapter.UserCardAdapter;
import com.meisshi.meisshi.view.ICardHolderView;

import java.util.List;

/**
 * Created by DevAg on 05/09/2017.
 */

public class CardHolderFragment extends BaseFragment
    implements ICardHolderView {

    private ListView mLvContacts;
    private CardHolderPresenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_card_holder, null);

        mLvContacts = (ListView) view.findViewById(R.id.lvContacts);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        setup();
    }

    @Override
    public void setup() {
        mPresenter = new CardHolderPresenter(this);
        mApplicationComponent.inject(mPresenter);

        mPresenter.loadContacts();
    }

    @Override
    public void showErrorMessage(String message) {

    }

    @Override
    public void setContacts(List<User> contacts) {
        UserCardAdapter adapter = new UserCardAdapter(contacts, getContext());
        mLvContacts.setAdapter(adapter);
    }
}
