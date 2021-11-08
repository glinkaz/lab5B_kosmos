package com.glinkaz;

import com.glinkaz.statki.Mysliwiec;
import com.glinkaz.statki.StatekKosmiczny;
import com.glinkaz.statki.StatekKosmicznyPasazerski;
import com.glinkaz.statki.StatekKosmicznyTowarowy;
import com.glinkaz.wyjatki.WyjatekLotniczy;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Random;
import java.util.function.Consumer;

public class PortKosmiczny {
    private final LinkedList<StatekKosmiczny> statki;

    public PortKosmiczny(int ileStatkow) {
        statki = new LinkedList<>();
        for (int i = 0; i < ileStatkow; i++) {
            int zmiennaLosowa = new Random().nextInt(3);
            if(zmiennaLosowa == 0){
                statki.add(new StatekKosmicznyPasazerski(generujNazwe(),new Random().nextInt(4001)+1000, new Random().nextInt(101)+100));
            } else if( zmiennaLosowa == 1 ){
                statki.add(new StatekKosmicznyTowarowy(generujNazwe(), new Random().nextInt(901)+100, new Random().nextInt(901)+100) );
            } else {
                statki.add(new Mysliwiec(generujNazwe(), new Random().nextInt(5001)+5000));
            }
        }
    }

    public void zaladunekStatkowKosmicznych(){
        statki.forEach(samolot -> {
            if(samolot instanceof StatekKosmicznyPasazerski) {
                try {
                    samolot.zaladunek(new Random().nextInt(400)+1);
                } catch (WyjatekLotniczy e) {
                    System.out.println("Blad lotniczy");
                }
            }else if(samolot instanceof StatekKosmicznyTowarowy){
                try {
                    samolot.zaladunek(new Random().nextInt(200)+1);
                } catch (WyjatekLotniczy e) {
                    System.out.println("Blad Lotniczy");
                }
            }else if(samolot instanceof Mysliwiec){
                ((Mysliwiec)samolot).zaladunek(new Random().nextInt(10)+1);
            }
        });
    }

    public void dzialaniaKosmiczne(){
        Consumer<LinkedList<StatekKosmiczny>> wykonajDzialaniaKosmiczne = statki -> {
            Consumer<StatekKosmiczny> wypisz = statek -> System.out.println(statek.toString());
            statki.forEach(wypisz);
            System.out.println();
            statki.forEach(StatekKosmiczny::laduj);
            statki.forEach(wypisz);
            System.out.println();
            statki.forEach(samolot -> {
                try {
                    samolot.zaladunek(new Random().nextInt(400)+1);
                } catch (WyjatekLotniczy e) {
                    System.out.println("Blad Lotniczy");
                }
            });
            statki.forEach(wypisz);
            System.out.println();
            statki.forEach(samolot -> samolot.start(10));
            statki.forEach(wypisz);
            System.out.println();
            statki.forEach(samolot -> {
                if(samolot instanceof Mysliwiec){
                    ((Mysliwiec) samolot).atak();
                }
            });
            statki.forEach(wypisz);
            System.out.println();
        };
        wykonajDzialaniaKosmiczne.accept(statki);
    }

    public void sortowanieStatkowKosmicznych(){
        statki.sort(Comparator.comparingInt(StatekKosmiczny::getMaxPredkosc));
        statki.sort((o1, o2) -> {
            if(o1.getNazwa().length() < 5 || o2.getNazwa().length() < 5){
                return 0;
            }else{
                return o1.getNazwa().compareTo(o2.getNazwa());
            }
        });
    }

    @FunctionalInterface
    public interface LosowyKomparator{
         Comparator<StatekKosmiczny> losujKomparator(Comparator<StatekKosmiczny> c1, Comparator<StatekKosmiczny> c2);
    }

    public void sortowanieLosowe() {
        LosowyKomparator comparator = (c1, c2) -> {
            if (new Random().nextInt(2) == 1){
                return c1;
            }else {
                return c2;
            }
        };
        Comparator<StatekKosmiczny> c1 = Comparator.comparingInt(StatekKosmiczny::getMaxPredkosc);
        Comparator<StatekKosmiczny> c2 = (o1, o2) -> {
            if(o1.getNazwa().length() < 5 || o2.getNazwa().length() < 5){
                return 0;
            }else{
                return o1.getNazwa().compareTo(o2.getNazwa());
            }
        };
        statki.sort(comparator.losujKomparator(c1, c2));
    }


    private String generujNazwe(){
        Supplier<String> generator = () -> {
        String litery = "qwertyuiopasdfghjklzxcvbnm";
        StringBuilder nazwa = new StringBuilder();
        for (int i = 0; i < 20; i++) {
            nazwa.append(litery.charAt(new Random().nextInt(litery.length())));
            }
        return nazwa.toString();
        };
        return generator.generuj();
    }

    @FunctionalInterface
    public interface Supplier<String>{
        String generuj();
    }

    public LinkedList<StatekKosmiczny> getStatki() {
        return statki;
    }
}
