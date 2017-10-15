package com.meisshi.meisshi.model.deserializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.meisshi.meisshi.model.Session;
import com.meisshi.meisshi.model.User;

import java.lang.reflect.Type;

/**
 * Created by DevAg on 04/09/2017.
 */

public class SessionDeserializer
        implements JsonDeserializer<Session> {
    @Override
    public Session deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {

        JsonObject jsonObject = json.getAsJsonObject();

        Session session = new Session();

        if (jsonObject.has("token")) {
            session.setToken(jsonObject.get("token").getAsString());
        }
        if (jsonObject.has("user")) {
            User user = context
                    .deserialize(jsonObject.getAsJsonObject("user"), User.class);
            session.setUser(user);
        }

        return session;
    }
}
