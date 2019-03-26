import meinClasses.Racun;
import meinClasses.Izdelek;
import meinClasses.Izdelki;
import meinClasses.Podjetje;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) {




        Izdelek A = new Izdelek("1103526350767", "Zobna scetka", 12.0f, 22.0f);
        Izdelek B = new Izdelek("5226950246132", "Metla", 15.0f, 22.0f);
        Izdelek C = new Izdelek("1234987908454", "Nogavice", 2.99f, 22.0f);

        Izdelki izd = new Izdelki();

        izd.addIzdelek(A,1);
        izd.addIzdelek(B,2);
        izd.addIzdelek(C,4);

        System.out.println(izd.toString());
        System.out.println("\n\n");

        Racun R1 = new Racun(izd,"Marko baje");

        System.out.println(R1.toString());
        System.out.println("\n\n");

        Podjetje P1 = new Podjetje("Talum", "+38630 808 888", "info@talum.si", 45645645, Long.valueOf("6225480070"), true);
        Podjetje P2 = new Podjetje("Perutnina Ptuj d.d.", "+38630 708 888", "info@pp.si", 45655645, Long.valueOf("6255480070"), false);

        Racun R2 = new Racun(izd,"haha ne",P1);
        Racun R3 = new Racun(izd,"haha ne",P2);

        System.out.println(R2.toString());
        System.out.println("\n\n");
        System.out.println(R3.toString());
        System.out.println("\n\n");

        System.out.println("Prava koda: " + A.checkDigit("6291041500213"));
        System.out.println("Napacna koda: " + A.checkDigit("6291041500214"));
        System.out.println("Prava koda: " + A.checkDigit("9789616555104"));

    }

}