package com.meisshi.meisshi.model.serializer;

import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.meisshi.meisshi.model.Card;

import java.lang.reflect.Type;

/**
 * Created by DevAg on 05/09/2017.
 */

public class CardSerializer
    implements JsonSerializer<Card> {

    @Override
    public JsonElement serialize(Card src, Type typeOfSrc, JsonSerializationContext context) {
        return null;
    }
}
