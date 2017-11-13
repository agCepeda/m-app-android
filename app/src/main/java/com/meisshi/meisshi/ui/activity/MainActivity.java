package com.meisshi.meisshi.ui.activity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;
import android.widget.TextView;

import com.meisshi.meisshi.R;
import com.meisshi.meisshi.ui.fragment.CardHolderFragment;
import com.meisshi.meisshi.ui.fragment.MyCardFragment;
import com.meisshi.meisshi.ui.fragment.ProfileFragment;
import com.meisshi.meisshi.ui.fragment.QrFragment;
import com.meisshi.meisshi.ui.fragment.SearchFragment;
import com.meisshi.meisshi.util.FontManager;
import com.meisshi.meisshi.view.IMainView;

/**
 * Created by DevAg on 02/09/2017.
 */

public class MainActivity extends BaseActivity
    implements IMainView {

    private BottomNavigationView mNavigationView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setup();
    }

    @Override
    public void setup() {
        mNavigationView = (BottomNavigationView)
                findViewById(R.id.bottom_navigation_view);

        TextView tvToolbarTitle =  (TextView) findViewById(R.id.tvToolbarTitle);
        Typeface font = FontManager.getTypeface(getApplicationContext(), FontManager.MEISSHI_FONT);
        tvToolbarTitle.setTypeface(font, Typeface.NORMAL);

        if (mNavigationView != null) {
            mNavigationView.setOnNavigationItemSelectedListener(
                    new BottomNavigationView.OnNavigationItemSelectedListener() {
                        @Override
                        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                            onSelectMenuItem(item);
                            return false;
                        }
                    });

            mNavigationView
                    .getMenu()
                    .findItem(R.id.action_my_card)
                    .setChecked(true);

            showMyCardView();

            if (mApplication.getUser().getCard() == null) {
                lockToolbar();
            } else {
                unlockToolbar();
            }
        }
    }

    private void lockToolbar() {
        int i = 0;
        while (i < mNavigationView.getMenu().size()) {
            mNavigationView.getMenu().getItem(i ++).setEnabled(false);
        }
    }
    private void unlockToolbar() {
        int i = 0;
        while (i < mNavigationView.getMenu().size()) {
            mNavigationView.getMenu().getItem(i ++).setEnabled(true);
        }
    }

    private void onSelectMenuItem(MenuItem item) {
        item.setChecked(true);

        switch (item.getItemId()) {
            case R.id.action_qr:
                showQrView();
                break;
            case R.id.action_search:
                showSearchView();
                break;
            case R.id.action_my_card:
                showMyCardView();
                break;
            case R.id.action_cardholder:
                showCardHolderView();
                break;
            case R.id.action_profile:
                showProfileView();
                break;
        }
    }

    @Override
    public void showErrorMessage(int titleRes, int messageRes) {

    }

    @Override
    public void showQrView() {
        pushFragment(new QrFragment());
    }

    @Override
    public void showSearchView() {
        pushFragment(new SearchFragment());
    }

    @Override
    public void showMyCardView() {
        pushFragment(new MyCardFragment());
    }

    @Override
    public void showCardHolderView() {
        pushFragment(new CardHolderFragment());
    }

    @Override
    public void showProfileView() {
        Bundle args = new Bundle();

        args.putBoolean(ProfileFragment.ARG_IS_OWN, true);
        args.putSerializable(ProfileFragment.ARG_USER, mApplication.getUser());

        Fragment fragment = new ProfileFragment();
        fragment.setArguments(args);

        pushFragment(fragment);
    }

    protected void pushFragment(Fragment fragment) {
        if (fragment == null)
            return;

        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager != null) {
            FragmentTransaction ft = fragmentManager.beginTransaction();
            if (ft != null) {
                ft.replace(R.id.fragment_container, fragment);
                ft.commit();
            }
        }
    }
}
