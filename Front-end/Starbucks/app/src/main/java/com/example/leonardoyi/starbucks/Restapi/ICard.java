package com.example.leonardoyi.starbucks.Restapi;

import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ICard {

    @GET("getCurrentUseCard/{userId}/{userToken}")
    Call<Card> getCurrentUseCard(@Path("userId") int userId,@Path("userToken") String userToken);

    @POST("addNewCard")
    Call<JsonObject> addNewCard(@Body Card card);

    @POST("makePayment")
    Call<JsonObject> makePayment(@Body UserProfile userProfile);
}
