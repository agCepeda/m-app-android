package com.meisshi.meisshi.api;

import com.meisshi.meisshi.model.Pagination;
import com.meisshi.meisshi.model.Review;
import com.meisshi.meisshi.model.Session;
import com.meisshi.meisshi.model.User;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MeisshiApi {

    @FormUrlEncoded
    @POST("auth/login")
    Call<Session> login(
            @Field("username") String username,
            @Field("password") String password
    );

    @GET("auth/check-session")
    Call<User> checkSession();

    @FormUrlEncoded
    @POST("auth/sign-up")
    Call<Session> signUp(
            @Field("email") String email,
            @Field("password") String password,
            @Field("name") String name,
            @Field("last_name") String lastName
    );

    @GET("user")
    Call<HashMap<String, Object>> search(@Query("q") String q);

    @GET("user/{user_id}")
    Call<User> getUser(@Path("user_id") String id);

    @GET("user/{user_id}/review")
    Call<Pagination<Review>> loadReviews(
            @Path("user_id") String userId,
            @Query("size") int size,
            @Query("page") int page
    );

    @POST("contact")
    @FormUrlEncoded
    Call<Void> addContact(@Field("contact_id") String userId);

    @DELETE("contact/{user_id}")
    Call<Void> removeContact(@Path("user_id") String userId);
}
