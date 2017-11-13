package com.meisshi.meisshi.model;

import java.io.Serializable;

/**
 * Created by DevAg on 04/09/2017.
 */

public class Card implements Serializable {
    CardField profession;
    CardField email;
    CardField telephone1;
    CardField telephone2;
    CardField twitter;
    CardField facebook;
    CardField website;
    private CardField showName;
    private CardField address;
    private CardField company;
    private CardField logo;
    private CardField workEmail;
    private String imageUrl;
    private String id;

    public CardField getShowName() {
        return showName;
    }

    public CardField getProfession() {
        return profession;
    }

    public void setProfession(CardField profession) {
        this.profession = profession;
    }

    public CardField getEmail() {
        return email;
    }

    public void setEmail(CardField email) {
        this.email = email;
    }

    public CardField getTelephone1() {
        return telephone1;
    }

    public void setTelephone1(CardField telephone1) {
        this.telephone1 = telephone1;
    }

    public CardField getTelephone2() {
        return telephone2;
    }

    public void setTelephone2(CardField telephone2) {
        this.telephone2 = telephone2;
    }

    public CardField getTwitter() {
        return twitter;
    }

    public void setTwitter(CardField twitter) {
        this.twitter = twitter;
    }

    public CardField getFacebook() {
        return facebook;
    }

    public void setFacebook(CardField facebook) {
        this.facebook = facebook;
    }

    public CardField getWebsite() {
        return website;
    }

    public void setWebsite(CardField website) {
        this.website = website;
    }

    public void setShowName(CardField showName) {
        this.showName = showName;
    }


    public void setAddress(CardField address) {
        this.address = address;
    }

    public CardField getAddress() {
        return address;
    }

    public void setCompany(CardField company) {
        this.company = company;
    }

    public CardField getCompany() {
        return company;
    }

    public void setLogo(CardField logo) {
        this.logo = logo;
    }

    public CardField getLogo() {
        return logo;
    }

    public void setWorkEmail(CardField workEmail) {
        this.workEmail = workEmail;
    }

    public CardField getWorkEmail() {
        return workEmail;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
