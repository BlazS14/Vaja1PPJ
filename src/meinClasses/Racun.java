package meinClasses;

import meinClasses.Database.DBHelper;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


public class Racun implements Searchable {
    private static int counter = 0;

    public String uuid;
    private Izdelki izdelki;
    private String izdajatelj = "Marcatoor d.d.";
    private double cenaBrezDDV;
    private double cenaZDDV;
    private long id;
    private String prodajalec;
    private Date datum;
    private String davcnaStPodjetja;
    private boolean podjetjeDavcniZavezanec;
    private boolean originalRacun;
    static private long lastId = 0;

    public Racun(Izdelki artikli, String prodajalec, Date datum) {
        this(artikli, prodajalec);
        this.datum = datum;
    }

    public Racun(Izdelki artikli, String prodajalec, Date datum, Podjetje podjetje) {
        this(artikli, prodajalec, datum);

        this.davcnaStPodjetja = podjetje.getDavcnaSt();
        this.podjetjeDavcniZavezanec = podjetje.isDavcniZavezanec();
    }

    public Racun(Izdelki artikli, String prodajalec) {
        this.izdelki = artikli;
        this.id = ++lastId;
        this.prodajalec = prodajalec;
        this.datum = new Date();
        izracunajCeno();
        this.originalRacun = false;
    }

    public Racun(Izdelki artikli, String prodajalec, Podjetje podjetje) {
        this.izdelki = artikli;
        this.id = ++lastId;
        this.prodajalec = prodajalec;
        this.datum = new Date();
        this.davcnaStPodjetja = podjetje.getDavcnaSt();
        this.podjetjeDavcniZavezanec = podjetje.isDavcniZavezanec();
        izracunajCeno();
        this.originalRacun = true;
    }

    public Racun() {
        this.izdelki = new Izdelki();
        this.cenaBrezDDV = 0.0;
        this.cenaZDDV = 0.0;
        this.id = ++lastId;
        this.prodajalec = "";
        this.datum = new Date();
        this.originalRacun = false;
    }

    public Racun(Racun R) {
        this.izdelki = R.izdelki;
        this.cenaBrezDDV = R.cenaBrezDDV;
        this.cenaZDDV = R.cenaZDDV;
        this.id = ++lastId;
        this.prodajalec = R.prodajalec;
        this.datum = R.datum;
        this.davcnaStPodjetja = R.davcnaStPodjetja;
        this.podjetjeDavcniZavezanec = R.podjetjeDavcniZavezanec;
        setLastId(getLastId());
        this.originalRacun = R.isOriginalRacun();
    }

    public Izdelki getArtikli() {
        return izdelki;
    }

    public void setArtikli(Izdelki artikli) {
        this.izdelki = artikli;
    }

    public double getCenaBrezDDV() {
        return cenaBrezDDV;
    }

    public void setCenaBrezDDV(double cenaBrezDDV) {
        this.cenaBrezDDV = cenaBrezDDV;
    }

    public double getCenaZDDV() {
        return cenaZDDV;
    }

    public void setCenaZDDV(double cenaZDDV) {
        this.cenaZDDV = cenaZDDV;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProdajalec() {
        return prodajalec;
    }

    public void setProdajalec(String prodajalec) {
        this.prodajalec = prodajalec;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public static long getLastId() {
        return lastId;
    }

    public static void setLastId(long lastId) {
        Racun.lastId = lastId;
    }

    public boolean isOriginalRacun() {
        return originalRacun;
    }

    public void setOriginalRacun(boolean originalRacun) {
        this.originalRacun = originalRacun;
    }

    public void izracunajCeno(){
        cenaBrezDDV = 0.0;
        cenaZDDV = 0.0;
        int popust = 0;
        for(Izdelki.posamezniIzdelek i : izdelki.getSeznam()){

            if(i.izdelek.isFlagWeighable()){
                cenaBrezDDV += i.izdelek.getCenaBrezDDV() * i.kolicina * (i.izdelek.getWeight()/1000);
                cenaZDDV += i.izdelek.getCenaZDDV() * i.kolicina * (i.izdelek.getWeight()/1000);
            }else if(i.izdelek.isKupon()){
                popust += i.izdelek.getPopust();
            }else{
                cenaBrezDDV += i.izdelek.getCenaBrezDDV() * i.kolicina;
                cenaZDDV += i.izdelek.getCenaZDDV() * i.kolicina;
            }

        }
        this.cenaBrezDDV = (float)((int)(cenaBrezDDV *100f ))/100f/100*(100-popust);
        this.cenaZDDV = (float)((int)(cenaZDDV *100f ))/100f/100*(100-popust);
    }

    @Override
    public String toString() {
        String izpis = "Racun st. " + id + '\n';
        izpis +=izdelki.toString();
        izpis += "\nCena brez DDV:\t" + String.format("%.02f", cenaBrezDDV) + '\n';
        if(originalRacun == false || podjetjeDavcniZavezanec == true)
            izpis += "Cena z DDV:\t\t" + String.format("%.02f", cenaZDDV) + '\n';
        if(this.originalRacun) {
            izpis += "\nOriginal racun:\n";
            izpis += "Davcna stevilka podjetja: " + davcnaStPodjetja + '\n';
            if (this.podjetjeDavcniZavezanec)
                izpis += "Podjetje je zavezanec.\n";
            else
                izpis += "Podjetje ni zavezanec.\n";
        }
        izpis += "\nProdajalec: " + prodajalec;
        izpis += "\nDatum: " + datum.toString();
        izpis += "\nIzdajatelj: " + izdajatelj;
        return izpis;
    }

    public boolean search(String s) {
        if(String.valueOf(cenaBrezDDV).contains(s) || String.valueOf(cenaZDDV).contains(s) || String.valueOf(id).contains(s) || prodajalec.contains(s) || String.valueOf(datum).contains(s) || String.valueOf(davcnaStPodjetja).contains(s))
            return true;
        return false;
    }

    public Racun getById(String uuid){
        try (Connection connection = DBHelper.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM Racun WHERE idRacun = ?")){
            statement.setString(1,uuid);

            ResultSet rs = statement.executeQuery();
            return (Racun)rs;
        } catch (java.sql.SQLException i) {
            System.out.println("SQL napaka Rac.getById()! -- " + i.getMessage() + "\n");
            return null;
        }
    }

    public List<Racun> getAll(){
        try (Connection connection = DBHelper.getConnection();
             Statement statement = connection.createStatement()){
            List<Racun> list = null;
            ResultSet rs = statement.executeQuery("SELECT * FROM Racun");
            for(int i = 0; i!=rs.getFetchSize();i++)
            {
                list.add((Racun)rs.getObject(i));
            }
            return list;
        } catch (java.sql.SQLException i) {
            System.out.println("SQL napaka Rac.getAll()! -- " + i.getMessage() + "\n");
            return null;
        }
    }

    public boolean insert(Racun m){
        try (Connection conn = DBHelper.getConnection();
             PreparedStatement stat = conn.prepareStatement("INSERT INTO Racun (idRacun,total,total_val,created,modified) VALUES (unhex(replace(uuid(),'-','')),?,?,current_timestamp(),current_timestamp())")){
            stat.setDouble(1,m.getCenaBrezDDV());
            stat.setDouble(2,m.getCenaZDDV());

            return stat.execute();

        } catch (java.sql.SQLException i) {
            System.out.println("SQL napaka Rac.insert()! -- " + i.getMessage() + "\n");
            return false;
        }
    }

    public boolean update(Racun m){
        try (Connection conn = DBHelper.getConnection();
             PreparedStatement stat = conn.prepareStatement("UPDATE Podjetje SET total=?,total_val=? WHERE idRacun = ?")){
            stat.setDouble(1,m.getCenaBrezDDV());
            stat.setDouble(2,m.getCenaZDDV());
            stat.setString(3,m.uuid);

            return stat.execute();

        } catch (java.sql.SQLException i) {
            System.out.println("SQL napaka Rac.update()! -- " + i.getMessage() + "\n");
            return false;
        }
    }

    public boolean delete(Racun m){
        try (Connection conn = DBHelper.getConnection();
             PreparedStatement stat = conn.prepareStatement("DELETE FROM Racun WHERE idRacun = ?")){
            stat.setString(1,m.uuid);

            return stat.execute();

        } catch (java.sql.SQLException i) {
            System.out.println("SQL napaka Rac.delete()! -- " + i.getMessage() + "\n");
            return false;
        }
    }

    public Podjetje extractFromResultSet(ResultSet rs) throws SQLException {
        return null;
    }
}
