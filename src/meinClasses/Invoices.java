package meinClasses;

import java.util.ArrayList;
import java.util.List;

public class Invoices implements JsonSupport{

    private List<Racun> racuni = new ArrayList<>();

    public List<Racun> getRacuni() {
        return racuni;
    }

    public void setRacuni(List<Racun> racuni) {
        this.racuni = racuni;
    }

    public void addRacun(Racun r)
    {
        this.racuni.add(r);
    }

    @Override
    public void fromJson(String s) {

    }

    @Override
    public String toJson() {
        return null;
    }
}
