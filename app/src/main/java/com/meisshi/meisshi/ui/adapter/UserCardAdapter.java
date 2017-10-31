package com.meisshi.meisshi.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.meisshi.meisshi.R;
import com.meisshi.meisshi.model.User;
import com.meisshi.meisshi.ui.view.MeisshiCard;

import java.util.List;

/**
 * Created by agustin on 30/10/2017.
 */

public class UserCardAdapter extends BaseAdapter {

    private List<User> mUserList;
    private LayoutInflater mInflater;

    public UserCardAdapter(List<User> userList, Context context) {
        mUserList = userList;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mUserList.size();
    }

    @Override
    public Object getItem(int i) {
        return mUserList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;

        if (view != null) {
            viewHolder = (ViewHolder) view.getTag();
        } else {
            view = mInflater.inflate(R.layout.item_user_card, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }

        User user = mUserList.get(i);

        viewHolder.mTvUsername.setText(user.getShowName());
        viewHolder.mTvProfession.setText(user.getProfession());
        viewHolder.mCard.setCardData(user.getCard(), user);

        return view;
    }

    private class ViewHolder {
        TextView mTvUsername;
        TextView mTvProfession;
        MeisshiCard mCard;

        public ViewHolder(View view) {
            mTvUsername = (TextView) view.findViewById(R.id.tvUsername);
            mTvProfession = (TextView) view.findViewById(R.id.tvProfession);
            mCard = (MeisshiCard) view.findViewById(R.id.meisshiCard);
        }
    }
}
