package meinClasses;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import org.omg.CORBA.DATA_CONVERSION;

import java.io.IOException;
import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class Izdelek implements Searchable {


    private long id;
    private String EAN;
    private String ime;
    private boolean flagWeighable;
    private float cenaBrezDDV, cenaZDDV;
    private float DDV;
    private String drzava;
    private String oddelek;
    static private long lastId = 0;

    public Izdelek(){};
    public Izdelek(String EAN, String ime, float cena, float DDV) {
        this.id = ++lastId;
        this.EAN = EAN;
        this.ime = ime;
        this.cenaBrezDDV = cena;
        this.DDV = DDV;
        this.oddelek = "Import";
        this.cenaZDDV = cenaBrezDDV + cenaBrezDDV * (DDV / 100);
        this.cenaZDDV = (float)((int)(cenaZDDV *100f ))/100f;
        this.flagWeighable = false;
        setDrzavaFromEAN();
    }

    public Izdelek(String EAN,String ime) {
        this.EAN = EAN;
        this.ime = ime;
        this.flagWeighable = false;
        setDrzavaFromEAN();
    }

    public Izdelek(String EAN, String ime, float cena, float DDV, String drzava) {
        this.id = ++lastId;
        this.EAN = EAN;
        this.ime = ime;
        this.flagWeighable = false;
        this.cenaBrezDDV = cena;
        this.DDV = DDV;
        this.oddelek = "Import";
        this.cenaZDDV = cenaBrezDDV + cenaBrezDDV * (DDV / 100);
        this.cenaZDDV = (float)((int)(cenaZDDV *100f ))/100f;
        this.drzava = drzava;
    }

    public Izdelek(String EAN, float cena, float DDV) {
        this.id = ++lastId;
        this.EAN = EAN;
        this.ime = ime;
        this.cenaBrezDDV = cena;
        this.DDV = DDV;
        this.cenaZDDV = cenaBrezDDV + cenaBrezDDV * (DDV / 100);
        this.cenaZDDV = (float)((int)(cenaZDDV *100f ))/100f;
        this.flagWeighable = true;
        setDrzavaFromEAN();
    }

    @Override
    public String toString() {
        String izpis = this.id + "\t" + this.EAN + '\t' + this.drzava + '\t' + this.ime + '\t' + String.valueOf(this.cenaBrezDDV) +
                "\t\t\t" + String.valueOf(this.DDV) + "%\t" + String.valueOf(this.cenaZDDV) + "\t\t";
        return izpis;
    }


    public String toString(int lenDrzava,int lenIme, int lenId) {
        String izpis = "";
        if(this.isKupon())
        {
            izpis += "\t \t \t KUPON ZA " + String.valueOf(this.getPopust()) + "% !!!";
        }else{

        izpis += this.id;
        for(int i = String.valueOf(this.id).length()-1; i<lenId; i++)
            izpis += ' ';
        izpis += ' ';
        izpis += this.EAN;
        izpis += "  ";
        izpis += this.drzava;
        for(int i = this.drzava.length()-1; i<lenDrzava; i++)
            izpis += ' ';
        izpis += "  ";
        izpis += this.ime;
        for(int i = this.ime.length()-1; i<lenIme; i++)
            izpis += ' ';
        izpis += ' ' + String.valueOf(this.cenaBrezDDV) + "  " + String.valueOf(this.DDV) + "%  " + String.valueOf(this.cenaZDDV) + "  ";
        }
        return izpis;
    }

    public static boolean checkDigit(String crtnaKoda) {
        if(crtnaKoda.length() != 13)
            return false;
        int sum = 0;
        int last = 0;
        String[] d = crtnaKoda.split("");
        for(short i = 0; i < 13; i++) {
            if(i % 2 == 1 && i < 12)
                sum += 3* Integer.parseInt(d[i]);
            else if(i % 2 != 1 && i < 12)
                sum += Integer.parseInt(d[i]);
            else if(i==12)
                last = (int) ((10 - (sum % 10)) % 10);
        }

        if(Integer.parseInt(d[12]) == last)
            return true;
        return false;
    }

    public boolean checkDigit() {
        return Izdelek.checkDigit(this.EAN);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEAN() {
        return EAN;
    }

    public void setEAN(String EAN) {
        this.EAN = EAN;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public float getCenaBrezDDV() {
        return cenaBrezDDV;
    }

    public void setCenaBrezDDV(float cenaBrezDDV) {
        this.cenaBrezDDV = cenaBrezDDV;
    }

    public float getCenaZDDV() {
        return cenaZDDV;
    }

    public void setCenaZDDV(float cenaZDDV) {
        this.cenaZDDV = cenaZDDV;
    }

    public float getDDV() {
        return DDV;
    }

    public void setDDV(float DDV) {
        this.DDV = DDV;
    }

    public String getDrzava() {
        return drzava;
    }

    public boolean isFlagWeighable() {
        return flagWeighable;
    }

    public void setFlagWeighable(boolean flagWeighable) {
        this.flagWeighable = flagWeighable;
    }

    public void setDrzava(String drzava) {
        this.drzava = drzava;
    }

    public static long getLastId() {
        return lastId;
    }

    public static void setLastId(long lastId) {
        Izdelek.lastId = lastId;
    }

    public void setDrzavaFromEAN() {
        int kodaDrzave = 0;
        String[] e = EAN.split("");
        for(int i = 0; i < 3; i++) {
            kodaDrzave *= 10;
            kodaDrzave += Integer.parseInt(e[i]);
        }
        if(kodaDrzave / 10 != 99){
        if(kodaDrzave <= 139)
            drzava = "ZDA";
        else if(kodaDrzave >= 200 && kodaDrzave <= 299)
            setWeighable();
        else if(kodaDrzave >= 300 && kodaDrzave <= 379)
            drzava = "France and Monaco";
        else if(kodaDrzave == 380)
            drzava = "Bulgaria";
        else if(kodaDrzave == 383)
            drzava = "Slovenia";
        else if(kodaDrzave == 385)
            drzava = "Croatia";
        else if(kodaDrzave == 387)
            drzava = "Bosnia and Herzegovina";
        else if(kodaDrzave == 389)
            drzava = "Montenegro";
        else if(kodaDrzave == 390)
            drzava = "Kosovo";
        else if(kodaDrzave >= 400 && kodaDrzave <= 440)
            drzava = "Germany";
        else if(kodaDrzave >= 450 && kodaDrzave <= 459)
            drzava = "Japan";
        else if(kodaDrzave >= 460 && kodaDrzave <= 469)
            drzava = "Russia";
        else if(kodaDrzave == 470)
            drzava = "Kyrgyzstan";
        else if(kodaDrzave == 471)
            drzava = "Republic of China";
        else if(kodaDrzave == 474)
            drzava = "Estonia";
        else if(kodaDrzave == 475)
            drzava = "Latvia";
        else if(kodaDrzave == 476)
            drzava = "Azerbaijan";
        else if(kodaDrzave == 477)
            drzava = "Lithuania";
        else if(kodaDrzave == 478)
            drzava = "Uzbekistan";
        else if(kodaDrzave == 479)
            drzava = "Sri Lanka";
        else if(kodaDrzave == 480)
            drzava = "Philippines";
        else if(kodaDrzave == 481)
            drzava = "Belarus";
        else if(kodaDrzave == 482)
            drzava = "Ukraine";
        else if(kodaDrzave == 483)
            drzava = "Turkmenistan";
        else if(kodaDrzave == 484)
            drzava = "Moldova";
        else if(kodaDrzave == 485)
            drzava = "Armenia";
        else if(kodaDrzave == 486)
            drzava = "Georgia";
        else if(kodaDrzave == 487)
            drzava = "Kazakhstan";
        else if(kodaDrzave == 488)
            drzava = "Tajikistan";
        else if(kodaDrzave == 489)
            drzava = "Hong Kong";
        else if(kodaDrzave >= 490 && kodaDrzave <= 499)
            drzava = "Japan";
        else if(kodaDrzave >= 500 && kodaDrzave <= 509)
            drzava = "United Kingdom";
        else if(kodaDrzave >= 520 && kodaDrzave <= 521)
            drzava = "Greece";
        else if(kodaDrzave == 528)
            drzava = "Lebanon";
        else if(kodaDrzave == 529)
            drzava = "Cyprus";
        else if(kodaDrzave == 530)
            drzava = "Albania";
        else if(kodaDrzave == 531)
            drzava = "Macedonia";
        else if(kodaDrzave == 535)
            drzava = "Malta";
        else if(kodaDrzave == 539)
            drzava = "Ireland";
        else if(kodaDrzave >= 540 && kodaDrzave <= 549)
            drzava = "Belgium and Luxembourg";
        else if(kodaDrzave == 560)
            drzava = "Portugal";
        else if(kodaDrzave == 569)
            drzava = "Iceland";
        else if(kodaDrzave >= 570 && kodaDrzave <= 579)
            drzava = "Denmark and Faroe Islands and Greenland";
        else if(kodaDrzave == 590)
            drzava = "Poland";
        else if(kodaDrzave == 594)
            drzava = "Romania";
        else if(kodaDrzave == 599)
            drzava = "Hungary";
        else if(kodaDrzave >= 600 && kodaDrzave <= 601)
            drzava = "South Africa";
        else drzava = "Null";}
    }

    private void setWeighable() {
        this.drzava = "Slovenia";
        int kodaOddelka = 0;
        String[] e = EAN.split("");
        for(int i = 0; i < 3; i++) {
            kodaOddelka *= 10;
            kodaOddelka += Integer.parseInt(e[i]);
        }

        int kodaIzdelka = 0;
        for(int i = 3; i < 7; i++) {
            kodaIzdelka *= 10;
            kodaIzdelka += Integer.parseInt(e[i]);
        }

        int checkDigit = 0;
        checkDigit += Integer.parseInt(e[12]);

        if(!this.checkDigit()){
            setCheckDigit();
        }

        if(kodaOddelka == 240) {
            this.oddelek="Sadje";
            if(kodaIzdelka == 4444)
               this.ime = "Jabolka";
            else if(kodaIzdelka == 6666)
                this.ime="Banane";
            else if(kodaIzdelka == 1212)
                this.ime="Jagode";
            else if(kodaIzdelka == 1024)
                this.ime="Hruske";

        }else if(kodaOddelka== 241){
            this.oddelek = "Zelenjava";
            if(kodaIzdelka == 4444)
                this.ime = "Paprika";
            else if(kodaIzdelka == 6666)
                this.ime="Cebula";
            else if(kodaIzdelka == 1212)
                this.ime="Cesen";
            else if(kodaIzdelka == 1024)
                this.ime="Paradiznik";

        }else if(kodaOddelka== 241){
            this.oddelek = "Meso";
            if(kodaIzdelka == 4444)
                this.ime = "Svinjsko plece";
            else if(kodaIzdelka == 6666)
                this.ime="Piscancja prsa";
            else if(kodaIzdelka == 1212)
                this.ime="Kranjske Kosak";
            else if(kodaIzdelka == 1024)
                this.ime="Goveji hrbet";

        }else if(kodaOddelka== 241){
            this.oddelek = "Kruh";
            if(kodaIzdelka == 4444)
                this.ime = "Beli kolac";
            else if(kodaIzdelka == 6666)
                this.ime="Bageta";
            else if(kodaIzdelka == 1212)
                this.ime="Crni kolac";
            else if(kodaIzdelka == 1024)
                this.ime="Polcrni kolac";

        }else if(kodaOddelka== 241){
            this.oddelek = "Delikatesa";
            if(kodaIzdelka == 4444)
                this.ime = "Posebna Poli";
            else if(kodaIzdelka == 6666)
                this.ime="Sunka Ave";
            else if(kodaIzdelka == 1212)
                this.ime="Cajna Ave";
            else if(kodaIzdelka == 1024)
                this.ime="Tlacenka Kras";
        }
    }

    public void setCheckDigit() {
        int sum = 0;
        int last = 0;
        String[] d = this.EAN.split("");
        for(short i = 0; i < 13; i++) {
            if(i % 2 == 1 && i < 12)
                sum += 3* Integer.parseInt(d[i]);
            else if(i % 2 != 1 && i < 12)
                sum += Integer.parseInt(d[i]);
            else if(i==12)
                last = (int) ((10 - (sum % 10)) % 10);
        }
        d[12] = String.valueOf(last);
        System.out.println(this.EAN + '\n');
        this.EAN = String.join("",d);
        System.out.println(this.EAN + '\n');
    }

    public int getWeight(){

        String[] e = EAN.split("");
        int kodaTeze = 0;
        for(int i = 7; i < 12; i++) {
            kodaTeze *= 10;
            kodaTeze += Integer.parseInt(e[i]);
        }
        return kodaTeze;
    }

    public void setWeight(int teza){

        String[] e = EAN.split("");

        for(int i = 11; i > 6; i--) {
            e[i] = String.valueOf(teza%10);
            teza = teza / 10;
        }
        this.EAN = String.join("",e);
        this.setCheckDigit();
    }

    public boolean search(String s) {
        if(this.toString(0,0,0).contains(s))
            return true;
        return false;
    }

    public boolean isKupon(){
        String[] e = this.EAN.split("");
        int mark = 0;
        for(int i = 0; i != 2; i++) {
            mark *= 10;
            mark += Integer.parseInt(e[i]);
        }

        if(mark != 99 || !this.checkDigit())
            return false;

        int date = 0;
        for(int i = 2; i != 10; i++) {
            date *= 10;
            date += Integer.parseInt(e[i]);
        }
        SimpleDateFormat originalFormat = new SimpleDateFormat("ddMMyyyy");
        String datum = String.valueOf(date);
        Date d = new Date();
        try {
           d = originalFormat.parse(datum);
        }catch (ParseException i){};

        Date currentDate = new Date();
        if(d.compareTo(currentDate) <= 0)
            return false;
        return true;
    }

    public int getPopust()
    {
        String[] e = this.EAN.split("");
        int mark = 0;
        for(int i = 10; i != 12; i++) {
            mark *= 10;
            mark += Integer.parseInt(e[i]);
        }
        return mark;
    }
}
