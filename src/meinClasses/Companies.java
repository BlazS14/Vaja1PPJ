package meinClasses;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class Companies implements JsonSupport{

    private List<Podjetje> seznam = new ArrayList<>();

    public List<Podjetje> getPodjetja() {
        return seznam;
    }

    public void setPodjetja(List<Podjetje> podjetja) {
        this.seznam = podjetja;
    }

    public void addPodjetje(Podjetje p)
    {
        this.seznam.add(p);
    }

    public void fromJson(String s) {
        Gson gson = new Gson();
        Companies i = gson.fromJson(s,Companies.class);
        this.seznam = i.seznam;
    }

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
    }
}
