package com.meisshi.meisshi.model.deserializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.meisshi.meisshi.model.Card;
import com.meisshi.meisshi.model.Session;
import com.meisshi.meisshi.model.User;

import java.lang.reflect.Type;

/**
 * Created by DevAg on 04/09/2017.
 */

public class UserDeserializer
        implements JsonDeserializer<User> {
    @Override
    public User deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        User user = new User();

        JsonObject jsonObject = json.getAsJsonObject();

        if (jsonObject.has("id")) {
            user.setId(jsonObject.get("id").getAsString());
        }
        if (jsonObject.has("logo")) {
            user.setLogo(jsonObject.get("logo").getAsString());
        }
        if (jsonObject.has("show_name")) {
            user.setShowName(jsonObject.get("show_name").getAsString());
        }
        if (jsonObject.has("first_name")) {
            user.setFirstName(jsonObject.get("first_name").getAsString());
        }
        if (jsonObject.has("last_name")) {
            user.setLastName(jsonObject.get("last_name").getAsString());
        }
        if (jsonObject.has("email")) {
            user.setEmail(jsonObject.get("email").getAsString());
        }
        if (jsonObject.has("work_email")) {
            user.setWorkEmail(jsonObject.get("work_email").getAsString());
        }
        if (jsonObject.has("telephone1")) {
            user.setTelephone1(jsonObject.get("telephone1").getAsString());
        }
        if (jsonObject.has("telephone2")) {
            user.setTelephone2(jsonObject.get("telephone2").getAsString());
        }
        if (jsonObject.has("company")) {
            user.setCompany(jsonObject.get("company").getAsString());
        }
        /*
        if (jsonObject.has("position")) {
            user.setPosition(jsonObject.get("position").getAsString());
        }
        */
        if (jsonObject.has("profession")) {
            user.setProfession(
                    jsonObject
                            .get("profession")
                            .getAsJsonObject()
                            .get("name")
                            .getAsString()
            );
        }
        if (jsonObject.has("degree")) {
            user.setDegree(jsonObject.get("degree").getAsString());
        }
        if (jsonObject.has("ocupation")) {
            user.setOcupation(jsonObject.get("ocupation").getAsString());
        }
        if (jsonObject.has("street")) {
            user.setStreet(jsonObject.get("street").getAsString());
        }
        if (jsonObject.has("number")) {
            user.setNumber(jsonObject.get("number").getAsString());
        }
        if (jsonObject.has("neighborhood")) {
            user.setNeighborhood(jsonObject.get("neighborhood").getAsString());
        }
        if (jsonObject.has("city")) {
            user.setCity(jsonObject.get("city").getAsString());
        }
        if (jsonObject.has("zip_code")) {
            user.setZipCode(jsonObject.get("zip_code").getAsString());
        }
        if (jsonObject.has("website")) {
            user.setWebsite(jsonObject.get("website").getAsString());
        }
        if (jsonObject.has("twitter")) {
            user.setTwitter(jsonObject.get("twitter").getAsString());
        }
        if (jsonObject.has("facebook")) {
            user.setFacebook(jsonObject.get("facebook").getAsString());
        }
        if (jsonObject.has("followers_count")) {
            user.setFollowersCount(jsonObject.get("followers_count").getAsInt());
        }
        if (jsonObject.has("following_count")) {
            user.setFollowingCount(jsonObject.get("following_count").getAsInt());
        }

        if (jsonObject.has("card")) {
            user.setCard(
                    (Card) context.deserialize(jsonObject.get("card"), Card.class)
            );
        }

        return user;
    }
}
