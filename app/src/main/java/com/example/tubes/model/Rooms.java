package com.example.tubes.model;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;

public class Rooms {


    public String nama;
    public String fasilitas;
    public int harga;
    public String imgURL;

    public Rooms(String nama, String fasilitas, int harga, String imgURL)
    {
        this.nama = nama;
        this.fasilitas = fasilitas;
        this.harga = harga;
        this.imgURL =imgURL;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getFasilitas(){
        return fasilitas;
    }

    public void setFasilitas(String fasilitas){
        this.fasilitas = fasilitas;
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public String getStringHarga(){
        return String.valueOf(harga);
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }


    @BindingAdapter("android:loadImage")
    public static void loadImage(ImageView imageView, String imgURL){
        Glide.with(imageView)
                .load(imgURL)
                .into(imageView);
    }
}
