package com.glinkaz.wyjatki;

public class WyjatekPrzeladowania extends WyjatekLotniczy {
    public WyjatekPrzeladowania(int przeladowanie) {
        System.out.println("Za duzo o " + przeladowanie + " pasazerow.");
    }

    public WyjatekPrzeladowania(int przeladowanie, boolean towarowy) {
        System.out.println("Za duzo o " + przeladowanie + " ton ladunku.");
    }

}