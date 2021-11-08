package com.glinkaz.wyjatki;

public class WyjatekEkonomiczny extends WyjatekLotniczy {

    public WyjatekEkonomiczny() {
        System.out.println("Za malo pasazerow nie oplaca sie leciec");
    }
    public WyjatekEkonomiczny(boolean towarowy) {
        if(towarowy) {
            System.out.println("Zbyt maly ladunkek, nie oplaca sie leciec");
        }
    }
}