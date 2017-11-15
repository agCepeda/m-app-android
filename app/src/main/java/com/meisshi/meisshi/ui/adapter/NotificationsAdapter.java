package com.meisshi.meisshi.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.meisshi.meisshi.R;
import com.meisshi.meisshi.model.Notification;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by agustin on 13/11/2017.
 */

public class NotificationsAdapter extends BaseAdapter {

    private final List<Notification> mListNotifications;
    private final Picasso mImageLoader;
    private final LayoutInflater mLayoutInflater;

    public NotificationsAdapter(List<Notification> listNotifications, Context context) {
        mLayoutInflater = LayoutInflater.from(context);
        mImageLoader = Picasso.with(context);
        mListNotifications = listNotifications;
    }

    @Override
    public int getCount() {
        return mListNotifications.size();
    }

    @Override
    public Object getItem(int i) {
        return mListNotifications.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder holder;
        if (view == null) {
            view = mLayoutInflater.inflate(R.layout.item_notification, null);
            holder = new ViewHolder(view);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        Notification notification = mListNotifications.get(i);

        holder.tvUsername.setText(notification.getAttachment().getShowName());
        mImageLoader.load(notification.getAttachment().getProfilePicture()).into(holder.imvProfile);

        if ("review".equals(notification.getType())) {
            holder
                    .tvNotification
                    .setText(
                            notification.getAttachment().getShowName() +
                                    " posted a review on your profile!"
                    );
        } else {
            holder
                    .tvNotification
                    .setText(
                            notification.getAttachment().getShowName() +
                                    " started following you!"
                    );
        }

        return view;
    }

    private class ViewHolder {
        private final ImageView imvProfile;
        private final TextView tvUsername;
        private final TextView tvNotification;

        ViewHolder (View v) {
            imvProfile = (ImageView) v.findViewById(R.id.imvProfile);
            tvUsername = (TextView) v.findViewById(R.id.tvUsername);
            tvNotification = (TextView) v.findViewById(R.id.tvNotification);
        }
    }
}
