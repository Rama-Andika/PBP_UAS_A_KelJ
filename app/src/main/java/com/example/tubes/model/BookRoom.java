package com.example.tubes.model;

public class BookRoom {

        public String name, roomType, date, adult, child;

    public BookRoom() {

    }

    public BookRoom(String name, String roomType, String date, String adult, String child) {
        this.name = name;
        this.roomType = roomType;
        this.date = date;
        this.adult = adult;
        this.child = child;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAdult() {
        return adult;
    }

    public void setAdult(String adult) {
        this.adult = adult;
    }

    public String getChild() {
        return child;
    }

    public void setChild(String child) {
        this.child = child;
    }
}
