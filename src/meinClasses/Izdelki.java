package meinClasses;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import meinClasses.Database.DBHelper;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.*;

public class Izdelki implements JsonSupport {

    public class posamezniIzdelek {
        public Izdelek izdelek;
        public int kolicina;

        public posamezniIzdelek(Izdelek izdelek, int kolicina) {
            this.izdelek = izdelek;
            this.kolicina = kolicina;
        }
    }

    private List<posamezniIzdelek> seznam = new ArrayList<>();

    public Izdelki() {
    }

    public Izdelki(List<posamezniIzdelek> seznam) {
        this.seznam = seznam;
    }

    public void addIzdelek(Izdelek a, int k) { //TODO compare EAN
        for (int i = 0; i < seznam.size(); i++) {
            if (a.getId() == seznam.get(i).izdelek.getId() && !a.isKupon()) {
                seznam.get(i).kolicina += k;
                return;
            }
        }
        this.seznam.add(new posamezniIzdelek(a, k));
    }


    public String toString() {
        int lenDrzava = 0;
        int lenIme = 0;
        int lenId = 0;

        for (posamezniIzdelek i : seznam) {
            if (!i.izdelek.isKupon()) {
                if (i.izdelek.getDrzava().length() >= lenDrzava)
                    lenDrzava = i.izdelek.getDrzava().length();
                if (i.izdelek.getIme().length() > lenIme)
                    lenIme = i.izdelek.getIme().length();
                if (String.valueOf(i.izdelek.getId()).length() > lenId)
                    lenId = String.valueOf(i.izdelek.getId()).length();
            }
        }
        String izpis = "";
        for (posamezniIzdelek i : seznam) {

            izpis += i.izdelek.toString(lenDrzava, lenIme, lenId) + "  ";
            if (i.izdelek.getCenaZDDV() / 10 < 1)
                izpis += " ";
            if (!i.izdelek.isKupon())
                izpis += String.valueOf(i.kolicina) + ' ';
            if (i.izdelek.isFlagWeighable())
                izpis += String.valueOf(i.izdelek.getWeight()) + 'g';
            izpis += '\n';
        }
        return izpis;
    }

    public List<posamezniIzdelek> getSeznam() {
        return seznam;
    }

    public void setSeznam(List<posamezniIzdelek> seznam) {
        this.seznam = seznam;
    }

    public void setKolicina(int index, int k) {
        if (index < seznam.size())
            seznam.get(index).kolicina = k;
    }

    public int getKolicina(int index) {
        if (index < seznam.size())
            return seznam.get(index).kolicina;
        else return -1;
    }

    public void setCenaBrezDDV(int index, float c) {
        if (index < seznam.size())
            seznam.get(index).izdelek.setCenaBrezDDV(c);
    }

    public float getCenaBrezDDV(int index) {
        if (index < seznam.size())
            return seznam.get(index).izdelek.getCenaBrezDDV();
        else
            return -1.0f;
    }

    public void setCenaZDDV(int index, float c) {
        if (index < seznam.size())
            seznam.get(index).izdelek.setCenaZDDV(c);
    }

    public float getCenaZDDV(int index) {
        if (index < seznam.size())
            return seznam.get(index).izdelek.getCenaZDDV();
        else
            return 0.0f;
    }

    public float getDDV(int index) {
        if (index < seznam.size())
            return seznam.get(index).izdelek.getDDV();
        else
            return 0.0f;
    }

    public long getId(int index) {
        if (index < seznam.size())
            return seznam.get(index).izdelek.getId();
        else
            return -1;
    }

    public void fromJson(String s) {
        Gson gson = new Gson();
        Izdelki izd = gson.fromJson(s, Izdelki.class);
        this.seznam = izd.seznam;
    }

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }


}
