package com.meisshi.meisshi.ui.fragment;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.meisshi.meisshi.MeisshiApp;
import com.meisshi.meisshi.model.User;
import com.meisshi.meisshi.ui.activity.ProfileActivity;

import me.dm7.barcodescanner.zbar.Result;
import me.dm7.barcodescanner.zbar.ZBarScannerView;

/**
 * Created by DevAg on 05/09/2017.
 */
public class QrFragment extends BaseFragment
    implements ZBarScannerView.ResultHandler {

    public static final int REQUEST_CODE_CAMERA_PERMISSION = 123;

    private ZBarScannerView mScannerView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mScannerView = new ZBarScannerView(getActivity());

        return mScannerView;
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mScannerView.isActivated()) {
            mScannerView.stopCamera();
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        askForPermissions();
    }

    public void loadCamera() {
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();          // Start camera on resume
    }

    private void showMessageError(String title, String message) {
        new AlertDialog.Builder(getActivity())
                .setTitle(title)
                .setMessage(message)
                .create()
                .show();
    }

    public void askForPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(getActivity(),Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, REQUEST_CODE_CAMERA_PERMISSION);
            } else {
                loadCamera();
            }
        }
    }

    private void showProfile(User user) {
        Intent i = new Intent(this.getContext(), ProfileActivity.class);

        Bundle args = new Bundle();
        args.putSerializable(ProfileActivity.ARG_USER, user);

        i.putExtras(args);

        startActivity(i);
    }

    @Override
    public void handleResult(Result result) {
        Log.d("QREader", "Value : " + result.getContents());
        Log.d("QREader", "Value : " + MeisshiApp.MEISSHI_API_END_POINT);

        String data = result.getContents();

        if (data.contains(MeisshiApp.MEISSHI_API_END_POINT)) {

            Uri profileUrl = Uri.parse(data);
            Log.d("QREader", "OPEN Meisshi Profile: " + profileUrl.getLastPathSegment());

            User user = new User();
            user.setId(profileUrl.getLastPathSegment());

            showProfile(user);
        } else {
            Log.d("QREader", "EERRORR ASDASDASDASD!");
            mScannerView.resumeCameraPreview(this);
        }
    }
}
