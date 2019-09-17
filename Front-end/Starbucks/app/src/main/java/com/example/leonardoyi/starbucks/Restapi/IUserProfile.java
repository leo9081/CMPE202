package com.example.leonardoyi.starbucks.Restapi;



import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface IUserProfile {
    @GET("{user}")
    Call<List<UserProfile>> listRepos(@Path("user") String user);

/*    @FormUrlEncoded
    @POST("login")
    Call<UserProfile> login(@Field("username") String username, @Field("password") String password);*/

    @POST("login")
    Call<UserProfile> login(@Body UserProfile userProfile);

    @FormUrlEncoded
    @POST("logOut")
    Call<Integer> logOut(@Field("userId") Integer userId, @Field("userToken") String userToken);
}
