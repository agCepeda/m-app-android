package com.meisshi.meisshi.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.meisshi.meisshi.R;
import com.meisshi.meisshi.model.Review;

import java.util.List;

/**
 * Created by DevAg on 29/10/2017.
 */

public class ReviewsAdapter extends BaseAdapter {

    protected List<Review> mReviewList;
    protected LayoutInflater mInflater;

    public ReviewsAdapter(List<Review> reviewList, Context context) {
        this.mReviewList = reviewList;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mReviewList.size();
    }

    @Override
    public Object getItem(int i) {
        return mReviewList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return (long) Double.parseDouble(mReviewList.get(i).getId());
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder holder;
        if (view != null) {
            holder = (ViewHolder) view.getTag();
        } else {
            view = mInflater.inflate(R.layout.item_review, null);
            holder = new ViewHolder(view);

            view.setTag(holder);
        }

        Review review = mReviewList.get(i);

        holder.tvUsername.setText(review.getReviewer().getShowName());
        holder.tvComment.setText(review.getComment());

        return view;
    }

    private class ViewHolder {
        TextView tvUsername;
        TextView tvComment;

        ViewHolder(View view) {
            tvUsername = (TextView) view.findViewById(R.id.tvUsername);
            tvComment  = (TextView) view.findViewById(R.id.tvComment);
        }
    }
}
