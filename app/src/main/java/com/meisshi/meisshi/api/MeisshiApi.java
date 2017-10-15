package com.meisshi.meisshi.api;

import com.meisshi.meisshi.model.Session;
import com.meisshi.meisshi.model.User;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
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


}
