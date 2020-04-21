package com.example.infocovid;

public class NegaraItem {
    private String namaNegara, negaraPositif, negaraSembuh, negaraMeninggal;

    public NegaraItem(String nama, String positif, String sembuh, String meninggal){
        this.namaNegara = nama;
        this.negaraPositif = positif;
        this.negaraSembuh = sembuh;
        this.negaraMeninggal = meninggal;
    }
    public String getNamaNegara(){
        return namaNegara;
    }

    public String getNegaraPositif() {
        return negaraPositif;
    }

    public String getNegaraSembuh() {
        return negaraSembuh;
    }

    public String getNegaraMeninggal() {
        return negaraMeninggal;
    }
}
