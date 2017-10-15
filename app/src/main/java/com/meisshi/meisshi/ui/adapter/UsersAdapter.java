package com.meisshi.meisshi.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;

import com.meisshi.meisshi.R;
import com.meisshi.meisshi.model.User;
import com.meisshi.meisshi.ui.view.MeisshiCard;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by DevAg on 06/09/2017.
 */

public class UsersAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private List<User> mLsUsers;

    public UsersAdapter(List<User> userList, Context context) {
        mLsUsers = userList;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mLsUsers.size();
    }

    @Override
    public Object getItem(int i) {
        return mLsUsers.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view != null) {
            view = mInflater.inflate(R.layout.list_item_card, null);

            holder = new ViewHolder(view);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        User user = mLsUsers.get(i);

        holder.card.setCardData(user.getCard(), user);

        return view;
    }

    private class ViewHolder {

        MeisshiCard card;

        public ViewHolder(View view) {
            card = (MeisshiCard) view.findViewById(R.id.meisshi_card);
        }
    }
}
