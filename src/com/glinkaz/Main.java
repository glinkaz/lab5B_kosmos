package com.glinkaz;

import com.glinkaz.wyjatki.WyjatekLotniczy;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        PortKosmiczny portKosmiczny = new PortKosmiczny(3);
//        Consumer<List<StatekKosmiczny>> wypisz = statki -> statki.forEach(System.out::println);

        System.out.println("-------------------------");
        System.out.println("Statki kosmiczne w porcie\n");
        portKosmiczny.getStatki().forEach(System.out::println);
//        wypisz.accept(portKosmiczny.getStatki());

        System.out.println("-------------------------\n");
        System.out.println("Proba odlotu\n");
        portKosmiczny.getStatki().forEach(statekKosmiczny -> statekKosmiczny.start(10));

        System.out.println("-------------------------\n");
        System.out.println("Odprawa\n");
        portKosmiczny.getStatki().forEach(statekKosmiczny -> {
            try {
                statekKosmiczny.zaladunek(new Random().nextInt(400)+1);
            } catch (WyjatekLotniczy e) {
                System.out.println("Blad Lotniczy");
            }
        });
        portKosmiczny.getStatki().forEach(System.out::println);

        System.out.println("-------------------------\n");
        System.out.println("Odlot\n");
        portKosmiczny.getStatki().forEach(statekKosmiczny -> statekKosmiczny.start(10));
        portKosmiczny.getStatki().forEach(System.out::println);

        System.out.println("-------------------------\n");
        System.out.println("\nDzilania lotniskowe");
        portKosmiczny.dzialaniaKosmiczne();
        System.out.println("-------------------------\n");

        System.out.println("\nZaladunek");
        portKosmiczny.zaladunekStatkowKosmicznych();
        portKosmiczny.getStatki().forEach(System.out::println);
        System.out.println("-------------------------\n");

        System.out.println("\nSortowanie");
        portKosmiczny.sortowanieStatkowKosmicznych();
        portKosmiczny.getStatki().forEach(System.out::println);
        System.out.println("\nSortowanie losowe");
        portKosmiczny.sortowanieLosowe();
        portKosmiczny.getStatki().forEach(System.out::println);

    }
}
