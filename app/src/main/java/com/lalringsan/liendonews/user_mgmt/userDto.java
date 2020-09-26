package com.lalringsan.liendonews.user_mgmt;

public class userDto {

    String userName;
    String userEmail;
    String phoneNumber;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPhoneNumber() {
        return phoneNumber;
    }

    public void setUserPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public userDto() {

    }

    public userDto(String userName, String userEmail, String phoneNumber, String userAddress) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.phoneNumber = phoneNumber;
        this.userAddress = userAddress;
    }

    String userAddress;


}
