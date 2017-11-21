package com.meisshi.meisshi.ui.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.meisshi.meisshi.R;
import com.meisshi.meisshi.ui.fragment.CardHolderFragment;
import com.meisshi.meisshi.ui.fragment.MyCardFragment;
import com.meisshi.meisshi.ui.fragment.ProfileFragment;
import com.meisshi.meisshi.ui.fragment.QrFragment;
import com.meisshi.meisshi.ui.fragment.SearchFragment;
import com.meisshi.meisshi.util.FontManager;
import com.meisshi.meisshi.view.IMainView;

import static com.meisshi.meisshi.ui.fragment.QrFragment.REQUEST_CODE_CAMERA_PERMISSION;

/**
 * Created by DevAg on 02/09/2017.
 */

public class MainActivity extends BaseActivity
    implements IMainView {

    private static final int REQUEST_CODE_GEOLOCATION = 201;
    private BottomNavigationView mNavigationView;
    private int mCurrentAction;
    private Fragment mFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setup();
    }

    @Override
    public void setup() {
        mApplicationComponent.inject(this);
        mNavigationView = (BottomNavigationView)
                findViewById(R.id.bottom_navigation_view);

        TextView tvToolbarTitle =  (TextView) findViewById(R.id.tvToolbarTitle);
        Typeface font = FontManager.getTypeface(getApplicationContext(), FontManager.MEISSHI_FONT);
        tvToolbarTitle.setTypeface(font, Typeface.NORMAL);

        ImageButton btnOptions = (ImageButton) findViewById(R.id.btn_options);
        ImageButton btnNotifications = (ImageButton) findViewById(R.id.btn_notifications);

        btnOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showOptions();
            }
        });
        btnNotifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showNotificationsView();
            }
        });

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
        }

        askForGeolocalizationPermission();
    }

    private void showOptions() {
        CharSequence options[] = new CharSequence[] {"Logout"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select option");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                logout();
            }
        });
        builder.show();
    }

    private void logout() {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.remove("SESSION_TOKEN");

        editor.commit();

        mApplication.setUser(null);

        showSplashView();
        finish();
    }

    private void showSplashView() {
        Intent i = new Intent(this, SplashActivity.class);
        startActivity(i);
    }

    private void showNotificationsView() {
        Intent i = new Intent(this, NotificationsActivity.class);
        startActivity(i);
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

        if (mCurrentAction == item.getItemId()) {
            return;
        }

        mCurrentAction = item.getItemId();

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

        mFragment = fragment;

        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager != null) {
            FragmentTransaction ft = fragmentManager.beginTransaction();
            if (ft != null) {
                ft.replace(R.id.fragment_container, fragment);
                ft.commit();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkUser();
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_CAMERA_PERMISSION) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                if (mFragment instanceof QrFragment) {
                    ((QrFragment) mFragment).loadCamera();
                }
            }
        } else if (requestCode == REQUEST_CODE_GEOLOCATION) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                loadGeolocation();
            }
        }
    }

    private void askForGeolocalizationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE_GEOLOCATION);
            } else {
                loadGeolocation();
            }
        }
    }

    private void loadGeolocation() {

    }

    public void checkUser() {
        if (mApplication.getUser().getCard() == null) {
            showEditProfileView();
            lockToolbar();
        } else {
            unlockToolbar();
        }
    }

    private void showEditProfileView() {
        Intent i = new Intent(this, EditProfileActivity.class);
        startActivity(i);
    }
}
