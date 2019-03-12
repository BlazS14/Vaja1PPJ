import meinClasses.Racun;
import meinClasses.Izdelek;
import meinClasses.Izdelki;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) {




        Izdelki izdelek1 = new Izdelki("zarnica",BigDecimal.valueOf(12),false,BigDecimal.valueOf(1));
        System.out.println(izdelek1.toString());

        Izdelki izdelek2 = new Izdelki("stol",BigDecimal.valueOf(200.99),false,BigDecimal.valueOf(4));
        System.out.println(izdelek2.toString());


        Date datum = new Date();
        Racun racun = new Racun(datum);
        racun.addIzdelek(izdelek1);
        racun.addIzdelek(izdelek2);
        System.out.println(racun.toString());
        racun.removeIzdelek(izdelek1);
        System.out.println(racun.toString());


    }

}