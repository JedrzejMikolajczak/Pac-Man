package com.company;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import static com.company.Duszek.*;

enum Kierunek{
    GORA,DOL,LEWO,PRAWO;
}
public class Main {
    public static final int szerokoscPlanszy = 28;
    public static final int wysokoscPlanszy = 31;
    public static final int rozmiarPola = 16;
    public static final int skala = 2;

    public static ArrayList<Duszek> DuszekAR = new ArrayList<>();

    public static int[][] planszaWartosci = new int[szerokoscPlanszy][wysokoscPlanszy];
    public static Pole[][] plansza = new Pole[szerokoscPlanszy][wysokoscPlanszy];
    public static JFrame gra;
    public static Panel panel;

    public static PacMan pacman = new PacMan(16, 16);

    public static void ReadFromFile() throws FileNotFoundException {
        /* 1- dół prawo
           2- dół lewo
           3- góra prawo
           4- góra lewo
           5- mała kropka
           6- pusta
           7- duża kropka
           8- pion
           9- poziom */
        File file = new File("Plansza.txt");
        Scanner in = new Scanner(file);
        for (int y = 0; y < wysokoscPlanszy; y++) {
            for (int x = 0; x < szerokoscPlanszy; x++) {
                if(in.hasNext()) {
                    planszaWartosci[x][y] = in.nextInt();
                }
            }
        }
    }

    public static void WypelnieniePlanszy() {
        for (int y = 0; y < wysokoscPlanszy; y++) {
            for (int x = 0; x < szerokoscPlanszy; x++) {
                switch(planszaWartosci[x][y]) {
                    case 1:
                        plansza[x][y] = new Pole(Pole.RodzajPola.DOLPRAWY, false, false, x, y);
                        break;
                    case 2:
                        plansza[x][y] = new Pole(Pole.RodzajPola.DOLLEWY, false, false, x, y);
                        break;
                    case 3:
                        plansza[x][y] = new Pole(Pole.RodzajPola.GORAPRAWY, false, false, x, y);
                        break;
                    case 4:
                        plansza[x][y] = new Pole(Pole.RodzajPola.GORALEWY, false, false, x, y);
                        break;
                    case 5:
                        plansza[x][y] = new Pole(Pole.RodzajPola.PUSTE, true, false, x, y);
                        break;
                    case 0:
                    case 6:
                        plansza[x][y] = new Pole(Pole.RodzajPola.PUSTE, false, false, x, y);
                        break;
                    case 7:
                        plansza[x][y] = new Pole(Pole.RodzajPola.PUSTE, false, true, x, y);
                        break;
                    case 8:
                        plansza[x][y] = new Pole(Pole.RodzajPola.PIONOWA, false, false, x, y);
                        break;
                    case 9:
                        plansza[x][y] = new Pole(Pole.RodzajPola.POZIOMA, false, false, x, y);
                        break;

                }
            }
        }
    }

    public static void TworzenieDuszkow()
    {
        DuszekAR.add(new Duszek(JakiDuszek.CZERWONY));
        DuszekAR.add(new Duszek(JakiDuszek.POMARANCZOWY));
        DuszekAR.add(new Duszek(JakiDuszek.NIEBIESKI));
        DuszekAR.add(new Duszek(JakiDuszek.ROZOWY));
    }

    public static double GetDistance(int x1, int y1, int x2, int y2){
        double a = ((x2 - x1) * (x2 - x1)) + ((y2 - y1) * (y2 - y1));
        return Math.sqrt(a);
    }

    public static ArrayList<Pole> getSasiadow(Pole pole) {
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

    public static void UtworzPanel(){
        gra = new JFrame();
        panel = new Panel();
        gra.add(panel);
        //gra.setExtendedState(JFrame.MAXIMIZED_BOTH);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        gra.setSize(screenSize.width, screenSize.height);
        //gra.setUndecorated(true);
        gra.setResizable(false);
        gra.setTitle("Pac-Man");
        gra.setLocationRelativeTo(null);
        gra.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gra.setVisible(true);
        gra.addKeyListener(panel);
    }

    public static void main(String[] args) throws FileNotFoundException {
        ReadFromFile();
        WypelnieniePlanszy();
        UtworzPanel();
        /*for (int y = 0; y < 31; y++) {
            for (int x = 0; x < 28; x++) {
                System.out.print(planszaWartosci[x][y] + " ");
            }
            System.out.println();
        }*/
        TworzenieDuszkow();
        while (true){
            //Update
            Duszek.ruszDuszkami();
            pacman.rusz();
            try {
                Thread.sleep(16);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
