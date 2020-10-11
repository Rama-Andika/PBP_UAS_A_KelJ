package com.example.tubes;

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
            "Ruang/meja kerja\n" +
            "Brankas dalam kamar\n" +
            "Fasilitas untuk membuat kopi dan teh\n" +
            "hairdryer\n" +
            "Bath\n" +
            "Toilet\n" +
            "Non Smoking Room\n" +
            "Internet Access", 300000,
            "Ekspresif dekorasi tradisional peranakan, Kamar hotel mewah di The Queen " +
                    "adalah lokasi ideal untuk menetap sambil menjelajahi semua yang ditawarkan oleh" +
                    " pulau Bali. Akomodasi hotel modern yang ditawarkan di The Queen memberikan para " +
                    "tamu warna-warna yang menenangkan, tata letak dan fasilitas juga ramah. ",
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
                    "Toilet\n" +
                    "Non Smoking Room\n" +
                    "Internet Access",
            450000,
            "Kami telah merancang dengan desain yang mewah untuk memenuhi berbagai " +
                    "kebutuhan dan anggaran dari tamu kami. Dilengkapi dengan fasilitas" +
                    " demi kenyamanan anda.",
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
                    "Setrika & papan setrika\n" +
                    "Bak mandi\n" +
                    "Shower panas & dingin\n" +
                    "Peralatan Mandi\n" +
                    "Pengering rambut\n" +
                    "Cermin\n" +
                    "Meja makan\n" +
                    "Ruang keluarga\n" +
                    "Ranjang bayi (berdasarkan ketersediaan)\n" +
                    "Bath\n" +
                    "Bathtub",
            999000,
            "Tempat Tidur King dan ruang makan dan ruang tamu terpisah.",
            "https://origin.pegipegi.com/jalan/images/pictM/Y5/Y921895/Y921895065.jpg");
}
