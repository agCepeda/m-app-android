package com.meisshi.meisshi.model.deserializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.meisshi.meisshi.model.Review;
import com.meisshi.meisshi.model.User;

import java.lang.reflect.Type;

/**
 * Created by DevAg on 29/10/2017.
 */

public class ReviewDeserializer implements JsonDeserializer<Review> {
    @Override
    public Review deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Review review = new Review();
        JsonObject jsonObject = json.getAsJsonObject();

        review.setId(jsonObject.get("id").getAsString());

        //Type userType = new TypeToken<User>(){}.getType();

        if (jsonObject.has("reviewer")) {
             review.setReviewer((User) context.deserialize(jsonObject.get("reviewer"), User.class));
        }
        if (jsonObject.has("user")) {
             review.setUser((User) context.deserialize(jsonObject.get("user"), User.class));
        }
        if (jsonObject.has("comment")) {
            review.setComment(jsonObject.get("comment").getAsString());
        }
        if (jsonObject.has("score")) {
            review.setScore(jsonObject.get("score").getAsInt());
        }
        return review;
    }
}
