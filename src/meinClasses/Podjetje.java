package meinClasses;

import meinClasses.Database.DBHelper;

import java.sql.*;
import java.util.List;

public class Podjetje implements Searchable {
    public String uuid;
    private String ime;
    private String telSt;
    private String email;
    private String davcnaSt;
    private String maticnaSt;
    private boolean davcniZavezanec;
    private String addr;

    public Podjetje(String ime, String telSt, String email, String davcnaSt, String maticnaSt, boolean davcniZavezanec) {
        this.ime = ime;
        this.telSt = telSt;
        this.email = email;
        this.davcnaSt = davcnaSt;
        this.maticnaSt = maticnaSt;
        this.davcniZavezanec = davcniZavezanec;
    }

    @Override
    public String toString() {
        return "Podjetje{" +
                "ime='" + ime + '\'' +
                ", telSt='" + telSt + '\'' +
                ", email='" + email + '\'' +
                ", davcnaSt=" + davcnaSt +
                ", maticnaSt=" + maticnaSt +
                ", davcniZavezanec=" + davcniZavezanec +
                '}';
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getTelSt() {
        return telSt;
    }

    public void setTelSt(String telSt) {
        this.telSt = telSt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDavcnaSt() {
        return davcnaSt;
    }

    public void setDavcnaSt(String davcnaSt) {
        this.davcnaSt = davcnaSt;
    }

    public String getMaticnaSt() {
        return maticnaSt;
    }

    public void setMaticnaSt(String maticnaSt) {
        this.maticnaSt = maticnaSt;
    }

    public boolean isDavcniZavezanec() {
        return davcniZavezanec;
    }

    public void setDavcniZavezanec(boolean davcniZavezanec) {
        this.davcniZavezanec = davcniZavezanec;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public boolean search(String s) {
        if(ime.contains(s) || telSt.contains(s) || email.contains(s) || String.valueOf(davcnaSt).contains(s) || String.valueOf(maticnaSt).contains(s) || String.valueOf(davcniZavezanec).contains(s))
            return true;
        return false;
    }

    public Podjetje getById(String uuid){
        try {
            Connection connection = DBHelper.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Podjetje WHERE idIzdelek = ?");
            statement.setString(1,uuid);

            ResultSet rs = statement.executeQuery();
            return (Podjetje)rs;
        } catch (java.sql.SQLException i) {
            System.out.println("SQL napaka Pod.getById()! -- " + i.getMessage() + "\n");
            return null;
        }
    }

    public List<Podjetje> getAll(){
        try {
            List<Podjetje> list = null;
            Connection connection = DBHelper.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM Podjetje");
            for(int i = 0; i!=rs.getFetchSize();i++)
            {
                list.add((Podjetje)rs.getObject(i));
            }
            return list;
        } catch (java.sql.SQLException i) {
            System.out.println("SQL napaka Pod.getAll()! -- " + i.getMessage() + "\n");
            return null;
        }
    }

    public boolean insert(Podjetje m){
        try {
            Connection conn = DBHelper.getConnection();
            PreparedStatement stat = conn.prepareStatement("INSERT INTO Podjetje (idPodjetje,name,tax_num,registration_number,phone_number,taxpayer,adress,created,modified) VALUES (unhex(replace(uuid(),'-','')),?,?,?,?,?,?,current_timestamp(),current_timestamp())");
            stat.setString(1,m.getIme());
            stat.setString(2,m.getDavcnaSt());
            stat.setString(3,m.getMaticnaSt());
            stat.setString(4,m.getTelSt());
            stat.setBoolean(5,m.isDavcniZavezanec());
            stat.setString(6,m.getAddr());

            return stat.execute();

        } catch (java.sql.SQLException i) {
            System.out.println("SQL napaka Pod.insert()! -- " + i.getMessage() + "\n");
            return false;
        }
    }

    public boolean update(Podjetje m){
        try {
            Connection conn = DBHelper.getConnection();
            PreparedStatement stat = conn.prepareStatement("UPDATE Podjetje SET name=?,tax_num=?,registration_number=?,phone_number=?,taxpayer=?,adress=? WHERE idPodjetje = ?");
            stat.setString(1,m.getIme());
            stat.setString(2,m.getDavcnaSt());
            stat.setString(3,m.getMaticnaSt());
            stat.setString(4,m.getTelSt());
            stat.setBoolean(5,m.isDavcniZavezanec());
            stat.setString(6,m.getAddr());
            stat.setString(7,m.uuid);


            return stat.execute();

        } catch (java.sql.SQLException i) {
            System.out.println("SQL napaka Pod.update()! -- " + i.getMessage() + "\n");
            return false;
        }
    }

    public boolean delete(Podjetje m){
        try {
            Connection conn = DBHelper.getConnection();
            PreparedStatement stat = conn.prepareStatement("DELETE FROM Podjetje WHERE idPodjetje = ?");
            stat.setString(1,m.uuid);

            return stat.execute();

        } catch (java.sql.SQLException i) {
            System.out.println("SQL napaka Pod.delete()! -- " + i.getMessage() + "\n");
            return false;
        }
    }

    public Podjetje extractFromResultSet(ResultSet rs) throws SQLException {
        return null;
    }

}
