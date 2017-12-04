package com.meisshi.meisshi.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.meisshi.meisshi.R;
import com.meisshi.meisshi.model.User;
import com.meisshi.meisshi.presenter.CardHolderPresenter;
import com.meisshi.meisshi.ui.activity.ProfileActivity;
import com.meisshi.meisshi.ui.adapter.UserCardAdapter;
import com.meisshi.meisshi.view.ICardHolderView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DevAg on 05/09/2017.
 */

public class CardHolderFragment extends BaseFragment
    implements ICardHolderView {

    private ListView mLvContacts;
    private CardHolderPresenter mPresenter;
    private List<User> mListContacts = new ArrayList<>();
    private boolean mIsEnabled;
    private UserCardAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_card_holder, null);

        setupUI(view);

        return view;
    }

    private void setupUI(View view) {
        mLvContacts = (ListView) view.findViewById(R.id.lvContacts);
        mLvContacts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                showProfile(mListContacts.get(i));
            }
        });


        mAdapter = new UserCardAdapter(mListContacts, getContext());
        mLvContacts.setAdapter(mAdapter);
    }


    @Override
    public void onStart() {
        super.onStart();
        mIsEnabled = true;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setup();
    }

    @Override
    public void onStop() {
        super.onStop();
        mIsEnabled = false;
    }

    private void showProfile(User user) {
        Intent i = new Intent(this.getContext(), ProfileActivity.class);

        Bundle args = new Bundle();
        args.putSerializable(ProfileActivity.ARG_USER, user);

        i.putExtras(args);

        startActivity(i);
    }

    @Override
    public void setup() {
        mPresenter = new CardHolderPresenter(this);
        mApplicationComponent.inject(mPresenter);

        mPresenter.loadContacts();
    }

    @Override
    public void showErrorMessage(int titleRes, int messageRes) {

    }

    @Override
    public void setContacts(List<User> contacts) {
        mListContacts.clear();
        mListContacts.addAll(contacts);
        if (mIsEnabled) {
            mAdapter.notifyDataSetChanged();
        }
    }
}
