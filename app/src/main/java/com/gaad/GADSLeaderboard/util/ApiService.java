package com.gaad.GADSLeaderboard.util;

import com.gaad.GADSLeaderboard.model.Learning;
import com.gaad.GADSLeaderboard.model.Skill_IQ;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiService {

    @GET("/api/hours/")
    Call<List<Learning>> getDataHours();

    @GET("/api/skilliq/")
    Call<List<Skill_IQ>> getDataSkill();


    @Headers(value = {"Content-Type: application/x-www-form-urlencoded"})

    @FormUrlEncoded
    @POST("1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse")
    Call<Void> postValues(
            @Field("entry.1824927963") String email,
            @Field("entry.1877115667") String fname,
            @Field("entry.2006916086") String lname,
            @Field("entry.284483984") String githublink
    );


}
