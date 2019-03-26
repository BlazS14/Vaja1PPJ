package meinClasses;

public class Izdelek implements Searchable {


    private long id;
    private String EAN;
    private String ime;
    private float cenaBrezDDV, cenaZDDV;
    private float DDV;
    private String drzava;
    static private long lastId = 0;

    public Izdelek(){};
    public Izdelek(String EAN, String ime, float cena, float DDV) {
        this.id = ++lastId;
        this.EAN = EAN;
        this.ime = ime;
        this.cenaBrezDDV = cena;
        this.DDV = DDV;
        this.cenaZDDV = cenaBrezDDV + cenaBrezDDV * (DDV / 100);
        this.cenaZDDV = (float)((int)(cenaZDDV *100f ))/100f;
        setDrzavaFromEAN();
    }

    public Izdelek(String EAN, String ime, float cena, float DDV, String drzava) {
        this.id = ++lastId;
        this.EAN = EAN;
        this.ime = ime;
        this.cenaBrezDDV = cena;
        this.DDV = DDV;
        this.cenaZDDV = cenaBrezDDV + cenaBrezDDV * (DDV / 100);
        this.cenaZDDV = (float)((int)(cenaZDDV *100f ))/100f;
        this.drzava = drzava;
    }

    @Override
    public String toString() {
        String izpis = this.id + "\t" + this.EAN + '\t' + this.drzava + '\t' + this.ime + '\t' + String.valueOf(this.cenaBrezDDV) +
                "\t\t\t" + String.valueOf(this.DDV) + "%\t" + String.valueOf(this.cenaZDDV) + "\t\t";
        return izpis;
    }


    public String toString(int lenDrzava,int lenIme, int lenId) {


        String izpis = "";
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
        if(kodaDrzave <= 139)
            drzava = "ZDA";
        else if(kodaDrzave == 275)
            drzava = "Palestine";
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

        else drzava = "Null";
    }

    public boolean search(String s) {
        if(this.toString(0,0,0).contains(s))
            return true;
        return false;
    }
}
