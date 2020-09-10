package com.gaad.GADSLeaderboard.util;

import android.os.Build;

import java.io.IOException;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiBuilder {

    private static final String URL = "https://gadsapi.herokuapp.com";
    private static final String FORM_URL = "https://docs.google.com/forms/d/e/";


    // Create logger
    private static HttpLoggingInterceptor logger = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);

    // Create OkHttp Client
    private static OkHttpClient.Builder okHttp =
            new OkHttpClient.Builder()
                    .readTimeout(15, TimeUnit.SECONDS)
                    .addInterceptor(chain -> {
                        Request request = chain.request();

                        request = request.newBuilder()
                                .addHeader("x-device-type", Build.DEVICE)
                                .addHeader("Accept-Language", Locale.getDefault().getLanguage())
                                .build();

                        return chain.proceed(request);
                    })
                    .addInterceptor(logger);


    //Learner & Skill IQ "Server Connection Initiate"
    private static Retrofit.Builder builder = new Retrofit.Builder().baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttp.build());

    private static Retrofit retrofit = builder.build();

    public static <S> S buildService(Class<S> serviceType) {
        return retrofit.create(serviceType);
    }


    // Form "Server Connection Initiate"
    private static Retrofit.Builder form_builder = new Retrofit.Builder().baseUrl(FORM_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttp.build());

    private static Retrofit form_retrofit = form_builder.build();

    public static   <S> S form_buildService(Class<S> serviceType) { return form_retrofit.create(serviceType); }

}
