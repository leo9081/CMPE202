package com.example.leonardoyi.starbucks.Restapi;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IOrders {

    @GET("getPaymentHistory/{userId}/{userToken}")
    Call<List<Order>> getPaymentHistory(@Path("userId") int userId,@Path("userToken") String userToken);

}
