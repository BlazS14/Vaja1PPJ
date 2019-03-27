package meinClasses;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class Invoices implements JsonSupport{

    private List<Racun> seznam = new ArrayList<>();

    public List<Racun> getRacuni() {
        return seznam;
    }

    public void setRacuni(List<Racun> racuni) {
        this.seznam = racuni;
    }

    public void addRacun(Racun r)
    {
        this.seznam.add(r);
    }

    public void fromJson(String s) {
        Gson gson = new Gson();
        Invoices i = gson.fromJson(s,Invoices.class);
        this.seznam = i.seznam;
    }

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
