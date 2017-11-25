package com.meisshi.meisshi.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.meisshi.meisshi.R;
import com.meisshi.meisshi.model.Card;
import com.meisshi.meisshi.model.User;
import com.meisshi.meisshi.ui.adapter.CardsAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by DevAg on 05/11/2017.
 */

public class SelectCardFragment extends BaseFragment {

    public static final String ARG_USER = "ARG_USER";

    private ListView mLvCards;
    private User mUser;
    private CardsAdapter mAdapter;
    private List<Card> mListCard;
    private int mSelectedIndex = -1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cards, null);
        mLvCards = (ListView) view.findViewById(R.id.lvCards);

        mLvCards.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mAdapter.setSelectedIndex(i);
                mSelectedIndex = i;
            }
        });

        setHasOptionsMenu(true);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        setup();
    }

    private void setup() {
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(R.string.select_card_title);

        mUser = (User) getArguments().getSerializable(ARG_USER);

        mApplicationComponent.inject(this);

        mApi.getCards().enqueue(new Callback<ArrayList<Card>>() {
            @Override
            public void onResponse(Call<ArrayList<Card>> call, Response<ArrayList<Card>> response) {
                if (response.isSuccessful()) {
                    setCards(response.body());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Card>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void setCards(List<Card> cards) {
        mListCard = cards;
        mAdapter = new CardsAdapter(cards, mUser, getContext());
        mLvCards.setAdapter(mAdapter);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.edit_profile_card, menu);
    }

    public Card getCard() {
        if (mSelectedIndex < 0)
            return null;
        return mListCard.get(mSelectedIndex);
    }
}
