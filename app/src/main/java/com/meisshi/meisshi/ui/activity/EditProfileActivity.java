package com.meisshi.meisshi.ui.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.MenuItem;

import com.meisshi.meisshi.R;
import com.meisshi.meisshi.model.Card;
import com.meisshi.meisshi.model.User;
import com.meisshi.meisshi.ui.fragment.PersonalFragment;
import com.meisshi.meisshi.ui.fragment.SelectCardFragment;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Response;

/**
 * Created by agustin on 02/11/2017.
 */

public class EditProfileActivity extends BaseActivity {

    public static final String ARG_SECTION = "ARG_SECTION";

    public static final String SECTION_SELECT_CARD = "SELECT_CARD";
    public static final String SECTION_PERSONAL = "PERSONAL";

    private PersonalFragment mPersonalFragment;
    private SelectCardFragment mSelectfragment;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        mApplicationComponent.inject(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String section = getIntent().getStringExtra(ARG_SECTION);

        if (section != null && section.equals(SECTION_SELECT_CARD)) {
            showSelectCard();
        } else {
            showPersonal();
        }
    }

    private void showPersonal() {
        mPersonalFragment = new PersonalFragment();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, mPersonalFragment)
                .commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == android.R.id.home) {
            close();
            return true;
        }
        if (menuItem.getItemId() == R.id.action_next) {
            showSelectCard();
            return true;
        }
        if (menuItem.getItemId() == R.id.action_save) {
            save();
            return true;
        }
        return false;// super.onOptionsItemSelected(menuItem);
    }

    private void save() {
        Card card = mSelectfragment.getCard();

        if (card == null) {
            showErrorMessage("Error", "You must select a card to continue.");
        } else {
            showProgress();
            final HashMap<String, Object> params = mPersonalFragment != null ?
                    mPersonalFragment.getEdited() : new HashMap<String, Object>();
            params.put("card", card.getId());

            final MultipartBody.Part logoPart = partFromImage(
                    mPersonalFragment != null ?
                            mPersonalFragment.getUriLogo() : null,
                    "logo");
            final MultipartBody.Part profilePart = partFromImage(
                    mPersonalFragment != null ?
                            mPersonalFragment.getUriProfile() : null,
                    "profile_picture");

            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Response<User> result = mApi.saveProfile(params).execute();

                        if (result.isSuccessful()) {
                            mApplication.getSession().setUser(result.body());
                        }

                        if (logoPart != null || profilePart != null) {
                            result = mApi.saveProfile(profilePart, logoPart).execute();
                            if (result.isSuccessful()) {
                                mApplication.getSession().setUser(result.body());
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    hideProgress();
                    backToMain();
                }
            });

            t.start();

        }
    }

    private void backToMain() {
        this.finish();
    }

    private void showProgress() {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Save changes...");

        mProgressDialog.show();
    }

    private void hideProgress() {
        mProgressDialog.dismiss();
    }

    private MultipartBody.Part partFromImage(Uri uri, String name){
        try {
            InputStream in = getContentResolver().openInputStream(uri);
            RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), toByteArray(in));
            MultipartBody.Part body = MultipartBody.Part.createFormData(name, name, reqFile);
            return body;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static byte[] toByteArray(InputStream is) throws IOException{
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int reads = is.read();
        while(reads != -1) {
            baos.write(reads); reads = is.read();
        }
        return baos.toByteArray();
    }

    private void close() {
        Log.i("EditProfile", "Close");
        if (! getSupportFragmentManager().popBackStackImmediate()) {
            finish();
        }

        if (mPersonalFragment == null) {
            showPersonal();
        }
    }

    private void showSelectCard() {
        List<String> errors = mPersonalFragment != null ?
                mPersonalFragment.validate() : new ArrayList<String>();

        if (! errors.isEmpty()) {
            StringBuilder builder = new StringBuilder();
            for (String s : errors) {
                builder.append(s).append("\n");
            }
            showErrorMessage("Validation", builder.toString());
        } else {
            mSelectfragment = new SelectCardFragment();

            Bundle args = new Bundle();

            args.putSerializable(
                    SelectCardFragment.ARG_USER,
                    mPersonalFragment != null ?
                            mPersonalFragment.getEditedUser() :
                            mApplication.getUser()
            );

            mSelectfragment.setArguments(args);

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, mSelectfragment, "SelectCard")
                    .addToBackStack(null)
                    .commit();
        }
    }

    private void showErrorMessage(String title, String message) {
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .create()
                .show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
