package com.glinkaz.statki;

public class Mysliwiec extends StatekKosmiczny{
    private  int iloscRakiet = 0;

    public Mysliwiec(String nazwa, int maxPredkosc) {
        super(nazwa, maxPredkosc);
    }

    @Override
    public void zaladunek(int iloscZaladunku)  {
        this.iloscRakiet += iloscZaladunku;
        setZaladunekPoprawny(true);
    }

    public void atak() {
        if (iswPowietrzu()) {
            iloscRakiet--;
            System.out.println("Aaaaatak");
            if(iloscRakiet == 0){
                this.laduj();
            }
        }
    }

    @Override
    public String toString() {
        String info = "Mysliwiec o nazwie " + getNazwa() + ". Predkosc maksymalna " + getMaxPredkosc() +
                ", w powietrzu spedzil " + getIloscGodzWPowietrzu() + " godzin.";
        if (!iswPowietrzu()) {
            info += "Aktualnie uziemiony.";
        } else {
            info += "Obecnie leci. Rakiet: " + iloscRakiet;
        }
        return info;
    }
}
