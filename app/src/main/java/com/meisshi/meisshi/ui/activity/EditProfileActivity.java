package com.meisshi.meisshi.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.MenuItem;

import com.meisshi.meisshi.R;
import com.meisshi.meisshi.ui.fragment.PersonalFragment;
import com.meisshi.meisshi.ui.fragment.SelectCardFragment;

import java.util.HashMap;

/**
 * Created by agustin on 02/11/2017.
 */

public class EditProfileActivity extends BaseActivity {

    private PersonalFragment mPersonalFragment;
    private SelectCardFragment mSelectfragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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

    }

    private void close() {
        Log.i("EditProfile", "Close");
        if (!getSupportFragmentManager().popBackStackImmediate()) {
            finish();
        }
    }

    private void showSelectCard() {
        if (! mPersonalFragment.validate())
            return;
        HashMap<String, Object> mParams = mPersonalFragment.getEdited();

        Log.i("EditProfile", "Show select card");
        mSelectfragment = new SelectCardFragment();

        Bundle args = new Bundle();

        args.putSerializable(SelectCardFragment.ARG_USER, mPersonalFragment.getEditedUser());

        mSelectfragment.setArguments(args);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, mSelectfragment, "SelectCard")
                .addToBackStack(null)
                .commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
