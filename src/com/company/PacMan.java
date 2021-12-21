package com.company;

public class PacMan {
    int PozXPacMana;
    int PozYPacMana;
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


}
