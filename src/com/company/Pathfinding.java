package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import static com.company.Main.*;

public class Pathfinding {

    static Pole poczotkowePole;
    static Pole koncowePole;

    static ArrayList<Pole> otwartySet;
    static HashSet<Pole> zamknietySet;

    static ArrayList<Pole> znajdzSciezke(int startX, int startY, int celX, int celY){

        poczotkowePole = plansza[startX][startY];
        koncowePole = plansza[celX][celY];

        otwartySet = new ArrayList<Pole>();
        zamknietySet = new HashSet<Pole>();

        otwartySet.add(poczotkowePole);

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
                if (!sasiad.czyDaSieWejsc || zamknietySet.contains(sasiad))
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

        while (aktualnePole != poczotkowePole) {
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
