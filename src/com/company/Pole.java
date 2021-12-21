package com.company;

public class Pole {

    enum RodzajPola{
        POZIOMA, PIONOWA, GORALEWY, GORAPRAWY, DOLLEWY, DOLPRAWY, PUSTE
    }

    RodzajPola rodzajPola;
    Boolean czyDaSieWejsc;
    Boolean czyKropka;
    Boolean czyBoost;

    public Pole(RodzajPola pole, Boolean kropka, Boolean boost){
        rodzajPola = pole;
        czyKropka = kropka;
        czyBoost = boost;
        if (rodzajPola == RodzajPola.PUSTE)
            czyDaSieWejsc = true;
        else
            czyDaSieWejsc = false;
    }

    // 0 - nic nie zjadl
    // 1 - zjadl kropke
    // 2 - zjadl boosta
    public int zjedzZawartosc(){
        if (rodzajPola != RodzajPola.PUSTE)
            return 0;
        if (czyKropka) {
            czyKropka = false;
            return 1;
        }
        if (czyBoost) {
            czyBoost = false;
            return 2;
        }
        return 0;
    }

    public RodzajPola getRodzajPola() {
        return rodzajPola;
    }

    public Boolean getCzyDaSieWejsc() {
        return czyDaSieWejsc;
    }

    public Boolean getCzyKropka() {
        return czyKropka;
    }

    public Boolean getCzyBoost() {
        return czyBoost;
    }
}
