package com.example.tubes.API;

import com.google.gson.annotations.SerializedName;

public class RoomDAO {
    @SerializedName("id")
    private  String id;

    @SerializedName("jenis_kamar")
    private  String jenis_kamar;

    @SerializedName("harga_kamar")
    private  String harga_kamar;

    @SerializedName("layanan")
    private  String layanan;

    public RoomDAO(String id, String jenis_kamar, String harga_kamar, String layanan) {
        this.id = id;
        this.jenis_kamar = jenis_kamar;
        this.harga_kamar = harga_kamar;
        this.layanan = layanan;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJenis_kamar() {
        return jenis_kamar;
    }

    public void setJenis_kamar(String jenis_kamar) {
        this.jenis_kamar = jenis_kamar;
    }

    public String getHarga_kamar() {
        return harga_kamar;
    }

    public void setHarga_kamar(String harga_kamar) {
        this.harga_kamar = harga_kamar;
    }

    public String getLayanan() {
        return layanan;
    }

    public void setLayanan(String layanan) {
        this.layanan = layanan;
    }
}
