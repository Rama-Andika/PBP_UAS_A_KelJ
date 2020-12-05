package com.example.tubes.API;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RoomResponse {
    @SerializedName("data")
    @Expose
    private List<RoomDAO> rooms = null;

    @SerializedName("message")
    @Expose
    private  String message;

    public  List<RoomDAO> getRooms() {return rooms;}

    public String getMessage() { return message;}

    public  void  setRooms(List<RoomDAO> users) { this.rooms = users;}

    public void setMessage(String message) { this.message = message;}
}
