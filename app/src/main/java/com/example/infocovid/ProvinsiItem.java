package com.example.infocovid;

public class ProvinsiItem {
    private String namaProvinsi, provinsiPositif, provinsiSembuh, provinsiMeninggal;

    public ProvinsiItem(String nama, String positif, String sembuh, String meninggal){
        this.namaProvinsi = nama;
        this.provinsiPositif = positif;
        this.provinsiSembuh = sembuh;
        this.provinsiMeninggal = meninggal;
    }

    public String getNamaProvinsi() {
        return namaProvinsi;
    }

    public String getProvinsiPositif() {
        return provinsiPositif;
    }

    public String getProvinsiSembuh() {
        return provinsiSembuh;
    }

    public String getProvinsiMeninggal() {
        return provinsiMeninggal;
    }
}
