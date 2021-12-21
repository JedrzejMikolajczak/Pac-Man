package com.company;

public class Duszek {

    enum JakiDuszek{
        NIEBIESKI,ROZOWY,POMARANCZOWY,CZERWONY;
    }
    enum Stan{
        CHASE,FRIGHTENED,EATEN,SCUTTER;
    }

    JakiDuszek jakiDuszek;
    int PozXduszka;
    int PozYduszka;
    int PozXPixelduszka;
    int PozYPixelduszka;
    int celX;
    int celY;
    Kierunek kierunek;
    Stan stan;

    public Duszek(JakiDuszek duszek){
        jakiDuszek = duszek;
    }

    public JakiDuszek getJakiDuszek() {
        return jakiDuszek;
    }

    public int getPozXduszka() {
        return PozXduszka;
    }

    public void setPozXduszka(int pozXduszka) {
        PozXduszka = pozXduszka;
    }

    public int getPozYduszka() {
        return PozYduszka;
    }

    public void setPozYduszka(int pozYduszka) {
        PozYduszka = pozYduszka;
    }

    public int getPozXPixelduszka() {
        return PozXPixelduszka;
    }

    public void setPozXPixelduszka(int pozXPixelduszka) {
        PozXPixelduszka = pozXPixelduszka;
    }

    public int getPozYPixelduszka() {
        return PozYPixelduszka;
    }

    public void setPozYPixelduszka(int pozYPixelduszka) {
        PozYPixelduszka = pozYPixelduszka;
    }

    public int getCelX() {
        return celX;
    }

    public void setCelX(int celX) {
        this.celX = celX;
    }

    public int getCelY() {
        return celY;
    }

    public void setCelY(int celY) {
        this.celY = celY;
    }

    public Kierunek getKierunek() {
        return kierunek;
    }

    public void setKierunek(Kierunek kierunek) {
        this.kierunek = kierunek;
    }

    public Stan getStan() {
        return stan;
    }

    public void setStan(Stan stan) {
        this.stan = stan;
    }
}
