package com.meisshi.meisshi.view;

public interface ISplashView extends IView {
    void showLoginView();
    void showRegisterView();
    void showOptions();
    void showMainView();

    void unlockLogin();
    void lockLogin();

    String getUsername();
    String getPassword();
}
