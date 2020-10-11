package com.example.tubes;

import com.example.tubes.model.Rooms;

import java.util.ArrayList;

public class DaftarRooms {

    public ArrayList<Rooms> ROOMS;

    public DaftarRooms()
    {
        ROOMS = new ArrayList();
        ROOMS.add(STANDAR);
        ROOMS.add(CLASSIC);
        ROOMS.add(DELUXE);
    }

    public static final Rooms STANDAR = new Rooms("Classic", "Ukuran kamar adalah 21 m2\n" +
            "Tempat tidur queen atau twin\n" +
            "TV layar datar 32\"\n" +
            "Shower\n" +
            "Gratis WiFi\n" +
            "Ruang/meja kerja\n", 300000,
            "https://origin.pegipegi.com/jalan/images/pictM/Y1/Y925361/Y925361074.jpg");

    public static final Rooms CLASSIC = new Rooms("Deluxe",
            "AC\n" +
                    "TV\n" +
                    "Mini Bar\n" +
                    "Brankas\n" +
                    "Tempat tidur yang nyaman\n" +
                    "Kamar mandi\n" +
                    "Akses internet\n" +
                    "Bath\n" +
                    "Toilet\n",
            450000,
            "https://origin.pegipegi.com/jalan/images/pictM/Y4/Y990784/Y990784045.jpg");

    public static final Rooms DELUXE = new Rooms("Superior",
            "42 \"lED TV dengan Program TV Internasional & Program Domestik\n" +
                    "Kotak brankas di kamar\n" +
                    "Telepon\n" +
                    "IDD\n" +
                    "Mini-bar\n" +
                    "Fasilitas pembuatan kopi & kopi\n" +
                    "Outlet listrik (110 & 220 volt)\n" +
                    "Jubah mandi & sandal\n" +
                    "Setrika & papan setrika\n",
            999000,
            "https://origin.pegipegi.com/jalan/images/pictM/Y5/Y921895/Y921895065.jpg");
}
