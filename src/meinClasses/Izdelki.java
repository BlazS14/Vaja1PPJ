package meinClasses;

import java.math.BigDecimal;

public class Izdelki extends Izdelek {

    protected Izdelek izdelek;
    private BigDecimal kolicina;

    public Izdelki(Izdelek izdelek, BigDecimal kolicina) {
        this.izdelek = izdelek;
        this.kolicina = kolicina;
    }

    public Izdelki(String name, BigDecimal price, boolean znizan, BigDecimal kolicina) {
        this.izdelek = new Izdelek(name,price,znizan);
        this.kolicina = kolicina;
    }

    public Izdelek getIzdelek() {
        return izdelek;
    }

    public void setIzdelek(Izdelek izdelek) {
        this.izdelek = izdelek;
    }

    public BigDecimal getKolicina() {
        return kolicina;
    }

    public void setKolicina(BigDecimal kolicina) {
        this.kolicina = kolicina;
    }

    @Override
    public String toString() {
        return "Izdelki{" +
                "izdelek=" + izdelek +
                ", kolicina=" + kolicina +
                '}';
    }
}
