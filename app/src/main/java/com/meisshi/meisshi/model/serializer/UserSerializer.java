package com.meisshi.meisshi.model.serializer;

import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.meisshi.meisshi.model.User;

import java.lang.reflect.Type;

/**
 * Created by DevAg on 04/09/2017.
 */

public class UserSerializer implements JsonSerializer<User> {
    @Override
    public JsonElement serialize(User src, Type typeOfSrc, JsonSerializationContext context) {
        User user = new User();
        return null;
    }
}
