package com.meisshi.meisshi.ui.view;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.meisshi.meisshi.R;
import com.meisshi.meisshi.model.Card;
import com.meisshi.meisshi.model.CardField;
import com.meisshi.meisshi.model.User;
import com.squareup.picasso.Picasso;

/**
 * Created by DevAg on 02/09/2017.
 */

public class MeisshiCard extends RelativeLayout {

    private static final int REAL_CARD_WIDTH = 300;
    private static final int REAL_CARD_HEIGHT = 169;
    private static final int CARD_PADDING = 10;

    private TextView mTvShowName;
    private TextView mTvWorkEmail;
    private TextView mTvProfession;
    private TextView mTvCompany;
    private TextView mTvAddress;
    private TextView mTvTwitter;
    private TextView mTvFacebook;
    private TextView mTvWebsite;
    private TextView mTvTelephone1;
    private TextView mTvTelephone2;
    private ImageView mIvLogo;

    private ImageView mIvCard;
    private ImageView mIvQr;

    private Card mCard;
    private User mUser;
    private Picasso mPiInstance;
    private ViewGroup mViewCard;
    private ViewGroup mViewQr;
    private double mScaleFactor = 1;
    private ImageButton mBtFlip;
    private AnimatorSet mSetRightOut;
    private AnimatorSet mSetLeftIn;
    private boolean mIsBackVisible = false;

    public MeisshiCard(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        initPosition();
        initAnimations();
    }

    private void init() {
        mViewCard = new RelativeLayout(this.getContext());
        //mViewCard.setBackgroundColor(Color.RED);

        mViewQr = new RelativeLayout(this.getContext());
        //mViewQr.setBackgroundColor(Color.GREEN);
        mViewQr.setBackgroundColor(Color.WHITE);

        mBtFlip = new ImageButton(this.getContext());
        mBtFlip.setBackgroundColor(Color.BLUE);
        mBtFlip.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                flipCard();
            }
        });

        this.addView(mViewQr);
        this.addView(mViewCard);
        this.addView(mBtFlip);

        mTvShowName = new TextView(this.getContext());
        mTvWorkEmail = new TextView(this.getContext());
        mTvProfession = new TextView(this.getContext());
        mTvCompany = new TextView(this.getContext());
        mTvAddress = new TextView(this.getContext());
        mTvTwitter = new TextView(this.getContext());
        mTvFacebook = new TextView(this.getContext());
        mTvWebsite = new TextView(this.getContext());
        mTvTelephone1 = new TextView(this.getContext());
        mTvTelephone2 = new TextView(this.getContext());

        mIvCard = new ImageView(this.getContext());
        mIvLogo = new ImageView(this.getContext());

        mViewCard.addView(mIvCard);
        mViewCard.addView(mTvShowName);
        mViewCard.addView(mTvWorkEmail);
        mViewCard.addView(mTvProfession);
        mViewCard.addView(mTvCompany);
        mViewCard.addView(mTvAddress);
        mViewCard.addView(mTvTwitter);
        mViewCard.addView(mTvFacebook);
        mViewCard.addView(mTvWebsite);
        mViewCard.addView(mTvTelephone1);
        mViewCard.addView(mTvTelephone2);

        mViewCard.addView(mIvLogo);

        mIvQr = new ImageView(getContext());

        mViewQr.addView(mIvQr);


        mPiInstance = Picasso.with(this.getContext());
    }

    private void flipCard() {
        if (!mIsBackVisible) {
            mSetRightOut.setTarget(mViewCard);
            mSetLeftIn.setTarget(mViewQr);
            mSetRightOut.start();
            mSetLeftIn.start();
            mIsBackVisible = true;
        } else {
            mSetRightOut.setTarget(mViewQr);
            mSetLeftIn.setTarget(mViewCard);
            mSetRightOut.start();
            mSetLeftIn.start();
            mIsBackVisible = false;
        }
    }

    public void initPosition() {
        mScaleFactor = (getResources().getDisplayMetrics().widthPixels - CARD_PADDING) / REAL_CARD_WIDTH;

        int xCard = (int) ((getResources().getDisplayMetrics().widthPixels) - (REAL_CARD_WIDTH * mScaleFactor)) / 2;

        int widthFlip = (int) ((REAL_CARD_WIDTH / 8) * mScaleFactor);
        int xFlip = (int)(xCard + (REAL_CARD_WIDTH * mScaleFactor)) - (widthFlip / 2);

        RelativeLayout.LayoutParams paramsCard = new RelativeLayout
                .LayoutParams((int) (REAL_CARD_WIDTH * mScaleFactor), (int) (REAL_CARD_HEIGHT * mScaleFactor));
        RelativeLayout.LayoutParams paramsQr = new RelativeLayout
                .LayoutParams((int) (REAL_CARD_WIDTH * mScaleFactor), (int) (REAL_CARD_HEIGHT * mScaleFactor));
        RelativeLayout.LayoutParams cardParams = new RelativeLayout
                .LayoutParams((int) (REAL_CARD_WIDTH * mScaleFactor), (int) (REAL_CARD_HEIGHT * mScaleFactor));
        RelativeLayout.LayoutParams qrParams = new RelativeLayout
                .LayoutParams((int) (REAL_CARD_WIDTH * mScaleFactor), (int) (REAL_CARD_HEIGHT * mScaleFactor));
        RelativeLayout.LayoutParams flipParams = new RelativeLayout
                .LayoutParams(widthFlip, widthFlip);

        mViewCard.setLayoutParams(paramsCard);
        mViewQr.setLayoutParams(paramsQr);

        mIvCard.setLayoutParams(cardParams);
        mIvQr.setLayoutParams(qrParams);

        mBtFlip.setLayoutParams(flipParams);

        mViewCard.setX(xCard);
        mViewCard.setY(widthFlip  / 2);

        mViewQr.setX(xCard);
        mViewQr.setY(widthFlip  / 2);

        mBtFlip.setX(xFlip);
    }

    public void initAnimations() {
        mSetRightOut = (AnimatorSet) AnimatorInflater.loadAnimator(this.getContext(), R.animator.out_animation);
        mSetLeftIn = (AnimatorSet) AnimatorInflater.loadAnimator(this.getContext(), R.animator.in_animation);
    }

    public void buildCard() {
        setupTextView(mTvShowName, mCard.getShowName(), mUser.getShowName());
        setupTextView(mTvWorkEmail, mCard.getWorkEmail(), mUser.getWorkEmail());
        setupTextView(mTvProfession, mCard.getProfession(), mUser.getProfession());
        setupTextView(mTvCompany, mCard.getCompany(), mUser.getCompany());
        setupTextView(mTvAddress, mCard.getAddress(), mUser.getAddress());
        setupTextView(mTvTelephone1, mCard.getTelephone1(), mUser.getTelephone1());
        setupTextView(mTvTelephone2, mCard.getTelephone2(), mUser.getTelephone2());
        setupTextView(mTvFacebook, mCard.getFacebook(), mUser.getFacebook());
        setupTextView(mTvWebsite, mCard.getWebsite(), mUser.getWebsite());
        setupTextView(mTvTwitter, mCard.getTwitter(), mUser.getTwitter());

        if (mCard.getLogo() != null) {
            CardField field = mCard.getLogo();

            mIvLogo.requestLayout();

            mIvLogo.setX((int) (field.getX() * mScaleFactor));
            mIvLogo.setY((int) (field.getY() * mScaleFactor));

            mIvLogo.getLayoutParams().width = (int) (field.getWidth() * mScaleFactor);
            mIvLogo.getLayoutParams().height = (int) (field.getHeight() * mScaleFactor);
            mIvLogo.requestLayout();

            mPiInstance
                    .load(mUser.getLogo())
                    .into(mIvLogo);
            mPiInstance
                    .load(mUser.getQrImage())
                    .into(mIvQr);
        }

        mPiInstance
                .load(mCard.getImageUrl())
                .into(mIvCard);

    }

    public void setupTextView(TextView tv, CardField field, String text) {
        if (field != null) {
            tv.setVisibility(View.VISIBLE);

            tv.setX((int) (field.getX()* mScaleFactor));
            tv.setY((int) (field.getY() * mScaleFactor));

            tv.setWidth((int) (field.getWidth() * mScaleFactor));
            tv.setHeight((int) (field.getHeight() * mScaleFactor));

            tv.setTextColor(Color.parseColor(field.getColor()));

            tv.setIncludeFontPadding(false);
            tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, (int) (field.getFontSize() * mScaleFactor));

            if ("bold".equals(field.getStyle())) {
                tv.setTypeface(null, Typeface.BOLD);
            } else {
                tv.setTypeface(null, Typeface.NORMAL);
            }

            if ("center".equals(field.getAlign())) {
                tv.setGravity(Gravity.CENTER);
            } else if ("right".equals(field.getAlign())) {
                tv.setGravity(Gravity.RIGHT);
            } else {
                tv.setGravity(Gravity.LEFT);
            }

            tv.setText(text);
        } else {
            tv.setVisibility(View.GONE);
        }
    }

    public void setCardData(Card card, User user) {
        this.mCard = card;
        this.mUser = user;
        buildCard();
    }
}
