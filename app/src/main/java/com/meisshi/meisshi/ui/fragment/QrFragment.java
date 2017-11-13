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
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;

import com.meisshi.meisshi.MeisshiApp;
import com.meisshi.meisshi.R;
import com.meisshi.meisshi.model.User;
import com.meisshi.meisshi.ui.activity.ProfileActivity;

import github.nisrulz.qreader.QRDataListener;
import github.nisrulz.qreader.QREader;

/**
 * Created by DevAg on 05/09/2017.
 */
public class QrFragment extends BaseFragment {

    private SurfaceView mSvCamera;
    private QREader mQrReader;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_qr, null);

        mSvCamera = (SurfaceView) view.findViewById(R.id.camera_view);

        /*


        */

        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        //mQrReader.releaseAndCleanup();
    }

    @Override
    public void onStart() {
        super.onStart();

        askForPermissions();
    }

    public void loadCamera() {
        mQrReader = new QREader.Builder(this.getContext(), mSvCamera, new QRDataListener() {
            @Override
            public void onDetected(final String data) {
                Log.d("QREader", "Value : " + data);

                if (data.contains(MeisshiApp.MEISSHI_API_END_POINT)) {
                    Uri profileUrl = Uri.parse(data);

                    User user = new User();
                    user.setId(profileUrl.getLastPathSegment());

                    showProfile(user);
                } else {
                    showMessageError("Error", "Error");
                }

            }
        }).facing(QREader.BACK_CAM)
                .enableAutofocus(true)
                .height(mSvCamera.getHeight())
                .width(mSvCamera.getWidth())
                .build();

        mQrReader.initAndStart(mSvCamera);
        mQrReader.start();
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
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, 1);
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
}
