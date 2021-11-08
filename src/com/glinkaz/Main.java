package com.glinkaz;

import com.glinkaz.wyjatki.WyjatekLotniczy;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        Lotnisko lotnisko = new Lotnisko(3);
//        Consumer<List<StatekKosmiczny>> wypisz = statki -> statki.forEach(System.out::println);

        System.out.println("-------------------------");
        System.out.println("Samoloty w lotnisku\n");
        lotnisko.getSamoloty().forEach(System.out::println);
//        wypisz.accept(portKosmiczny.getStatki());

        System.out.println("-------------------------\n");
        System.out.println("Proba odlotu\n");
        lotnisko.getSamoloty().forEach(samolot -> samolot.start(10));

        System.out.println("-------------------------\n");
        System.out.println("Odprawa\n");
        lotnisko.getSamoloty().forEach(samolot -> {
            try {
                samolot.zaladunek(new Random().nextInt(400)+1);
            } catch (WyjatekLotniczy e) {
                System.out.println("Blad Lotniczy");
            }
        });
        lotnisko.getSamoloty().forEach(System.out::println);

        System.out.println("-------------------------\n");
        System.out.println("Odlot\n");
        lotnisko.getSamoloty().forEach(samolot -> samolot.start(10));
        lotnisko.getSamoloty().forEach(System.out::println);

        System.out.println("-------------------------\n");
        System.out.println("\nDzilania kosmiczne");
        lotnisko.dzialaniaLotniskowe();
        System.out.println("-------------------------\n");

        System.out.println("\nZaladunek");
        lotnisko.zaladunekSamolotow();
        lotnisko.getSamoloty().forEach(System.out::println);
        System.out.println("-------------------------\n");

        System.out.println("\nSortowanie");
        lotnisko.sortowanieSamolotow();
        lotnisko.getSamoloty().forEach(System.out::println);
        System.out.println("\nSortowanie losowe");
        lotnisko.sortowanieLosowe();
        lotnisko.getSamoloty().forEach(System.out::println);

    }
}
