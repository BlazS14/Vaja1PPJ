package meinClasses;

import java.math.BigDecimal;

public class Izdelek {


    private String ime;
    protected BigDecimal cena;
    private int davek;

    public Izdelek(){};
    public Izdelek(String name, BigDecimal price, boolean znizan) {
        this.ime = name;
        this.cena = price;
        if(znizan){
            this.davek = 95;
        }
        else{
            this.davek = 220;
        }
    }

    @Override
    public String toString() {
        return "Izdelek{" +
                "ime='" + ime + '\'' +
                ", cena=" + cena +
                ", davek=" + davek +
                '}';
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public BigDecimal getCena() {
        return cena;
    }

    public void setCena(BigDecimal cena) {
        this.cena = cena;
    }

    public int getDavek() {
        return davek;
    }

    public void setDavek(int davek) {
        this.davek = davek;
    }





}
