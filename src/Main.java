import meinClasses.Racun;
import meinClasses.Izdelek;
import meinClasses.Izdelki;
import meinClasses.Podjetje;
import meinClasses.Helper;
import meinClasses.Database.DBHelper;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) {


        Izdelek A = new Izdelek("1103526350767", "Majeron", 12.0f, 22.0f);
        Izdelek B = new Izdelek("5226950246132", "Metla", 15.0f, 22.0f);
        Izdelek C = new Izdelek("6291041500214", "Nogavice", 2.99f, 22.0f);
        Izdelek W = new Izdelek("2404444490211", 2.99f, 22.0f);
        Izdelek K1 = new Izdelek("9928032019101","Kupon 10%");
        Izdelek K2 = new Izdelek("9928032019201","Kupon 20%");
        K2.setCheckDigit();
        K1.setCheckDigit();
        System.out.println(K2.isKupon());
        System.out.println(K1.getPopust());

        Izdelki izd = new Izdelki();

        izd.addIzdelek(A, 1);
        izd.addIzdelek(B, 2);
        izd.addIzdelek(C, 4);
        izd.addIzdelek(W, 1);
        izd.addIzdelek(K1, 1);
        izd.addIzdelek(K2, 1);


        System.out.println("\n\n");

        Racun R1 = new Racun(izd, "Marko baje");

        //System.out.println(R1.toString());
        System.out.println("\n\n");

        Podjetje P1 = new Podjetje("Pipistrel", "+38630 808 888", "info@ppstrel.si", "45645645","6225480070", true);
        Podjetje P2 = new Podjetje("Perutnina Ptuj d.d.", "+38630 708 888", "info@pp.si", "45655645", "6255480070", false);

        Racun R2 = new Racun(izd, "haha ne", P1);
        Racun R3 = new Racun(izd, "haha ne", P2);


        System.out.println(C.checkDigit());
        C.setCheckDigit();
        System.out.println(C.checkDigit());

        System.out.println(B.search("metl"));
        System.out.println(B.search("Metl"));
        System.out.println(C.search("1234"));

        System.out.println(R2.toString());
        System.out.println("\n\n");
        System.out.println(R3.toString());
        System.out.println("\n\n");


        Helper.writeFile("Izdelki.json", izd.toJson());

        Izdelki izdread = new Izdelki();

        izdread.fromJson(Helper.readFile("Izdelki.json"));

        System.out.println(izdread.toString());
        System.out.println(W.getWeight());
        W.setWeight(999);
        System.out.println(W.toString());



        DBHelper.testConnection();
        DBHelper.fillDb("C:\\Users\\Blaz Satler\\IdeaProjects\\Vaja1PPJ\\resources\\en.openfoodfacts.org.products.csv",20);
        A.insert(A);
    }

}