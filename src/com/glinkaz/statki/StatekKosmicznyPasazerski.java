package com.glinkaz.statki;

import com.glinkaz.wyjatki.WyjatekEkonomiczny;
import com.glinkaz.wyjatki.WyjatekLotniczy;
import com.glinkaz.wyjatki.WyjatekPrzeladowania;

public class StatekKosmicznyPasazerski extends StatekKosmiczny {
    private int liczbaPasazerow;
    private final int maxLiczbaPasazerow;

    public StatekKosmicznyPasazerski(String nazwa, int maxPredkosc, int maxLiczbaPasazerow) {
        super(nazwa, maxPredkosc);
        this.maxLiczbaPasazerow = maxLiczbaPasazerow;

    }

    @Override
    public void zaladunek(int iloscZaladunku) throws WyjatekLotniczy {
        this.liczbaPasazerow = iloscZaladunku;
        System.out.println("Obecna liczba pasazerow " + this.liczbaPasazerow);
        if(liczbaPasazerow*2<maxLiczbaPasazerow){
            setZaladunekPoprawny(false);
            throw new WyjatekEkonomiczny();
        } else if(liczbaPasazerow>maxLiczbaPasazerow){
            setZaladunekPoprawny(true);
            throw new WyjatekPrzeladowania(liczbaPasazerow - maxLiczbaPasazerow);
        }else{
            setZaladunekPoprawny(true);
        }
    }

    @Override
    public void laduj() {
        if(iswPowietrzu())
            liczbaPasazerow = 0;
        super.laduj();
    }

    @Override
    public String toString() {
        String info = "Samolot pasazerski o nazwie " + getNazwa() + ". Predkosc maksymalna " + getMaxPredkosc() +
                ", w powietrzu spedzil " + getIloscGodzWPowietrzu() + " godzin, moze zabrac na poklad " + maxLiczbaPasazerow +
                " pasazerow.  ";
        if (!iswPowietrzu()) {
            info += "Aktualnie uziemiony.";
        } else {
            if(liczbaPasazerow < maxLiczbaPasazerow)
                info += "Obecine leci z " + liczbaPasazerow + " pasazerami na pokladzie.";
            else
                info += "Obecine leci z " + maxLiczbaPasazerow + " pasazerami na pokladzie.";
        }
        return info;
    }
}


