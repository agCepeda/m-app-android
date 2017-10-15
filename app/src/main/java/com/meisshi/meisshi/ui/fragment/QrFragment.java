package com.meisshi.meisshi.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;

import com.meisshi.meisshi.R;

import github.nisrulz.qreader.QRDataListener;
import github.nisrulz.qreader.QREader;

/**
 * Created by DevAg on 05/09/2017.
 */
public class QrFragment extends Fragment {

    private SurfaceView mSvCamera;
    private QREader mQrReader;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_qr, null);

        mSvCamera = (SurfaceView) view.findViewById(R.id.camera_view);
        mQrReader = new QREader.Builder(this.getContext(), mSvCamera, new QRDataListener() {
            @Override
            public void onDetected(final String data) {
                Log.d("QREader", "Value : " + data);
                /*
                text.post(new Runnable() {
                    @Override
                    public void run() {
                        text.setText(data);
                    }
                });
                */
            }
        }).facing(QREader.BACK_CAM)
                .enableAutofocus(true)
                .height(mSvCamera.getHeight())
                .width(mSvCamera.getWidth())
                .build();

        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        mQrReader.releaseAndCleanup();
    }

    @Override
    public void onStart() {
        super.onStart();
        mQrReader.initAndStart(mSvCamera);
        mQrReader.start();

    }
}
