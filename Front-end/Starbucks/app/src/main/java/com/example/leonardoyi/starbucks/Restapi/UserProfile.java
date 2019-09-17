package com.example.leonardoyi.starbucks.Restapi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserProfile {
    @SerializedName("userId")
    @Expose
    private Integer userId;


    @SerializedName("username")
    @Expose
    private String username;

    @SerializedName("pwd")
    @Expose
    private String pwd;

    @SerializedName("firstName")
    @Expose
    private String firstName;

    @SerializedName("lastName")
    @Expose
    private String lastName;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("homeAddress")
    @Expose
    private String homeAddress;

    @SerializedName("homePhone")
    @Expose
    private String homePhone;

    @SerializedName("cellPhone")
    @Expose
    private String cellPhone;

    @SerializedName("userToken")
    @Expose
    private String userToken;

    public static UserProfile userProfile;

    private UserProfile(){}

    public static UserProfile getInstance(){
        if(userProfile == null){
            userProfile = new UserProfile();
        }

        return userProfile;
    }

    public static void setInstance(UserProfile us){
        userProfile = us;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return pwd;
    }

    public void setPassword(String password) {
        this.pwd = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    @Override
    public String toString() {
        return "Post{" +
                "userId='" + userId + '\'' +
                ", username='" + username + '\'' +
                ", password=" + pwd +
                ", firstName=" + firstName +
                '}';
    }
}
