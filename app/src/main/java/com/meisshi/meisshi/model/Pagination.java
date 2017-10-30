package com.meisshi.meisshi.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by DevAg on 27/10/2017.
 */
public class Pagination<Z> {

    @SerializedName("data")
    private Z[] items;

    @SerializedName("total")
    private int total;

    @SerializedName("last_page")
    private int lastPage;

    @SerializedName("per_page")
    private int size;

    @SerializedName("current_page")
    private int page;


    public Z[] getItems() {
        return items;
    }

    public void setItems(Z[] items) {
        this.items = items;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getLastPage() {
        return lastPage;
    }

    public void setLastPage(int lastPage) {
        this.lastPage = lastPage;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
