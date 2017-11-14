package com.meisshi.meisshi.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.meisshi.meisshi.R;
import com.meisshi.meisshi.model.User;
import com.meisshi.meisshi.presenter.SearchPresenter;
import com.meisshi.meisshi.ui.activity.ProfileActivity;
import com.meisshi.meisshi.ui.adapter.UserCardAdapter;
import com.meisshi.meisshi.view.ISearchView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by DevAg on 05/09/2017.
 */
public class SearchFragment extends BaseFragment
    implements ISearchView {

    private ListView mLvCards;
    private EditText mEtSearch;

    private SearchPresenter mPresenter;
    private List<User> mListUsers;
    private UserCardAdapter mAdapter;
    private ImageButton mBtnSearch;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, null);

        mLvCards = (ListView) view.findViewById(R.id.lv_cards);
        mEtSearch = (EditText) view.findViewById(R.id.et_search);
        mBtnSearch = (ImageButton) view.findViewById(R.id.btnSearch);

        mBtnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.search();
            }
        });

        mLvCards.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                showProfile(mListUsers.get(i));
            }
        });

        return view;
    }

    private void showProfile(User user) {
        Intent i = new Intent(this.getContext(), ProfileActivity.class);

        Bundle args = new Bundle();
        args.putSerializable(ProfileActivity.ARG_USER, user);

        i.putExtras(args);

        startActivity(i);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setup();
    }

    @Override
    public void setup() {
        mPresenter = new SearchPresenter(this);
        mApplicationComponent.inject(mPresenter);

        mListUsers = new ArrayList<>();
        mAdapter = new UserCardAdapter(mListUsers, this.getContext());

        mLvCards.setAdapter(mAdapter);

        mPresenter.search();
    }

    @Override
    public void showErrorMessage(int titleRes, int messageRes) {
        new AlertDialog.Builder(getActivity())
                .setTitle(titleRes)
                .setMessage(messageRes)
                .create()
                .show();
    }

    @Override
    public String getFilter() {
        return mEtSearch.getText().toString();
    }

    @Override
    public void addUsers(User[] users) {
        mListUsers.addAll(Arrays.asList(users));
        mAdapter.notifyDataSetChanged();
    }
}
