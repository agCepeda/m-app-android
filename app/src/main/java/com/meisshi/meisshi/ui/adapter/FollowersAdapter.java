package com.meisshi.meisshi.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.meisshi.meisshi.R;
import com.meisshi.meisshi.model.User;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by DevAg on 01/11/2017.
 */

public class FollowersAdapter extends BaseAdapter {

    private final List<User> mListUsers;
    private final LayoutInflater mInflater;
    private final Picasso mImageLoader;

    public FollowersAdapter(List<User> listUsers, Context context) {
        mListUsers = listUsers;
        mInflater = LayoutInflater.from(context);
        mImageLoader = Picasso.with(context);
    }

    @Override
    public int getCount() {
        return mListUsers.size();
    }

    @Override
    public Object getItem(int i) {
        return mListUsers.get(i);
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
            view = mInflater.inflate(R.layout.item_follower, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }

        User user = mListUsers.get(i);

        viewHolder.mTvUsername.setText(user.getShowName());
        viewHolder.mTvProfession.setText(user.getProfession());
        mImageLoader.load(user.getProfilePicture()).into(viewHolder.mImvProfile);

        return view;
    }

    private class ViewHolder {
        TextView mTvUsername;
        TextView mTvProfession;
        ImageView mImvProfile;

        public ViewHolder(View view) {
            mTvUsername = (TextView) view.findViewById(R.id.tvUsername);
            mTvProfession = (TextView) view.findViewById(R.id.tvProfession);
            mImvProfile = (ImageView) view.findViewById(R.id.imvProfile);
        }
    }
}
