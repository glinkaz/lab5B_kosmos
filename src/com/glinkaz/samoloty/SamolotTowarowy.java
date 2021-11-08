package com.glinkaz.samoloty;

import com.glinkaz.wyjatki.WyjatekEkonomiczny;
import com.glinkaz.wyjatki.WyjatekLotniczy;
import com.glinkaz.wyjatki.WyjatekPrzeladowania;

public class SamolotTowarowy extends Samolot{
    private int iloscLadunku = 0;
    private final int maxIloscLadunku;

    public SamolotTowarowy(String nazwa, int maxPredkosc, int maxIloscLadunku) {
        super(nazwa, maxPredkosc);
        this.maxIloscLadunku = maxIloscLadunku;
    }

    @Override
    public void zaladunek(int iloscZaladunku) throws WyjatekLotniczy {
        this.iloscLadunku += iloscZaladunku;
        System.out.println("Obecna ilosc ladunku " + this.iloscLadunku);
        if(iloscLadunku*2<maxIloscLadunku){
            setZaladunekPoprawny(false);
            throw new WyjatekEkonomiczny(true);
        } else if(iloscLadunku>maxIloscLadunku){
            setZaladunekPoprawny(true);
            throw new WyjatekPrzeladowania(iloscLadunku - maxIloscLadunku, true);
        } else {
            setZaladunekPoprawny(true);
        }
    }

    @Override
    public void laduj() {
        if(iswPowietrzu())
            iloscLadunku = 0;
        super.laduj();
    }

    @Override
    public String toString() {
        String info = "Samolot towarowy o nazwie " + getNazwa() + ". Predkosc maksymalna " + getMaxPredkosc() +
                ", w powietrzu spedzil " + getIloscGodzWPowietrzu() + " godzin, moze zabrac na poklad " + maxIloscLadunku +
                " ton ladunku.  ";
        if (!iswPowietrzu()) {
            info += "Aktualnie uziemiony.";
        } else {
            if(iloscLadunku < maxIloscLadunku)
                info += "Obecine leci z " + iloscLadunku + " pasazerami na pokladzie.";
            else
                info += "Obecine leci z " + maxIloscLadunku + " pasazerami na pokladzie.";
        }
        return info;
    }
}

