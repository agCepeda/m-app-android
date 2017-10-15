package com.meisshi.meisshi.model.serializer;

import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.meisshi.meisshi.model.Session;

import java.lang.reflect.Type;

/**
 * Created by DevAg on 04/09/2017.
 */

public class SessionSerializer
    implements JsonSerializer<Session> {
    @Override
    public JsonElement serialize(Session src, Type typeOfSrc, JsonSerializationContext context) {
        return null;
    }
}
