package com.meisshi.meisshi.model;

/**
 * Created by DevAg on 20/08/2017.
 */

public class Session {
    private String token;
    private User user;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
