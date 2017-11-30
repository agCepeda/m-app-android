package com.meisshi.meisshi.model;

import java.io.Serializable;

/**
 * Created by DevAg on 26/11/2017.
 */

public class Error implements Serializable {

    private String message;
    private String error;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
