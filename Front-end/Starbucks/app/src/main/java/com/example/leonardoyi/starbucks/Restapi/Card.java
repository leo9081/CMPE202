package com.example.leonardoyi.starbucks.Restapi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Card {

    @SerializedName("cardNum")
    @Expose
    private String cardNum;

    @SerializedName("cardCode")
    @Expose
    private String cardCode;

    @SerializedName("userId")
    @Expose
    private Integer userId;

    @SerializedName("balance")
    @Expose
    private double balance;

    @SerializedName("userToken")
    @Expose
    private String userToken;

    public static Card card;

    private Card(double balance, String carNumber, String code){
        this.balance = balance;
        this.cardNum = carNumber;
        this.cardCode = code;
    }

    public static Card getInstance(){
        if(card == null){
            String carNumber = "000000000";
            String code = "000";
            card = new Card(0.00,carNumber,code);
        }

        return card;
    }

    public static void setInstance(Card c){
        card = c;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }


    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public String getCardCode() {
        return cardCode;
    }

    public void setCardCode(String cardCode) {
        this.cardCode = cardCode;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }
}
