package com.example.tubes.API;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserResponse {

    @SerializedName("data")
    @Expose
    private List<BookingDAO> users = null;

    @SerializedName("message")
    @Expose
    private  String message;

    public  List<BookingDAO> getPelanggans() {return users;}

    public String getMessage() { return message;}

    public  void  setPelanggans(List<BookingDAO> users) { this.users = users;}

    public void setMessage(String message) { this.message = message;}
}
