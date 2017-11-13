package com.meisshi.meisshi.model;

import java.io.Serializable;

/**
 * Created by DevAg on 21/08/2017.
 */
public class User implements Serializable {
    private String id;
    private String email;
    private String lastName;
    private String password;

    private Card card;
    private String cardId;
    private String firstName;
    private int followingCount;
    private int followersCount;
    private String facebook;
    private String twitter;
    private String website;
    private String zipCode;
    private String city;
    private String neighborhood;
    private String number;
    private String street;
    private String ocupation;
    private String degree;
    private String position;
    private String company;
    private String telephone2;
    private String telephone1;
    private String workEmail;
    private String profession;
    private String professionId;
    private String logo;
    private String profilePicture;
    private String bio;
    private String instagram;

    private boolean isContact;

    public User() {}

    public User(User user) {
        this.id = user.id;
        this.email = user.email;
        this.firstName = user.firstName;
        this.lastName = user.lastName;
        this.password = user.password;
        this.card = user.card;
        this.cardId = user.cardId;
        this.followingCount = user.followingCount;
        this.followersCount = user.followersCount;
        this.facebook = user.facebook;
        this.twitter = user.twitter;
        this.instagram = user.instagram;

        this.street = user.street;
        this.number = user.number;
        this.neighborhood = user.neighborhood;
        this.city = user.city;
        this.zipCode =user.zipCode;
        this.company = user.company;
        this.telephone1 = user.telephone1;

        this.workEmail = user.workEmail;
        this.profession = user.profession;
        this.professionId = user.professionId;

        this.logo = user.logo;
        this.profilePicture = user.profilePicture;
        this.bio = user.bio;
    }

    public String getQrImage() {
        return qrImage;
    }

    public void setQrImage(String qrImage) {
        this.qrImage = qrImage;
    }

    private String qrImage;

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFollowingCount(int followingCount) {
        this.followingCount = followingCount;
    }

    public int getFollowingCount() {
        return followingCount;
    }

    public void setFollowersCount(int followersCount) {
        this.followersCount = followersCount;
    }

    public int getFollowersCount() {
        return followersCount;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getWebsite() {
        return website;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreet() {
        return street;
    }

    public void setOcupation(String ocupation) {
        this.ocupation = ocupation;
    }

    public String getOcupation() {
        return ocupation;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getDegree() {
        return degree;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPosition() {
        return position;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCompany() {
        return company;
    }

    public void setTelephone2(String telephone2) {
        this.telephone2 = telephone2;
    }

    public String getTelephone2() {
        return telephone2;
    }

    public void setTelephone1(String telephone1) {
        this.telephone1 = telephone1;
    }

    public String getTelephone1() {
        return telephone1;
    }

    public void setWorkEmail(String workEmail) {
        this.workEmail = workEmail;
    }

    public String getWorkEmail() {
        return workEmail;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getProfession() {
        return profession;
    }

    public String getShowName() {
        return firstName + " " + lastName;
    }

    public String getAddress() {
        if (street != null || number != null || neighborhood != null || city != null) {
            String fullAddress = "";

            if (street != null) {
                fullAddress = fullAddress + street;
            }
            if (number != null) {
                fullAddress = fullAddress + " " + number;
            }
            if (city != null) {
                fullAddress = fullAddress + " " + city;
            }

            return fullAddress;
        }
        return null;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public void setProfessionId(String professionId) {
        this.professionId = professionId;
    }

    public boolean isContact() {
        return isContact;
    }

    public void setContact(boolean contact) {
        isContact = contact;
    }

    public String getProfessionId() {
        return professionId;
    }
}
