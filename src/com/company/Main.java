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

    public static int[][] planszaWartosci = new int[szerokoscPlanszy][wysokoscPlanszy];
    public static Pole[][] plansza = new Pole[szerokoscPlanszy][wysokoscPlanszy];
    public static JFrame gra;
    public static Panel panel;

    public static void ReadFromFile() throws FileNotFoundException {
        /* 0 - podwójne dół- prawo
        1 - podwójne poziome górne
        2 - Podwójne rozgałęźnik lewo-prawo-dół
        3 - podwójne rogałęźnik prawo-lewo-dół
        4 - podwójne dół-lewo
        5 - podwójne pionowe - lewo
        6 - puste
        7 - pojedyńcze pionowe
        8 - pojedyńcze góra-prawo
        9 - pojedyńcze dół-prawo
        10 - pojedyńcze poziome
        11 - pojedyńcze dół-lewo
        12 - pojedyńcze góra-lewo
        13 - podwójne góra-prawo
        14 - podwójne góra-lewo
        15 - prostokątne dół-prawo
        16 - prostokątne poziome górne
        17 - biała bramka
        18 - prostokątne dół-lewo
        19 - prostokątne pionowe lewe
        20 - prostokątne pionowe prawe
        21 - podwójne poziome dolne
        22 - prostokątne góra-prawo
        23 - prostkątne poziome dolne
        24 - prostokątne góra-lewo
        25 - podwójne pionowe - prawo */

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
        UtworzPanel();
    }
}
