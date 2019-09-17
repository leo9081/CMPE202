package com.example.leonardoyi.starbucks.Restapi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Order {

    @SerializedName("paymentId")
    @Expose
    private Integer paymentId;

    @SerializedName("userId")
    @Expose
    private Integer userId;

    @SerializedName("cardId")
    @Expose
    private Integer cardId;

    @SerializedName("cardNum")
    @Expose
    private String cardNum;

    @SerializedName("cost")
    @Expose
    private Double cost;

    @SerializedName("paymentTime")
    @Expose
    private String paymentTime;

    public Integer getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Integer paymentId) {
        this.paymentId = paymentId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCardId() {
        return cardId;
    }

    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public String getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(String paymentTime) {
        this.paymentTime = paymentTime;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }
}
