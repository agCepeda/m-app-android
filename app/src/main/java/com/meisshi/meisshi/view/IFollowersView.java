package com.meisshi.meisshi.view;

import com.meisshi.meisshi.model.User;

import java.util.ArrayList;

/**
 * Created by agustin on 31/10/2017.
 */
public interface IFollowersView extends IView {
    void setUsers(ArrayList<User> body);

    boolean isForFollowers();

    User getUser();
}