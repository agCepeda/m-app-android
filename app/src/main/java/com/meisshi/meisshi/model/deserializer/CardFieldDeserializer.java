package com.meisshi.meisshi.model.deserializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.meisshi.meisshi.model.CardField;

import java.lang.reflect.Type;

/**
 * Created by DevAg on 05/09/2017.
 */

public class CardFieldDeserializer
    implements JsonDeserializer<CardField> {
    @Override
    public CardField deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();

        CardField cardField = new CardField();

        cardField.setName(jsonObject.get("name").getAsString());

        cardField.setX(jsonObject.get("x").getAsInt());
        cardField.setY(jsonObject.get("y").getAsInt());
        cardField.setWidth(jsonObject.get("width").getAsInt());
        cardField.setHeight(jsonObject.get("height").getAsInt());

        cardField.setAlign(jsonObject.get("align").getAsString());
        cardField.setStyle(jsonObject.get("style").getAsString());
        cardField.setFontSize(jsonObject.get("font_size").getAsInt());
        cardField.setColor(jsonObject.get("color").getAsString());

        return cardField;
    }
}
