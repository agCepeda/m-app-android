package com.meisshi.meisshi.view;

import com.meisshi.meisshi.model.User;

import java.util.ArrayList;

/**
 * Created by DevAg on 06/09/2017.
 */

public interface ISearchView extends IView {
    String getFilter();
    void addUsers(User[] users);
}
