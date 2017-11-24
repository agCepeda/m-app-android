package com.meisshi.meisshi.model.deserializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
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

        if (jsonObject.has("id") && ! jsonObject.get("id").isJsonNull()) {
            user.setId(jsonObject.get("id").getAsString());
        }
        if (jsonObject.has("logo") && ! jsonObject.get("logo").isJsonNull()) {
            user.setLogo(jsonObject.get("logo").getAsString());
        }
        if (jsonObject.has("profile_picture") && ! jsonObject.get("profile_picture").isJsonNull()) {
            user.setProfilePicture(jsonObject.get("profile_picture").getAsString());
        }
        if (jsonObject.has("qr_image") && ! jsonObject.get("qr_image").isJsonNull()) {
            user.setQrImage(jsonObject.get("qr_image").getAsString());
        }
        if (jsonObject.has("name") && ! jsonObject.get("name").isJsonNull()) {
            user.setFirstName(jsonObject.get("name").getAsString());
        }
        if (jsonObject.has("bio") && ! jsonObject.get("bio").isJsonNull()) {
            user.setBio(jsonObject.get("bio").getAsString());
        }
        if (jsonObject.has("last_name") && ! jsonObject.get("last_name").isJsonNull()) {
            user.setLastName(jsonObject.get("last_name").getAsString());
        }
        if (jsonObject.has("email") && ! jsonObject.get("email").isJsonNull()) {
            user.setEmail(jsonObject.get("email").getAsString());
        }
        if (jsonObject.has("work_email") && ! jsonObject.get("work_email").isJsonNull()) {
            user.setWorkEmail(jsonObject.get("work_email").getAsString());
        }
        if (jsonObject.has("telephone1") && ! jsonObject.get("telephone1").isJsonNull()) {
            user.setTelephone1(jsonObject.get("telephone1").getAsString());
        }
        if (jsonObject.has("telephone2") && ! jsonObject.get("telephone2").isJsonNull()) {
            user.setTelephone2(jsonObject.get("telephone2").getAsString());
        }
        if (jsonObject.has("company") && ! jsonObject.get("company").isJsonNull()) {
            user.setCompany(jsonObject.get("company").getAsString());
        }
        if (jsonObject.has("contact")) {
            user.setContact(jsonObject.get("contact").getAsBoolean());
        }
        if (jsonObject.has("profession") && ! jsonObject.get("profession").isJsonNull()) {
            user.setProfession(
                    jsonObject
                            .get("profession")
                            .getAsString()
            );
        }
        if (jsonObject.has("degree") && ! jsonObject.get("degree").isJsonNull()) {
            user.setDegree(jsonObject.get("degree").getAsString());
        }
        if (jsonObject.has("ocupation") && ! jsonObject.get("ocupation").isJsonNull()) {
            user.setOcupation(jsonObject.get("ocupation").getAsString());
        }
        if (jsonObject.has("street") && ! jsonObject.get("street").isJsonNull()) {
            user.setStreet(jsonObject.get("street").getAsString());
        }
        if (jsonObject.has("number") && ! jsonObject.get("number").isJsonNull()) {
            user.setNumber(jsonObject.get("number").getAsString());
        }
        if (jsonObject.has("neighborhood") && ! jsonObject.get("neighborhood").isJsonNull()) {
            user.setNeighborhood(jsonObject.get("neighborhood").getAsString());
        }
        if (jsonObject.has("city") && ! jsonObject.get("city").isJsonNull()) {
            user.setCity(jsonObject.get("city").getAsString());
        }
        if (jsonObject.has("zip_code") && ! jsonObject.get("zip_code").isJsonNull()) {
            user.setZipCode(jsonObject.get("zip_code").getAsString());
        }
        if (jsonObject.has("website") && ! jsonObject.get("website").isJsonNull()) {
            user.setWebsite(jsonObject.get("website").getAsString());
        }
        if (jsonObject.has("twitter") && ! jsonObject.get("twitter").isJsonNull()) {
            user.setTwitter(jsonObject.get("twitter").getAsString());
        }
        if (jsonObject.has("facebook") && ! jsonObject.get("facebook").isJsonNull()) {
            user.setFacebook(jsonObject.get("facebook").getAsString());
        }
        if (jsonObject.has("instagram") && ! jsonObject.get("instagram").isJsonNull()) {
            user.setInstagram(jsonObject.get("instagram").getAsString());
        }
        if (jsonObject.has("followers_count") && jsonObject.get("followers_count").isJsonPrimitive()) {
                user.setFollowersCount(jsonObject.get("followers_count").getAsInt());
        }
        if (jsonObject.has("following_count") && jsonObject.get("following_count").isJsonPrimitive()) {
                user.setFollowingCount(jsonObject.get("following_count").getAsInt());
        }

        if (jsonObject.has("card") && jsonObject.get("card").isJsonObject()) {
            user.setCard(
                    (Card) context.deserialize(jsonObject.get("card"), Card.class)
            );
        }

        return user;
    }
}
