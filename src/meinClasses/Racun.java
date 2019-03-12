package meinClasses;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


public class Racun {
    private static int counter = 0;

    private int id;
    private Date datum;
    protected List<Izdelki> izdelki = new ArrayList<Izdelki>();

    public Racun(Date dat) {
        this.id = counter;
        counter++;
        this.datum=dat;
    }

    @Override
    public String toString() {
        String str = "Racun{" +
                "id=" + id +
                ", datum=" + datum;
        for(int i = 0;i!=this.izdelki.size();i++)
            str = str + this.izdelki.get(i).toString();

               str = str + '}';
               return str;
    }

    public int getId() {
        return id;
    }

    public void addIzdelek(Izdelki item){
        this.izdelki.add(item);
    }

    public void removeIzdelek(Izdelki item){
        this.izdelki.remove(item);
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public List<Izdelki> getIzdelki() {
        return izdelki;
    }

    public void setIzdelki(List<Izdelki> izdelki) {
        this.izdelki = izdelki;
    }


}
