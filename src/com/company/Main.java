package com.company;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

enum Kierunek{
    GORA,DOL,LEWO,PRAWO;
}
public class Main {
    public static int szerokoscPlanszy = 28;
    public static int wysokoscPlanszy = 31;
    public static int rozmiarPola = 16;
    public static int skala = 2;

    public static int[][] planszaWartosci = new int[szerokoscPlanszy][wysokoscPlanszy];
    public static Pole[][] plansza = new Pole[szerokoscPlanszy][wysokoscPlanszy];
    public static JFrame gra;
    public static Panel panel;

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

    public static double GetDistance(int x1, int y1, int x2, int y2){
        double a = ((x2 - x1) * (x2 - x1)) + ((y2 - y1) * (y2 - y1));
        return Math.sqrt(a);
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
    }

    public static void main(String[] args) throws FileNotFoundException {
        ReadFromFile();
        WypelnieniePlanszy();
        UtworzPanel();
        for (int y = 0; y < 31; y++) {
            for (int x = 0; x < 28; x++) {
                System.out.print(planszaWartosci[x][y] + " ");
            }
            System.out.println();
        }
    }
}
