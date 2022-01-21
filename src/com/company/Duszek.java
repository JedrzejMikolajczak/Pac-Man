package com.company;

import java.util.ArrayList;

import static com.company.Main.*;

public class Duszek {

    enum JakiDuszek{
        NIEBIESKI,ROZOWY,POMARANCZOWY,CZERWONY;
    }
    enum Stan{
        CHASE,FRIGHTENED,EATEN,SCUTTER;
    }

    private JakiDuszek jakiDuszek;
    private int PozXduszka;
    private int PozYduszka;
    private int PozXPixelduszka;
    private int PozYPixelduszka;
    private int celX;
    private int celY;
    private Kierunek kierunek;
    private Stan stan;
    private int predkosc;

    public Duszek(JakiDuszek duszek){
        jakiDuszek = duszek;
        predkosc = 1;
        PozXPixelduszka = rozmiarPola*1;
        PozYPixelduszka = rozmiarPola*1;
        PozXduszka = PozXPixelduszka/rozmiarPola;
        PozYduszka = PozYPixelduszka/rozmiarPola;
    }

    private void przejdzDoPola(int x, int y) {
        int roznicaX = x * rozmiarPola - PozXPixelduszka;
        int roznicaY = y * rozmiarPola - PozYPixelduszka;
        if (roznicaX != 0) {
            if (roznicaX > 0)
                PozXPixelduszka += predkosc;
            else
                PozXPixelduszka -= predkosc;
        } else if (roznicaY != 0) {
            if (roznicaY > 0)
                PozYPixelduszka += predkosc;
            else
                PozYPixelduszka -= predkosc;
        }
        PozXduszka = PozXPixelduszka/rozmiarPola;
        PozYduszka = PozYPixelduszka/rozmiarPola;
    }

    public void idzDoCelu(ArrayList<Pole> sciezka){
        if (sciezka.size() == 0)
            return;
        if (sciezka.get(0).xSiatka * 32 == PozXduszka && sciezka.get(0).ySiatka * 32 == PozYduszka)
            sciezka.remove(0);
        przejdzDoPola(sciezka.get(0).xSiatka, sciezka.get(0).ySiatka);
    }

    public static void ruszDuszkami(){
        for (Duszek duszek : DuszekAR) {
            switch (duszek.jakiDuszek){
                case CZERWONY -> {
                    ArrayList<Pole> sciezka = Pathfinding.znajdzSciezke(duszek.getPozXduszka(), duszek.getPozYduszka(), pacman.getPozXPacMana(), pacman.getPozYPacMana());
                    duszek.idzDoCelu(sciezka);
                }
                case POMARANCZOWY -> {

                }
                case NIEBIESKI -> {

                }
                case ROZOWY -> {

                }
            }
        }
    }

    public JakiDuszek getJakiDuszek() {
        return jakiDuszek;
    }

    public int getPozXduszka() {
        return PozXduszka;
    }

    public int getPozYduszka() {
        return PozYduszka;
    }

    public int getPozXPixelduszka() {
        return PozXPixelduszka;
    }

    public void setPozXPixelduszka(int pozXPixelduszka) {
        PozXPixelduszka = pozXPixelduszka;
        PozXduszka = pozXPixelduszka / rozmiarPola;
    }

    public int getPozYPixelduszka() {
        return PozYPixelduszka;
    }

    public void setPozYPixelduszka(int pozYPixelduszka) {
        PozYPixelduszka = pozYPixelduszka;
        PozYduszka = pozYPixelduszka / rozmiarPola;
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