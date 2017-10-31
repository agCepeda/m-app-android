package com.meisshi.meisshi.ui.fragment;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.meisshi.meisshi.R;
import com.meisshi.meisshi.model.Review;
import com.meisshi.meisshi.model.User;
import com.meisshi.meisshi.presenter.ProfilePresenter;
import com.meisshi.meisshi.ui.adapter.ReviewsAdapter;
import com.meisshi.meisshi.ui.view.MeisshiCard;
import com.meisshi.meisshi.util.FontManager;
import com.meisshi.meisshi.view.IProfileView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created by DevAg on 05/09/2017.
 */

public class ProfileFragment extends BaseFragment
    implements IProfileView {

    public static final String ARG_IS_OWN = "ARG_IS_OWN";
    public static final String ARG_USER = "ARG_USER";

    private MeisshiCard mMcCard;
    private ProfilePresenter mPresenter;
    private User mUser;
    private boolean mIsOwn;

    private ImageView mImvProfile;
    private TextView mTvUsername;
    private TextView mTvProfession;
    private Button mBtnFollowed;
    private Button mBtnFollowers;

    private TextView mTvBio;


    private TextView mTvIconPhone;
    private TextView mTvIconWebsite;
    private TextView mTvIconEmail;
    private TextView mTvIconAddress;

    private TextView mTvPhone;
    private TextView mTvWebsite;
    private TextView mTvEmail;
    private TextView mTvAddress;

    private View mViewPhone;
    private View mViewWebsite;
    private View mViewEmail;
    private View mViewAddress;

    private LinearLayout mLvReviews;
    private List<Review> mListReviews = new ArrayList<>();

    private ReviewsAdapter mReviewsAdapter;

    private Picasso mImgLoader;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, null);

        mMcCard = (MeisshiCard) view.findViewById(R.id.meisshi_card);
        mImvProfile = (ImageView) view.findViewById(R.id.imvProfile);

        mTvUsername   = (TextView) view.findViewById(R.id.tvUsername);
        mTvProfession = (TextView) view.findViewById(R.id.tvProfession);

        mBtnFollowed  = (Button) view.findViewById(R.id.btnFollowed);
        mBtnFollowers = (Button) view.findViewById(R.id.btnFollowers);

        mTvBio = (TextView) view.findViewById(R.id.tvBio);

        mViewPhone = view.findViewById(R.id.viewPhone);
        mTvIconPhone = (TextView) view.findViewById(R.id.tvIconPhone);
        mTvPhone = (TextView) view.findViewById(R.id.tvPhone);

        mViewEmail = view.findViewById(R.id.viewEmail);
        mTvIconEmail = (TextView) view.findViewById(R.id.tvIconEmail);
        mTvEmail = (TextView) view.findViewById(R.id.tvEmail);

        mViewAddress = view.findViewById(R.id.viewAddress);
        mTvIconAddress = (TextView) view.findViewById(R.id.tvIconAddress);
        mTvAddress = (TextView) view.findViewById(R.id.tvAddress);

        mViewWebsite = view.findViewById(R.id.viewWebsite);
        mTvIconWebsite = (TextView) view.findViewById(R.id.tvIconWebsite);
        mTvWebsite = (TextView) view.findViewById(R.id.tvWebsite);

        mLvReviews = (LinearLayout) view.findViewById(R.id.lvReviews);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        setup();
    }

    @Override
    public void setup() {
        mUser  = (User) getArguments().getSerializable(ARG_USER);
        mIsOwn = getArguments().getBoolean(ARG_IS_OWN);

        mImgLoader = Picasso.with(getContext());

        mPresenter = new ProfilePresenter(this);
        mApplicationComponent.inject(mPresenter);

        // mReviewsAdapter = new ReviewsAdapter(mListReviews, getContext());

        mPresenter.loadProfile();
    }

    @Override
    public void showErrorMessage(String message) {

    }

    @Override
    public User getUser() {
        return mUser;
    }

    @Override
    public void addReviews(Review[] items) {
        mListReviews.addAll(Arrays.asList(items));

        LayoutInflater inflater = LayoutInflater.from(getContext());
        for(Review r : items) {
            View view = inflater.inflate(R.layout.item_review, null);
            TextView tvUsername = (TextView) view.findViewById(R.id.tvUsername);
            TextView tvComment  = (TextView) view.findViewById(R.id.tvComment);

            tvComment.setText(r.getComment());
            tvUsername.setText(r.getReviewer().getShowName());

            mLvReviews.addView(view);
        }
    }

    @Override
    public void lockAddRemove() {
    }

    @Override
    public void unlockAddRemove() {
    }

    @Override
    public void setUser(User user) {
        mUser = user;

        mMcCard.setCardData(user.getCard(), user);
        mImgLoader.load(user.getProfilePicture()).into(mImvProfile);

        mTvUsername.setText(user.getShowName());
        mTvProfession.setText(user.getProfession());

        mTvBio.setText(user.getBio());

        Typeface font = FontManager.getTypeface(getContext(), FontManager.FONTAWESOME);

        mTvIconPhone.setTypeface(font, Typeface.BOLD);
        mTvPhone.setText(user.getTelephone1());

        mTvIconAddress.setTypeface(font, Typeface.BOLD);
        mTvAddress.setText(user.getAddress());

        mTvIconWebsite.setTypeface(font, Typeface.BOLD);
        mTvWebsite.setText(user.getWebsite());

        mTvIconEmail.setTypeface(font, Typeface.BOLD);
        mTvEmail.setText(user.getWorkEmail());

        //mBtnFollowed.setText(user.getFollowingCount());
        //mBtnFollowers.setText(user.getFollowersCount());
    }

    @Override
    public void setToolText(int resourceString) {

    }
}
