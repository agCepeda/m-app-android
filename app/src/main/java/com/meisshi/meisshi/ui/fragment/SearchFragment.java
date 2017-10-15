package com.meisshi.meisshi.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import com.meisshi.meisshi.R;
import com.meisshi.meisshi.model.User;
import com.meisshi.meisshi.presenter.SearchPresenter;
import com.meisshi.meisshi.ui.adapter.UsersAdapter;
import com.meisshi.meisshi.view.ISearchView;

import java.util.ArrayList;

/**
 * Created by DevAg on 05/09/2017.
 */
public class SearchFragment extends BaseFragment
    implements ISearchView {

    private ListView mLvCards;
    private EditText mEtSearch;

    private SearchPresenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, null);

        mLvCards = (ListView) view.findViewById(R.id.lv_cards);
        mEtSearch = (EditText) view.findViewById(R.id.et_search);

        return view;
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

        mPresenter.search();
    }

    @Override
    public void showErrorMessage(String message) {

    }

    @Override
    public String getFilter() {
        return mEtSearch.getText().toString();
    }

    @Override
    public void setUsersList(ArrayList<User> usersList) {
        UsersAdapter adapter = new UsersAdapter(usersList, this.getContext());

        mLvCards.setAdapter(adapter);
    }
}
