package com.meisshi.meisshi.ui.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.meisshi.meisshi.R;
import com.meisshi.meisshi.model.User;
import com.meisshi.meisshi.presenter.EditProfilePresenter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by DevAg on 05/11/2017.
 */

public class PersonalFragment extends BaseFragment {

    private static final int PICK_PROFILE_IMAGE = 1;
    private static final int PICK_LOGO_IMAGE = 2;

    private ImageView mImvLogo;
    private ImageView mImvProfile;
    private EditProfilePresenter mPresenter;
    private Picasso mImageLoader;
    private EditText mEtBio;
    private EditText mEtInstagram;
    private EditText mEtTelephone;
    private EditText mEtFirstName;
    private EditText mEtLastName;
    private TextView mBtnProfession;
    private EditText mEtWorkEmail;
    private EditText mEtWebsite;
    private EditText mEtFacebook;
    private EditText mEtTwitter;
    private EditText mEtStreet;
    private EditText mEtNumber;
    private EditText mEtNeighborhood;
    private EditText mEtCity;
    private EditText mEtZipCode;
    private Uri mUriLogo;
    private Uri mUriProfile;
    private User mUser;
    private ArrayList<String> mProfessionIds;
    private ArrayList<String> mProfessionNames;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_personal, null);

        setHasOptionsMenu(true);

        mImvProfile = (ImageView) view.findViewById(R.id.imvProfile);
        mImvLogo = (ImageView) view.findViewById(R.id.imvLogo);

        mEtFirstName = (EditText) view.findViewById(R.id.etFirstName);
        mEtLastName = (EditText) view.findViewById(R.id.etLastName);
        mEtTelephone = (EditText) view.findViewById(R.id.etTelephone);
        mBtnProfession = (TextView) view.findViewById(R.id.tvProfession);
        mEtWorkEmail = (EditText) view.findViewById(R.id.etWorkEmail);

        mEtWebsite = (EditText) view.findViewById(R.id.etWebsite);
        mEtFacebook = (EditText) view.findViewById(R.id.etFacebook);
        mEtTwitter = (EditText) view.findViewById(R.id.etTwitter);
        mEtInstagram = (EditText) view.findViewById(R.id.etInstagram);
        mEtBio = (EditText) view.findViewById(R.id.etBio);

        mEtStreet = (EditText) view.findViewById(R.id.etStreet);
        mEtNumber = (EditText) view.findViewById(R.id.etNumber);
        mEtNeighborhood = (EditText) view.findViewById(R.id.etNeighborhood);
        mEtCity = (EditText) view.findViewById(R.id.etCity);
        mEtZipCode = (EditText) view.findViewById(R.id.etZipCode);

        mImvLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectLogoImage();
            }
        });

        mImvProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectProfileImage();
            }
        });
        mBtnProfession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSelectProfession();
            }
        });

        return view;
    }

    private void showSelectProfession() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                getContext(),
                android.R.layout.simple_spinner_item,
                mProfessionNames
        );

        AlertDialog dialog = new AlertDialog.Builder(getContext())
                .setTitle(R.string.professions_dialog_title)
                .setAdapter(adapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        setProfessionByIndex(i);
                    }
                })
                .create();

        dialog.show();


    }

    private void setProfessionByIndex(int i) {
        mUser.setProfession(mProfessionNames.get(i));
        mUser.setProfessionId(mProfessionIds.get(i));

        mBtnProfession.setText(mUser.getProfession());
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setup();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    private void selectProfileImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_PROFILE_IMAGE);
    }

    private void selectLogoImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_LOGO_IMAGE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == PICK_LOGO_IMAGE) {
            mUser.setLogo(data.getDataString());
            mImageLoader.load(data.getDataString()).into(mImvLogo);
        } else if (requestCode == PICK_PROFILE_IMAGE) {
            mUser.setProfilePicture(data.getDataString());
            mImageLoader.load(data.getDataString()).into(mImvProfile);
        }
    }
    public void setup() {
        mImageLoader = Picasso.with(getContext());
        mApplicationComponent.inject(this);

        if (mUser != null) {
            mUser = new User(mUser);
        } else  {
            mUser = new User(mApplication.getUser());
        }

        mImageLoader.load(mUser.getProfilePicture()).into(mImvProfile);
        mImageLoader.load(mUser.getLogo()).into(mImvLogo);

        mEtFirstName.setText(mUser.getFirstName());
        mEtLastName.setText(mUser.getLastName());
        mEtTelephone.setText(mUser.getTelephone1());
        mBtnProfession.setText(mUser.getProfession());
        //mSpnProfession.setText(mUser.getProfession());
        mEtWorkEmail.setText(mUser.getWorkEmail());

        mEtWebsite.setText(mUser.getWebsite());
        mEtFacebook.setText(mUser.getFacebook());
        mEtTwitter.setText(mUser.getTwitter());
        mEtInstagram.setText(mUser.getInstagram());
        mEtBio.setText(mUser.getBio());

        mEtStreet.setText(mUser.getStreet());
        mEtNumber.setText(mUser.getNumber());
        mEtNeighborhood.setText(mUser.getNeighborhood());
        mEtCity.setText(mUser.getCity());
        mEtZipCode.setText(mUser.getZipCode());

        mApi.getProfessions().enqueue(new Callback<ArrayList<HashMap<String, Object>>>() {
            @Override
            public void onResponse(Call<ArrayList<HashMap<String, Object>>> call, Response<ArrayList<HashMap<String, Object>>> response) {
                if (response.isSuccessful()) {
                    mProfessionIds = new ArrayList<String>();
                    mProfessionNames = new ArrayList<String>();
                    for (HashMap<String, Object> obj:response.body()) {
                        mProfessionIds.add(obj.get("id").toString());
                        mProfessionNames.add(obj.get("name").toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<HashMap<String, Object>>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.edit_profile_personal, menu);
    }

    public User getEditedUser() {

        mUser.setFirstName(mEtFirstName.getText().toString());
        mUser.setLastName(mEtLastName.getText().toString());
        mUser.setTelephone1(mEtTelephone.getText().toString());
        // PROFESSION
        mUser.setWorkEmail(mEtWorkEmail.getText().toString());
        mUser.setWebsite(mEtWebsite.getText().toString());
        mUser.setFacebook(mEtFacebook.getText().toString());
        mUser.setTwitter(mEtTwitter.getText().toString());
        mUser.setInstagram(mEtInstagram.getText().toString());
        mUser.setBio(mEtBio.getText().toString());

        mUser.setStreet(mEtStreet.getText().toString());
        mUser.setNumber(mEtNumber.getText().toString());
        mUser.setNeighborhood(mEtNeighborhood.getText().toString());
        mUser.setCity(mEtCity.getText().toString());
        mUser.setZipCode(mEtZipCode.getText().toString());

        return mUser;
    }
}
