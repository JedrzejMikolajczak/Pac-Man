package com.company;
import static com.company.Main.*;
import static com.company.Duszek.*;

public class PacMan {
    private static int PozXPacMana;
    private static int PozYPacMana;
    private static int Punkty;

    static int PredkoscRuchuPacMana = 3;

    private int PozXPixelPacMana;
    private int PozYPixelPacMana;


    Kierunek kierunek;


    public PacMan(int pozXPixelPacMana, int pozYPixelPacMana, Kierunek kierunek) {
        PozXPixelPacMana = pozXPixelPacMana;
        PozYPixelPacMana = pozYPixelPacMana;
        PozXPacMana = pozXPixelPacMana/ rozmiarPola;
        PozYPacMana = pozYPixelPacMana/ rozmiarPola;
        this.kierunek = kierunek;
    }

    public int getPozXPacMana() { return PozXPacMana; }

    //public void setPozXPacMana(int pozXPacMana) { PozXPacMana = pozXPacMana; }

    public int getPozYPacMana() { return PozYPacMana; }

    //public void setPozYPacMana(int pozYPacMana) { PozYPacMana = pozYPacMana; }

    public int getPozXPixelPacMana() { return PozXPixelPacMana; }

    public void setPozXPixelPacMana(int pozXPixelPacMana) {
        PozXPixelPacMana = pozXPixelPacMana;
        PozXPacMana = PozXPixelPacMana / rozmiarPola;
    }

    public int getPozYPixelPacMana() { return PozYPixelPacMana; }

    public void setPozYPixelPacMana(int pozYPixelPacMana) {
        PozYPixelPacMana = pozYPixelPacMana;
        PozYPacMana = PozYPixelPacMana / rozmiarPola;
    }

    public Kierunek getKierunek() {
        return kierunek;
    }

    public void setKierunek(Kierunek kierunek) {
        this.kierunek = kierunek;
    }

    public static void PunktyMetod()
    {
        if(plansza[PozXPacMana][PozYPacMana].getCzyKropka()) {Punkty++;}
    }




}