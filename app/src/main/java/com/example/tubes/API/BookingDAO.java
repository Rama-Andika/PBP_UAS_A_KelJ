package com.example.tubes.API;

import com.google.gson.annotations.SerializedName;

public class BookingDAO {
    @SerializedName("id")
    private  String id;

    @SerializedName("nama_pelanggan")
    private  String nama_pelanggan;

    @SerializedName("jenis_room")
    private  String room;

    @SerializedName("tanggal")
    private  String date;

    @SerializedName("jml_orang_dewasa")
    private  String jml_dewasa;

    @SerializedName("jml_anak_kecil")
    private  String jml_kecil;

    public BookingDAO(String id, String nama_pelanggan, String room, String date, String jml_dewasa, String jml_kecil) {
        this.id = id;
        this.nama_pelanggan = nama_pelanggan;
        this.room = room;
        this.date = date;
        this.jml_dewasa = jml_dewasa;
        this.jml_kecil = jml_kecil;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama_pelanggan() {
        return nama_pelanggan;
    }

    public void setNama_pelanggan(String nama_pelanggan) {
        this.nama_pelanggan = nama_pelanggan;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getJml_dewasa() {
        return jml_dewasa;
    }

    public void setJml_dewasa(String jml_dewasa) {
        this.jml_dewasa = jml_dewasa;
    }

    public String getJml_kecil() {
        return jml_kecil;
    }

    public void setJml_kecil(String jml_kecil) {
        this.jml_kecil = jml_kecil;
    }


}
