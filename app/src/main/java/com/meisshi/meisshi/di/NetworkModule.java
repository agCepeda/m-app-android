package com.meisshi.meisshi.di;

import android.app.Application;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.meisshi.meisshi.MeisshiApp;
import com.meisshi.meisshi.api.MeisshiApi;
import com.meisshi.meisshi.model.Card;
import com.meisshi.meisshi.model.CardField;
import com.meisshi.meisshi.model.Session;
import com.meisshi.meisshi.model.User;
import com.meisshi.meisshi.model.deserializer.CardDeserializer;
import com.meisshi.meisshi.model.deserializer.CardFieldDeserializer;
import com.meisshi.meisshi.model.deserializer.SessionDeserializer;
import com.meisshi.meisshi.model.deserializer.UserDeserializer;
import com.meisshi.meisshi.model.serializer.CardSerializer;
import com.meisshi.meisshi.model.serializer.SessionSerializer;
import com.meisshi.meisshi.model.serializer.UserSerializer;

import java.io.IOException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    private String mBaseUrl;

    public NetworkModule(String baseUrl) {
        this.mBaseUrl = baseUrl;
    }

    @Provides
    @Singleton
    Cache providesHttpCache(MeisshiApp application) {
        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        Cache cache = new Cache(application.getCacheDir(), cacheSize);
        return cache;
    }

    @Provides
    Interceptor providesInterceptor(final SharedPreferences sharedPreferences) {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();

                String sessionToken = sharedPreferences.getString("SESSION_TOKEN", null);

                if (sessionToken != null) {
                     request = request.newBuilder()
                             .addHeader("SESSION-TOKEN", sessionToken)
                             .build();
                }

                Response response = chain.proceed(request);


                Log.w("NETWORK", request.url().toString());
                Log.w("NETWORK - Status Code", response.code() + "");
                for (String name: response.headers().names()) {
                    Log.w("NETWORK - " + name, response.header(name));
                }

                return  response;
            }
        };
    }

    @Provides
    OkHttpClient providesHttpClient(Interceptor interceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();
    }

    @Provides
    @Singleton
    Gson providesGson() {
        return new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .registerTypeAdapter(Session.class, new SessionSerializer())
                .registerTypeAdapter(Session.class, new SessionDeserializer())
                .registerTypeAdapter(User.class, new UserSerializer())
                .registerTypeAdapter(User.class, new UserDeserializer())
                .registerTypeAdapter(Card.class, new CardDeserializer())
                .registerTypeAdapter(CardField.class, new CardFieldDeserializer())
                .create();
    }

    @Provides
    @Singleton
    Retrofit providesRetrofit(Gson gson, OkHttpClient httpClient) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(this.mBaseUrl)
                .client(httpClient)
                .build();
    }

    @Provides
    @Singleton
    MeisshiApi providesMeisshiApi(Retrofit retrofit) {
        return retrofit.create(MeisshiApi.class);
    }
}
