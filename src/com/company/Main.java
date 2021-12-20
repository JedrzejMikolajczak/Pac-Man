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
    public static double distance = 0;

    public static void ReadFromFile() throws FileNotFoundException {
        File file = new File("Plansza.txt");
        Scanner in = new Scanner(file);

    }

    public static void GetDistance(int x1, int y1, int x2, int y2){
        double a = ((x2 - x1) * (x2 - x1)) + ((y2 - y1) * (y2 - y1));
        distance = Math.sqrt(a);
    }

    public static void main(String[] args) {
        /*GetDistance(10, 19, 15, 7);
        System.out.println(distance); */
        JFrame gra = new JFrame();
        Panel panel = new Panel();
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
}
