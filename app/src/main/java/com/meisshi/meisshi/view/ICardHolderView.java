package com.meisshi.meisshi.view;

import com.meisshi.meisshi.model.User;

import java.util.List;

/**
 * Created by agustin on 30/10/2017.
 */

public interface ICardHolderView extends IView {
    void setContacts(List<User> contacts);
}
