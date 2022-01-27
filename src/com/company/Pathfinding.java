package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import static com.company.Main.*;

public class Pathfinding {

    private static Pole poczatkowePole;
    private static Pole koncowePole;

    private static ArrayList<Pole> otwartySet;
    private static HashSet<Pole> zamknietySet;

    public static ArrayList<Pole> znajdzSciezke(int startX, int startY, int celX, int celY, Kierunek kierunek){

        poczatkowePole = plansza[startX][startY];
        koncowePole = plansza[celX][celY];

        otwartySet = new ArrayList<Pole>();
        zamknietySet = new HashSet<Pole>();

        otwartySet.add(poczatkowePole);

        while (!otwartySet.isEmpty()) {
            Pole aktualnePole = otwartySet.get(0);
            for (int i = 1; i < otwartySet.size(); i++) {
                if (otwartySet.get(i).fCost() < aktualnePole.fCost() || otwartySet.get(i).fCost() == aktualnePole.fCost() && otwartySet.get(i).hCost < aktualnePole.hCost){
                    aktualnePole = otwartySet.get(i);
                }
            }
            otwartySet.remove(aktualnePole);
            zamknietySet.add(aktualnePole);

            if (aktualnePole == koncowePole){
                return oddtworzSiezke();
            }
            for (Pole sasiad : getSasiadow(aktualnePole)) {
                if (sasiadPrzeciwnyDoKierunku(sasiad, kierunek, startX, startY))
                    continue;
                if (!sasiad.getCzyDaSieWejsc() || zamknietySet.contains(sasiad))
                    continue;
                int nowyKosztRuchuDoSasiada = aktualnePole.gCost + getOdleglosc(aktualnePole, sasiad);
                if (nowyKosztRuchuDoSasiada < sasiad.gCost || !otwartySet.contains(sasiad)) {
                    sasiad.gCost = nowyKosztRuchuDoSasiada;
                    sasiad.hCost = getOdleglosc(sasiad, koncowePole);
                    sasiad.rodzic = aktualnePole;

                    if (!otwartySet.contains(sasiad))
                        otwartySet.add(sasiad);
                }
            }
        }

        return new ArrayList<Pole>();
    }

    private static ArrayList<Pole> oddtworzSiezke() {
        ArrayList<Pole> sciezka = new ArrayList<Pole>();
        Pole aktualnePole = koncowePole;

        while (aktualnePole != poczatkowePole) {
            sciezka.add(aktualnePole);
            aktualnePole = aktualnePole.rodzic;
        }
        Collections.reverse(sciezka);
        return sciezka;
    }

    private static int getOdleglosc(Pole poleA, Pole poleB) {
        int dstX = Math.abs(poleA.xSiatka - poleB.xSiatka);
        int dstY = Math.abs(poleA.ySiatka - poleB.ySiatka);

        return 10 * (dstX + dstY);
    }


    private static ArrayList<Pole> getSasiadow(Pole pole) {
        ArrayList<Pole> sasiedzi = new ArrayList<>();
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                if (x == 0 && y == 0 || (x != 0 && y != 0))
                    continue;

                int sprawdzX = pole.xSiatka + x;
                int sprawdzY = pole.ySiatka + y;

                if (Pole.czyPoleIstnieje(sprawdzX, sprawdzY)) {
                    sasiedzi.add(plansza[sprawdzX][sprawdzY]);
                }
            }
        }
        return sasiedzi;
    }

    private static Boolean sasiadPrzeciwnyDoKierunku(Pole sasiad, Kierunek kierunek, int startX, int startY){
        switch (kierunek){
            case PRAWO -> {
                if (sasiad.xSiatka == startX - 1 && sasiad.ySiatka == startY)
                    return true;
            }
            case LEWO -> {
                if (sasiad.xSiatka == startX + 1 && sasiad.ySiatka == startY)
                    return true;
            }
            case DOL -> {
                if (sasiad.xSiatka == startX && sasiad.ySiatka == startY - 1)
                    return true;
            }
            case GORA -> {
                if (sasiad.xSiatka == startX && sasiad.ySiatka == startY + 1)
                    return true;
            }
        }
        return false;
    }
}