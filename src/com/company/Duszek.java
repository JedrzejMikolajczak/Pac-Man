package com.company;

import java.util.ArrayList;
import java.util.Random;

import static com.company.Main.*;
import static com.company.Main.pacman;

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
    private ArrayList<Pole> sciezka;

    public Duszek(JakiDuszek duszek){
        jakiDuszek = duszek;
        predkosc = 1;
        PozXPixelduszka = rozmiarPola*1;
        PozYPixelduszka = rozmiarPola*1;
        PozXduszka = PozXPixelduszka/rozmiarPola;
        PozYduszka = PozYPixelduszka/rozmiarPola;
        kierunek = Kierunek.PRAWO;
        sciezka = new ArrayList<Pole>();
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

        if (PozXPixelduszka%rozmiarPola == 0){
            PozXduszka = PozXPixelduszka/rozmiarPola;
        }
        if (PozYPixelduszka%rozmiarPola == 0){
            PozYduszka = PozYPixelduszka/rozmiarPola;
        }

        if (roznicaX > 0 && roznicaY == 0)
            kierunek = Kierunek.PRAWO;
        if (roznicaX < 0 && roznicaY == 0)
            kierunek = Kierunek.LEWO;
        if (roznicaX == 0 && roznicaY < 0)
            kierunek = Kierunek.GORA;
        if (roznicaX == 0 && roznicaY > 0)
            kierunek = Kierunek.DOL;
    }

    public void idzDoCelu(ArrayList<Pole> sciezka){
        if (sciezka.size() == 0) {
            ArrayList<Kierunek> kierunki = plansza[PozXduszka][PozYduszka].getKierunki();
            Random random = new Random();
            int rng = random.nextInt(kierunki.size());
            switch (kierunki.get(rng)) {
                case PRAWO -> {
                    sciezka.add(plansza[PozXduszka + 1][PozYduszka]);
                }
                case LEWO -> {
                    sciezka.add(plansza[PozXduszka - 1][PozYduszka]);
                }
                case DOL -> {
                    sciezka.add(plansza[PozXduszka][PozYduszka + 1]);
                }
                case GORA -> {
                    sciezka.add(plansza[PozXduszka][PozYduszka - 1]);
                }
            }
        }
        if (!sciezka.isEmpty() && sciezka.get(0).xSiatka * 16 == PozXPixelduszka && sciezka.get(0).ySiatka * 16 == PozYPixelduszka)
            sciezka.remove(0);
        if (sciezka.size() == 0)
            return;
        przejdzDoPola(sciezka.get(0).xSiatka, sciezka.get(0).ySiatka);
    }


    public static void ruszDuszkami(){
        for (Duszek duszek : DuszekAR) {
            if (duszek.getPozXPixelduszka() % rozmiarPola == 0 && duszek.getPozYPixelduszka() % rozmiarPola == 0) {
                switch (duszek.jakiDuszek) {
                    case CZERWONY -> {
                        //int[] tablica = Pole.znajdzWolnePole(pacman.getPozXPacMana(),pacman.getPozYPacMana());
                        //ArrayList<Pole> sciezka = Pathfinding.znajdzSciezke(duszek.getPozXduszka(), duszek.getPozYduszka(), tablica[0], tablica[1]);
                        duszek.celX = pacman.getPozXPacMana();
                        duszek.celY = pacman.getPozYPacMana();
                    }
                    case POMARANCZOWY -> {
                        if (GetDistance(pacman.getPozXPacMana(), pacman.getPozYPacMana(), duszek.getPozXduszka(), duszek.getPozYduszka()) >= 8){
                            duszek.celX = pacman.getPozXPacMana();
                            duszek.celY = pacman.getPozYPacMana();
                        }
                        else {
                            duszek.celX = 1;
                            duszek.celY = wysokoscPlanszy - 2;
                        }
                    }
                    case NIEBIESKI -> {

                    }
                    case ROZOWY -> {
                        int[] tablica = new int[2];
                        switch (pacman.getKierunekAktualny()) {
                            case PRAWO -> {
                                tablica = Pole.znajdzWolnePole(pacman.getPozXPacMana() + 4, pacman.getPozYPacMana());
                            }
                            case LEWO -> {
                                tablica = Pole.znajdzWolnePole(pacman.getPozXPacMana() - 4, pacman.getPozYPacMana());
                            }
                            case DOL -> {
                                tablica = Pole.znajdzWolnePole(pacman.getPozXPacMana(), pacman.getPozYPacMana() + 4);
                            }
                            case GORA -> {
                                tablica = Pole.znajdzWolnePole(pacman.getPozXPacMana(), pacman.getPozYPacMana() - 4);
                            }
                        }
                        duszek.celX = tablica[0];
                        duszek.celY = tablica[1];
                    }
                }
                duszek.sciezka = Pathfinding.znajdzSciezke(duszek.getPozXduszka(), duszek.getPozYduszka(), duszek.getCelX(), duszek.getCelY(), duszek.kierunek);
            }

            duszek.idzDoCelu(duszek.sciezka);
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