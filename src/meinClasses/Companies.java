package meinClasses;

import java.util.ArrayList;
import java.util.List;

public class Companies implements JsonSupport{

    private List<Podjetje> podjetja = new ArrayList<>();

    public List<Podjetje> getPodjetja() {
        return podjetja;
    }

    public void setPodjetja(List<Podjetje> podjetja) {
        this.podjetja = podjetja;
    }

    public void addPodjetje(Podjetje p)
    {
        this.podjetja.add(p);
    }

    @Override
    public void fromJson(String s) {

    }

    @Override
    public String toJson() {
        return null;
    }
}
