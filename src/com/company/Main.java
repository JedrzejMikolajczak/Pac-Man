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

    public static int[][] plansza = new int[szerokoscPlanszy][wysokoscPlanszy];
    public static JFrame gra;
    public static Panel panel;

    public static void ReadFromFile() throws FileNotFoundException {
        File file = new File("Plansza.txt");
        Scanner in = new Scanner(file);
        for (int y = 0; y < wysokoscPlanszy; y++) {
            for (int x = 0; x < szerokoscPlanszy; x++) {
                plansza[x][y] = in.nextInt();
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
