package com.glinkaz;

import com.glinkaz.samoloty.Mysliwiec;
import com.glinkaz.samoloty.Samolot;
import com.glinkaz.samoloty.SamolotPasazerski;
import com.glinkaz.samoloty.SamolotTowarowy;
import com.glinkaz.wyjatki.WyjatekLotniczy;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Random;
import java.util.function.Consumer;

public class Lotnisko {
    private final LinkedList<Samolot> samoloty;

    public Lotnisko(int ileStatkow) {
        samoloty = new LinkedList<>();
        for (int i = 0; i < ileStatkow; i++) {
            int zmiennaLosowa = new Random().nextInt(3);
            if(zmiennaLosowa == 0){
                samoloty.add(new SamolotPasazerski(generujNazwe(),new Random().nextInt(4001)+1000, new Random().nextInt(101)+100));
            } else if( zmiennaLosowa == 1 ){
                samoloty.add(new SamolotTowarowy(generujNazwe(), new Random().nextInt(901)+100, new Random().nextInt(901)+100) );
            } else {
                samoloty.add(new Mysliwiec(generujNazwe(), new Random().nextInt(5001)+5000));
            }
        }
    }

    public void zaladunekSamolotow(){
        samoloty.forEach(samolot -> {
            if(samolot instanceof SamolotPasazerski) {
                try {
                    samolot.zaladunek(new Random().nextInt(400)+1);
                } catch (WyjatekLotniczy e) {
                    System.out.println("Blad lotniczy");
                }
            }else if(samolot instanceof SamolotTowarowy){
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

    public void dzialaniaLotniskowe(){
        Consumer<LinkedList<Samolot>> wykonajDzialaniaLotniskowe = samoloty -> {
            Consumer<Samolot> wypisz = samolot -> System.out.println(samolot.toString());
            samoloty.forEach(wypisz);
            System.out.println();
            samoloty.forEach(Samolot::laduj);
            samoloty.forEach(wypisz);
            System.out.println();
            samoloty.forEach(samolot -> {
                try {
                    samolot.zaladunek(new Random().nextInt(400)+1);
                } catch (WyjatekLotniczy e) {
                    System.out.println("Blad Lotniczy");
                }
            });
            samoloty.forEach(wypisz);
            System.out.println();
            samoloty.forEach(samolot -> samolot.start(10));
            samoloty.forEach(wypisz);
            System.out.println();
            samoloty.forEach(samolot -> {
                if(samolot instanceof Mysliwiec){
                    ((Mysliwiec) samolot).atak();
                }
            });
            samoloty.forEach(wypisz);
            System.out.println();
        };
        wykonajDzialaniaLotniskowe.accept(samoloty);
    }

    public void sortowanieSamolotow(){
        samoloty.sort(Comparator.comparingInt(Samolot::getMaxPredkosc));
        samoloty.sort((o1, o2) -> {
            if(o1.getNazwa().length() < 5 || o2.getNazwa().length() < 5){
                return 0;
            }else{
                return o1.getNazwa().compareTo(o2.getNazwa());
            }
        });
    }

    @FunctionalInterface
    public interface LosowyKomparator{
         Comparator<Samolot> losujKomparator(Comparator<Samolot> c1, Comparator<Samolot> c2);
    }

    public void sortowanieLosowe() {
        LosowyKomparator comparator = (c1, c2) -> {
            if (new Random().nextInt(2) == 1){
                return c1;
            }else {
                return c2;
            }
        };
        Comparator<Samolot> c1 = Comparator.comparingInt(Samolot::getMaxPredkosc);
        Comparator<Samolot> c2 = (o1, o2) -> {
            if(o1.getNazwa().length() < 5 || o2.getNazwa().length() < 5){
                return 0;
            }else{
                return o1.getNazwa().compareTo(o2.getNazwa());
            }
        };
        samoloty.sort(comparator.losujKomparator(c1, c2));
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

    public LinkedList<Samolot> getSamoloty() {
        return samoloty;
    }
}
