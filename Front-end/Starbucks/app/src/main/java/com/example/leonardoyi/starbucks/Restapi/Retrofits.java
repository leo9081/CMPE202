package com.example.leonardoyi.starbucks.Restapi;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Retrofits {

    Retrofit retrofit;
    public static Retrofits retrofits;
    OperationListener listener;


    private Retrofits(){
        retrofit = new Retrofit.Builder()
                .baseUrl("http://tamedogisnotdog.com/snowtools/external/RestServer/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public static Retrofits getInstance(){
        if(retrofits == null){
            try {
                retrofits = new Retrofits();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return retrofits;
    }

    public Retrofits setListerner(OperationListener listener){
        this.listener = listener;
        return this;
    }

    public void userLogin(String username, String password){
        IUserProfile userProfile = retrofit.create(IUserProfile.class);
        UserProfile us = UserProfile.getInstance();
        us.setUsername(username);
        us.setPassword(password);
        Call call = userProfile.login(us);

        call.enqueue(new retrofit2.Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                System.out.print(response.body());
                if(response!=null){
                    UserProfile.setInstance((UserProfile)response.body());
                    getActivieCard();
                }else {
                    listener.onError(0, "No User Find");
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                System.out.print(t.toString());
                listener.onError(0,"Login Error");
            }
        });
    }

    public void getActivieCard(){
        ICard iCard = retrofit.create(ICard.class);
        UserProfile us = UserProfile.getInstance();

        Call call = iCard.getCurrentUseCard(us.getUserId(),us.getUserToken());

        call.enqueue(new retrofit2.Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                System.out.print(response.body());
                if(response!=null){
                    Card.setInstance((Card)response.body());
                }
                listener.onSuccess(null);
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                System.out.print(t.toString());
                listener.onSuccess(null);
            }
        });
    }

    public void userLogOut(){
        IUserProfile userProfile = retrofit.create(IUserProfile.class);
        Call call = userProfile.logOut(UserProfile.getInstance().getUserId(),UserProfile.getInstance().getUserToken());

        call.enqueue(new retrofit2.Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                System.out.print(response.body());
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                System.out.print(t.toString());
            }
        });
    }

    public void addCard(String cardNumber, String cardCode){

        ICard card = retrofit.create(ICard.class);

        Card c = Card.getInstance();
        c.setUserId(UserProfile.getInstance().getUserId());
        c.setUserToken(UserProfile.getInstance().getUserToken());
        c.setCardCode(cardCode);
        c.setCardNum(cardNumber);
        c.setBalance(20.00);

        Call call = card.addNewCard(c);

        call.enqueue(new retrofit2.Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                System.out.print(response.body());
                if(!response.body().toString().equalsIgnoreCase("1")){
                    listener.onSuccess(null);
                }else{
                    //listener.onError();
                }

            }

            @Override
            public void onFailure(Call call, Throwable t) {
                System.out.print(t.toString());
            }
        });
    }

    public void makePayment(){

        ICard card = retrofit.create(ICard.class);

        UserProfile u = UserProfile.getInstance();

        Call call = card.makePayment(u);

        call.enqueue(new retrofit2.Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                System.out.print(response.body());
                if(!response.body().toString().equalsIgnoreCase("1")){
                    listener.onSuccess(null);
                }else{
                    //listener.onError();
                }

            }

            @Override
            public void onFailure(Call call, Throwable t) {
                System.out.print(t.toString());
            }
        });
    }


    public void getPaymentHistory(){

        IOrders iOrders = retrofit.create(IOrders.class);

        UserProfile u = UserProfile.getInstance();

        Call call = iOrders.getPaymentHistory(u.getUserId(),u.getUserToken());

        call.enqueue(new retrofit2.Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                System.out.print(response.body());

                listener.onSuccess((ArrayList) response.body());


            }

            @Override
            public void onFailure(Call call, Throwable t) {
                System.out.print(t.toString());
            }
        });
    }


}
