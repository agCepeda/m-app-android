package com.meisshi.meisshi.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
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
    private List<User> mListUsers = new ArrayList<>();
    private UserCardAdapter mAdapter;
    private ImageButton mBtnSearch;
    private SwipeRefreshLayout mSwrUsers;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, null);

        mLvCards = (ListView) view.findViewById(R.id.lv_cards);
        mEtSearch = (EditText) view.findViewById(R.id.et_search);
        mBtnSearch = (ImageButton) view.findViewById(R.id.btnSearch);
        mSwrUsers = (SwipeRefreshLayout) view.findViewById(R.id.swrUsers);

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

        mLvCards.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {
                dissmissKeyboard();
            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {

            }
        });

        mSwrUsers.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.search();
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

        mAdapter = new UserCardAdapter(mListUsers, this.getContext());
        mAdapter.setScrollEndListener(new UserCardAdapter.OnScrollEndListener() {
            @Override
            public void onEndReached() {
                mPresenter.loadUsers();
            }
        });

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

        mSwrUsers.setRefreshing(false);
    }

    @Override
    public void clearUsers() {
        mListUsers.clear();
        mAdapter.notifyDataSetChanged();
    }

    private void dissmissKeyboard() {
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
