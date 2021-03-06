package com.company;

import java.util.ArrayList;
import static com.company.Main.*;

public class Pole {

    enum RodzajPola{
        POZIOMA, PIONOWA, GORALEWY, GORAPRAWY, DOLLEWY, DOLPRAWY, PUSTE
    }

    private RodzajPola rodzajPola;
    private Boolean czyDaSieWejsc;
    private Boolean czyKropka;
    private Boolean czyBoost;
    private ArrayList<Kierunek> kierunki;
    final int xSiatka;
    final int ySiatka;

    public int gCost;
    public int hCost;

    public Pole rodzic;

    public int fCost() {
        return gCost + hCost;
    }

    public Pole(RodzajPola pole, Boolean kropka, Boolean boost, int xPola, int yPola){
        rodzajPola = pole;
        czyKropka = kropka;
        czyBoost = boost;
        if (rodzajPola == RodzajPola.PUSTE)
            czyDaSieWejsc = true;
        else
            czyDaSieWejsc = false;
        if (planszaWartosci[xPola][yPola] == 0)
            czyDaSieWejsc = false;
        kierunki = new ArrayList<>();
        xSiatka = xPola;
        ySiatka = yPola;
        ustawKierunki(xPola, yPola);
    }

    private void ustawKierunki(int xPola, int yPola){
        if (planszaWartosci[xPola][yPola] == 5 || planszaWartosci[xPola][yPola] == 6 || planszaWartosci[xPola][yPola] == 7)
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                if ((x != 0 && y != 0) || (x == 0 && y == 0))
                    continue;
                if (czyPoleIstnieje(xPola + x, yPola + y))
                    if (planszaWartosci[xPola + x][yPola + y] == 5 || planszaWartosci[xPola + x][yPola + y] == 6 || planszaWartosci[xPola + x][yPola + y] == 7) {
                        if (x > 0)
                            kierunki.add(Kierunek.PRAWO);
                        else if (x < 0)
                            kierunki.add(Kierunek.LEWO);
                        else if (y > 0)
                            kierunki.add(Kierunek.DOL);
                        else if (y < 0)
                            kierunki.add(Kierunek.GORA);

                    }
            }
        }
    }

    public static int[] znajdzWolnePole(int xSiatka, int ySiatka) {
        int odlegloscOdPola = 1;
        double dystansNajlepszy = 10000;
        int xPola = -1;
        int yPola = -1;

        if (czyPoleIstnieje(xSiatka, ySiatka) && plansza[xSiatka][ySiatka].czyDaSieWejsc)
            return new int[] {xSiatka, ySiatka};

        while (xPola == -1 && yPola == -1) {
            for (int x = -odlegloscOdPola; x <= odlegloscOdPola; x++) {
                if (czyPoleIstnieje(xSiatka + x, ySiatka - odlegloscOdPola) && plansza[xSiatka + x][ySiatka - odlegloscOdPola].getCzyDaSieWejsc()) {
                    double dystans = GetDistance(xSiatka, ySiatka, xSiatka + x, ySiatka - odlegloscOdPola);
                    if (dystans < dystansNajlepszy) {
                        xPola = xSiatka + x;
                        yPola = ySiatka - odlegloscOdPola;
                        dystansNajlepszy = dystans;
                    }
                }
            }
            for (int x = -odlegloscOdPola; x <= odlegloscOdPola; x++) {
                if (czyPoleIstnieje(xSiatka + x, ySiatka + odlegloscOdPola) && plansza[xSiatka + x][ySiatka + odlegloscOdPola].getCzyDaSieWejsc()) {
                    double dystans = GetDistance(xSiatka, ySiatka, xSiatka + x, ySiatka + odlegloscOdPola);
                    if (dystans < dystansNajlepszy) {
                        xPola = xSiatka + x;
                        yPola = ySiatka + odlegloscOdPola;
                        dystansNajlepszy = dystans;
                    }
                }
            }
            for (int y = -odlegloscOdPola; y <= odlegloscOdPola; y++) {
                if (czyPoleIstnieje(xSiatka - odlegloscOdPola, ySiatka + y) && plansza[xSiatka - odlegloscOdPola][ySiatka + y].getCzyDaSieWejsc()) {
                    double dystans = GetDistance(xSiatka, ySiatka, xSiatka - odlegloscOdPola, ySiatka + y);
                    if (dystans < dystansNajlepszy) {
                        xPola = xSiatka - odlegloscOdPola;
                        yPola = ySiatka + y;
                        dystansNajlepszy = dystans;
                    }
                }
            }
            for (int y = -odlegloscOdPola; y <= odlegloscOdPola; y++) {
                if (czyPoleIstnieje(xSiatka + odlegloscOdPola, ySiatka + y) && plansza[xSiatka + odlegloscOdPola][ySiatka + y].getCzyDaSieWejsc()) {
                    double dystans = GetDistance(xSiatka, ySiatka, xSiatka + odlegloscOdPola, ySiatka + y);
                    if (dystans < dystansNajlepszy) {
                        xPola = xSiatka + odlegloscOdPola;
                        yPola = ySiatka + y;
                        dystansNajlepszy = dystans;
                    }
                }
            }
            odlegloscOdPola++;
        }
        int[] tablica = new int[2];
        tablica[0] = xPola;
        tablica[1] = yPola;
        return tablica;
    }

    public static Boolean czyPoleIstnieje(int x, int y) {
        if (x >= 0 && y >= 0 && x < szerokoscPlanszy && y < wysokoscPlanszy)
            return true;
        return false;
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

    public ArrayList<Kierunek> getKierunki() {
        return kierunki;
    }

    public int getxSiatka() {
        return xSiatka;
    }

    public int getySiatka() {
        return ySiatka;
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
