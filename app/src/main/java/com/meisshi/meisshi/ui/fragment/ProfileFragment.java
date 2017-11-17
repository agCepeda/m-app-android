package com.meisshi.meisshi.ui.fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.maxwell.nc.library.StarRatingView;
import com.meisshi.meisshi.R;
import com.meisshi.meisshi.model.Review;
import com.meisshi.meisshi.model.User;
import com.meisshi.meisshi.presenter.ProfilePresenter;
import com.meisshi.meisshi.ui.activity.EditProfileActivity;
import com.meisshi.meisshi.ui.activity.FollowersActivity;
import com.meisshi.meisshi.ui.activity.ProfileActivity;
import com.meisshi.meisshi.ui.activity.ReviewFormActivity;
import com.meisshi.meisshi.ui.view.MeisshiCard;
import com.meisshi.meisshi.util.FontManager;
import com.meisshi.meisshi.view.IProfileView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

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

    private ImageButton mBtnInstagram;
    private ImageButton mBtnFacebook;
    private ImageButton mBtnTwitter;

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

    private Picasso mImgLoader;
    private Button mBtnTool;
    private Button mBtnReview;

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
        mBtnTool = (Button) view.findViewById(R.id.btnTool);

        mBtnFacebook = (ImageButton) view.findViewById(R.id.btnFacebook);
        mBtnTwitter = (ImageButton) view.findViewById(R.id.btnTwitter);
        mBtnInstagram = (ImageButton) view.findViewById(R.id.btnInstagram);

        View.OnClickListener socialClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.btnFacebook) {
                    gotoFacebook();

                } else if (view.getId() == R.id.btnTwitter) {
                    gotoTwitter();
                } else if (view.getId() == R.id.btnInstagram) {
                    gotoInstagram();
                }
            }
        };

        mBtnFacebook.setOnClickListener(socialClickListener);
        mBtnInstagram.setOnClickListener(socialClickListener);
        mBtnTwitter.setOnClickListener(socialClickListener);

        View.OnClickListener networkListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.viewAddress:
                        showMapAddress();
                        break;
                    case R.id.viewEmail:
                        showSendEmail();
                        break;
                    case R.id.viewPhone:
                        showMakePhoneCall();
                        break;
                    case R.id.viewWebsite:
                        showBrowser();
                        break;
                }
            }
        };

        mViewPhone = view.findViewById(R.id.viewPhone);
        mViewPhone.setOnClickListener(networkListener);
        mTvIconPhone = (TextView) view.findViewById(R.id.tvIconPhone);
        mTvPhone = (TextView) view.findViewById(R.id.tvPhone);

        mViewEmail = view.findViewById(R.id.viewEmail);
        mViewEmail.setOnClickListener(networkListener);
        mTvIconEmail = (TextView) view.findViewById(R.id.tvIconEmail);
        mTvEmail = (TextView) view.findViewById(R.id.tvEmail);

        mViewAddress = view.findViewById(R.id.viewAddress);
        mViewAddress.setOnClickListener(networkListener);
        mTvIconAddress = (TextView) view.findViewById(R.id.tvIconAddress);
        mTvAddress = (TextView) view.findViewById(R.id.tvAddress);

        mViewWebsite = view.findViewById(R.id.viewWebsite);
        mViewWebsite.setOnClickListener(networkListener);
        mTvIconWebsite = (TextView) view.findViewById(R.id.tvIconWebsite);
        mTvWebsite = (TextView) view.findViewById(R.id.tvWebsite);

        mBtnReview = (Button) view.findViewById(R.id.btnReview);
        mLvReviews = (LinearLayout) view.findViewById(R.id.lvReviews);

        mBtnFollowed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFollowed();
            }
        });

        mBtnFollowers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFollowers();
            }
        });

        mBtnTool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toolAction();
            }
        });
        mBtnReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showReviewForm();
            }
        });

        return view;
    }

    private void showSendEmail() {
        if (mUser.getWorkEmail() != null && ! mUser.getWorkEmail().isEmpty()) {
            Intent intent=new Intent(Intent.ACTION_SEND);
            String[] recipients={mUser.getWorkEmail()};
            intent.putExtra(Intent.EXTRA_EMAIL, recipients);
            //intent.putExtra(Intent.EXTRA_SUBJECT,"abc");
            //intent.putExtra(Intent.EXTRA_TEXT,"def");
            //intent.putExtra(Intent.EXTRA_CC,"ghi");
            intent.setType("text/html");
            startActivity(Intent.createChooser(intent, "Send mail"));
        }
    }

    private void showMakePhoneCall() {
        String phone = mUser.getTelephone1();

        if (phone == null)
            return;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CALL_PHONE}, 1);
            } else {
                Intent intent = new Intent(Intent.ACTION_CALL);

                intent.setData(Uri.parse("tel:" + phone));

                startActivity(intent);
            }
        }

    }


    private void showMapAddress() {
        String uri = String.format(Locale.ENGLISH, "geo:0,0?q=%s",mUser.getAddress());
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        startActivity(intent);
    }

    private void showBrowser() {
        String url = mUser.getWebsite();

        if (url == null)
            return;

        if (!url.startsWith("http://") && !url.startsWith("https://"))
            url = "http://" + url;

        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(browserIntent);

    }

    private void toolAction() {
        if (mIsOwn) {
            showEditProfile();
        } else if (mUser.isContact()) {
            mPresenter.removeContact();
        } else {
            mPresenter.addContact();
        }
    }

    private void gotoFacebook() {
        if (mUser.getFacebook() == null) return;
        try {
            getContext().getPackageManager()
                    .getPackageInfo("com.facebook.katana", 0); //Checks if FB is even installed.
            Intent i = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("fb://profile/" + mUser.getFacebook())); //Trys to make intent with FB's URI
            startActivity(i);
        } catch (Exception e) {
            Intent i = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.facebook.com/" + mUser.getFacebook())); //catches and opens a url to the desired page
            startActivity(i);
        }
    }
    private void gotoTwitter() {
        if (mUser.getTwitter() == null) return;
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("twitter://user?screen_name=" + mUser.getTwitter())));
        }catch (Exception e) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/#!/" + mUser.getTwitter())));
        }
    }
    private void gotoInstagram() {
        if (mUser.getInstagram() == null) return;
        Uri uri = Uri.parse("http://instagram.com/_u/" + mUser.getInstagram());
        Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

        likeIng.setPackage("com.instagram.android");

        try {
            startActivity(likeIng);
        } catch (Exception e) {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://instagram.com/" + mUser.getInstagram())));
        }
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
    public void showErrorMessage(int titleRes, int messageRes) {

    }

    @Override
    public User getUser() {
        return mUser;
    }

    @Override
    public void addReviews(Review[] items) {
        mListReviews = Arrays.asList(items);
        mLvReviews.removeAllViews();

        LayoutInflater inflater = LayoutInflater.from(getContext());
        for(Review r : items) {
            View view = inflater.inflate(R.layout.item_review, null);
            TextView tvUsername = (TextView) view.findViewById(R.id.tvUsername);
            TextView tvComment  = (TextView) view.findViewById(R.id.tvComment);
            StarRatingView srScore  = (StarRatingView) view.findViewById(R.id.srScore);

            tvComment.setText(r.getComment());
            tvUsername.setText(r.getReviewer().getShowName());
            srScore.rate(r.getScore());

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
        if (getActivity() instanceof ProfileActivity) {
            ((ProfileActivity) getActivity())
                    .getSupportActionBar()
                    .setTitle(user.getShowName());
        }

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

        mBtnFollowed.setText(user.getFollowingCount() + "\nFollowed");
        mBtnFollowers.setText(user.getFollowersCount() + "\nFollowers");

        if (mIsOwn) {
            mBtnReview.setVisibility(View.GONE);
            mBtnTool.setText(R.string.profile_tool_edit);
        } else if (mUser.isContact()) {
            mBtnTool.setText(R.string.profile_remove_contact);
        } else {
            mBtnTool.setText(R.string.profile_add_contact);
        }
    }

    @Override
    public void setToolText(int resourceString) {
        mBtnTool.setText(resourceString);
    }

    @Override
    public void showFollowers() {
        Intent i = new Intent(getContext(), FollowersActivity.class);

        Bundle args = new Bundle();
        args.putString(FollowersActivity.OPTION_FOLLOW_TYPE, FollowersActivity.FOLLOW_TYPE_FOLLOWER);
        args.putSerializable(FollowersActivity.OPTION_USER, mUser);

        i.putExtras(args);

        startActivity(i);
    }

    @Override
    public void showFollowed() {
        Intent i = new Intent(getContext(), FollowersActivity.class);

        Bundle args = new Bundle();
        args.putString(FollowersActivity.OPTION_FOLLOW_TYPE, FollowersActivity.FOLLOW_TYPE_FOLLOWED);
        args.putSerializable(FollowersActivity.OPTION_USER, mUser);

        i.putExtras(args);

        startActivity(i);
    }

    @Override
    public void showReviewForm() {
        Intent i = new Intent(getContext(), ReviewFormActivity.class);

        Bundle args = new Bundle();
        args.putSerializable(ReviewFormActivity.ARG_USER, mUser);

        i.putExtras(args);

        startActivity(i);
    }

    public void showEditProfile() {
        Intent i = new Intent(getContext(), EditProfileActivity.class);
        startActivity(i);
    }
}
