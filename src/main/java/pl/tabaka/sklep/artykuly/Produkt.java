package pl.tabaka.sklep.artykuly;

import pl.tabaka.sklep.core.Writable;

public class Produkt implements Writable{
    private final String nazwa;
    private float cena;
    private int iloscNaStanie;

    public Produkt(String nazwa, float cena, int iloscNaStanie) {
        this.nazwa = nazwa;
        this.cena = cena;
        this.iloscNaStanie = iloscNaStanie;
    }

    public void restock(int dodaj) {
        this.iloscNaStanie+=dodaj;
    }

    public String getNazwa() {
        return nazwa;
    }

    public float getCena() {
        return this.cena;
    }

    public int getIloscNaStanie() {
        return iloscNaStanie;
    }

    public void removeAmount(int ilosc){
        if(ilosc<=this.iloscNaStanie){
            iloscNaStanie-=ilosc;
        } else {
            System.out.println("Blad nie oblugiwany!");
        }
    }

    @Override
    public String toCSV(){
        return new StringBuilder().append(getClass().getSimpleName())
                .append(";")
                .append(this.nazwa)
                .append(";")
                .append(this.cena)
                .append(";")
                .append(this.iloscNaStanie)
                .toString();
    }
}
