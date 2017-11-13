package com.meisshi.meisshi.model.deserializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.meisshi.meisshi.model.Card;
import com.meisshi.meisshi.model.CardField;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by DevAg on 05/09/2017.
 */

public class CardDeserializer
    implements JsonDeserializer<Card> {

    @Override
    public Card deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();

        Card card = new Card();

        if (jsonObject.has("id") && !jsonObject.get("id").isJsonNull()) {
            card.setId(jsonObject.get("id").getAsString());
        }
        if (jsonObject.has("path") && !jsonObject.get("path").isJsonNull()) {
            card.setImageUrl(jsonObject.get("path").getAsString());
        }

        if (jsonObject.has("fields") && ! jsonObject.get("fields").isJsonNull()) {
            for(JsonElement element: jsonObject.get("fields").getAsJsonArray()) {
                CardField field = context.deserialize(element, CardField.class);

                if (field.getName().equals("show_name")) {
                    card.setShowName(field);
                }
                if (field.getName().equals("profession")) {
                    card.setProfession(field);
                }
                if (field.getName().equals("telephone1")) {
                    card.setTelephone1(field);
                }
                if (field.getName().equals("telephone2")) {
                    card.setTelephone2(field);
                }
                if (field.getName().equals("address")) {
                    card.setAddress(field);
                }
                if (field.getName().equals("company")) {
                    card.setCompany(field);
                }
                if (field.getName().equals("website")) {
                    card.setWebsite(field);
                }
                if (field.getName().equals("facebook")) {
                    card.setFacebook(field);
                }
                if (field.getName().equals("twitter")) {
                    card.setTwitter(field);
                }
                if (field.getName().equals("logo")) {
                    card.setLogo(field);
                }
                if (field.getName().equals("work_email")) {
                    card.setWorkEmail(field);
                }
            }
        }

        return card;
    }

}
