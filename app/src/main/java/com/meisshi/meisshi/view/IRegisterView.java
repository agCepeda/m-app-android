package com.meisshi.meisshi.view;

/**
 * Created by DevAg on 21/08/2017.
 */

public interface IRegisterView extends IView {
    void onRegisterWithFacebook();
    void onRegister();

    String getEmail();
    String getName();
    String getLastName();
    String getPassword();

    void setEmail(String email);
    void setPassword(String password);
    void setName(String name);
    void setLastName(String lastName);

    void lockRegister();
    void unlockRegister();

    void showMainView();

    void showErrorMessage(String s, String s1);
}
