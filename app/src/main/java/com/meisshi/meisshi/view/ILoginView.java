package com.meisshi.meisshi.view;

/**
 * Created by DevAg on 21/08/2017.
 */

public interface ILoginView extends IView {
    void onLoginWithFacebook();
    void onLogin();

    void showMainView();

    String getUsername();
    String getPassword();

    void setUsername(String username);
    void setPassword(String password);

    void lockLogin();
    void unlockLogin();

    void showErrorMessage(String s, String s1);
}
