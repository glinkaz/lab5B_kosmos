package com.glinkaz.samoloty;

import com.glinkaz.wyjatki.WyjatekLotniczy;

public abstract class Samolot {
    private final String nazwa;
    private final int maxPredkosc;
    private int iloscGodzWPowietrzu;
    private boolean zaladunekPoprawny;
    private boolean wPowietrzu;

    public Samolot(String nazwa, int maxPredkosc) {
        this.nazwa = nazwa;
        this.maxPredkosc = maxPredkosc;
        iloscGodzWPowietrzu = 0;
        zaladunekPoprawny = false;
        wPowietrzu = false;
    }

    public void start(int iloscGodzWPowietrzu){
        if(zaladunekPoprawny) {
            if(!wPowietrzu) {
                System.out.println("Startujemy!");
                this.iloscGodzWPowietrzu += iloscGodzWPowietrzu;
                wPowietrzu = true;
            }else{
                System.out.println("Lecimy");
            }
        }else{
            System.out.println("Nie możemy wystartować");
        }
    }

    public void laduj(){
        if(wPowietrzu){
            System.out.println("Ladujemy");
            wPowietrzu = false;
        }else{
            System.out.println("I tak jestesmy w na ziemi.");
        }
    }

    public abstract void zaladunek(int iloscZaladunku) throws  WyjatekLotniczy;



    public void setZaladunekPoprawny(boolean zaladunekPoprawny) {
        this.zaladunekPoprawny = zaladunekPoprawny;
    }

    public boolean iswPowietrzu() {
        return wPowietrzu;
    }

    public int getMaxPredkosc() {
        return maxPredkosc;
    }

    public String getNazwa() {
        return nazwa;
    }

    public int getIloscGodzWPowietrzu() {
        return iloscGodzWPowietrzu;
    }
}
