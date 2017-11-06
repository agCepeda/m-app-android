package com.meisshi.meisshi.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.meisshi.meisshi.R;
import com.meisshi.meisshi.model.Card;
import com.meisshi.meisshi.model.User;
import com.meisshi.meisshi.ui.view.MCard;

import java.util.List;

/**
 * Created by DevAg on 05/11/2017.
 */

public class CardsAdapter extends BaseAdapter {

    private List<Card> mListCard;
    private User mUser;
    private LayoutInflater mInflater;
    private int selectedIndex;
    private int selectedColor = Color.parseColor("#8D006F80");


    public CardsAdapter(List<Card> listCards, User user, Context context) {
        mInflater = LayoutInflater.from(context);
        mListCard = listCards;
        mUser = user;
        selectedIndex = -1;
    }

    public void setSelectedIndex(int ind)
    {
        selectedIndex = ind;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mListCard.size();
    }

    @Override
    public Object getItem(int i) {
        return mListCard.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;

        if (view == null) {
            view = mInflater.inflate(R.layout.item_card, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } {
            holder = (ViewHolder) view.getTag();
        }

        Card card = mListCard.get(i);
        holder.card.setCardData(card, mUser);

        if(selectedIndex!= -1 && i == selectedIndex)
        {
            holder.card.setBackgroundColor(selectedColor);
        }
        else
        {
            holder.card.setBackgroundColor(Color.TRANSPARENT);
        }

        return view;
    }

    private class ViewHolder {
        MCard card;

        ViewHolder(View v) {
            card = (MCard) v.findViewById(R.id.card);
        }
    }
}
