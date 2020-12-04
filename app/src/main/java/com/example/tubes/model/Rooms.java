package com.example.tubes.model;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;

public class Rooms {
    private String jenis_kamar, harga_kamar, layanan, image_kamar;

    public Rooms(String jenis_kamar, String harga_kamar, String layanan, String image_kamar) {
        this.jenis_kamar = jenis_kamar;
        this.harga_kamar = harga_kamar;
        this.layanan = layanan;
        this.image_kamar = image_kamar;
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

    public String getImage_kamar() {
        return image_kamar;
    }

    public void setImage_kamar(String image_kamar) {
        this.image_kamar = image_kamar;
    }
}
