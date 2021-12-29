package com.company;
import static com.company.Main.*;
import static com.company.Duszek.*;

public class PacMan {
    static int PozXPacMana;
    static int PozYPacMana;
    static int Punkty;

    static float PredkoscRuchuPacMana; //specjalnie przygotowane dla grupy 2:D

    int PozXPixelPacMana;
    int PozYPixelPacMana;




    Kierunek kierunek;


    public int getPozXPacMana() { return PozXPacMana; }

    public void setPozXPacMana(int pozXPacMana) { PozXPacMana = pozXPacMana; }

    public int getPozYPacMana() { return PozYPacMana; }

    public void setPozYPacMana(int pozYPacMana) { PozYPacMana = pozYPacMana; }

    public int getPozXPixelPacMana() { return PozXPixelPacMana; }

    public void setPozXPixelPacMana(int pozXPixelPacMana) { PozXPixelPacMana = pozXPixelPacMana; }

    public int getPozYPixelPacMana() { return PozYPixelPacMana; }

    public void setPozYPixelPacMana(int pozYPixelPacMana) { PozYPixelPacMana = pozYPixelPacMana; }

    public Kierunek getKierunek() {
        return kierunek;
    }

    public void setKierunek(Kierunek kierunek) {
        this.kierunek = kierunek;
    }

    public static void PunktyMetod()
    {
        if(plansza[PozXPacMana][PozYPacMana].czyKropka) {Punkty++; plansza[PozXPacMana][PozYPacMana].czyKropka = false;}
    }

    public static void StartBoost()
    {
        if(plansza[PozXPacMana][PozYPacMana].czyBoost) {PredkoscRuchuPacMana = 2f;}
    }


}
