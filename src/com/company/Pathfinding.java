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

    static ArrayList<Pole> znajdzSciezke(int startX, int startY, int celX, int celY){

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

    static ArrayList<Pole> oddtworzSiezke() {
        ArrayList<Pole> sciezka = new ArrayList<Pole>();
        Pole aktualnePole = koncowePole;

        while (aktualnePole != poczatkowePole) {
            sciezka.add(aktualnePole);
            aktualnePole = aktualnePole.rodzic;
        }
        Collections.reverse(sciezka);
        return sciezka;
    }

    static int getOdleglosc(Pole poleA, Pole poleB) {
        int dstX = Math.abs(poleA.xSiatka - poleB.xSiatka);
        int dstY = Math.abs(poleA.ySiatka - poleB.ySiatka);

        return 10 * (dstX + dstY);
    }
}
