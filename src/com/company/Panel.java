package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import static com.company.Main.*;

public class Panel extends JPanel implements KeyListener {

    ImageIcon boost = new ImageIcon("src/Grafika/boost.png");
    ImageIcon kropka = new ImageIcon("src/Grafika/kropka.png");
    ImageIcon puste = new ImageIcon("src/Grafika/puste.png");
    ImageIcon pionowa = new ImageIcon("src/Grafika/pionowa.png");
    ImageIcon pozioma = new ImageIcon("src/Grafika/pozioma.png");
    ImageIcon goraLewy = new ImageIcon("src/Grafika/goraLewy.png");
    ImageIcon goraPrawy = new ImageIcon("src/Grafika/goraPrawy.png");
    ImageIcon dolLewy = new ImageIcon("src/Grafika/dolLewy.png");
    ImageIcon dolPrawy = new ImageIcon("src/Grafika/dolPrawy.png");
    ImageIcon pacmanpng = new ImageIcon("src/Grafika/pacman.png");

    ImageIcon dobre = new ImageIcon("src/Grafika/dobre.png");
    ImageIcon zle = new ImageIcon("src/Grafika/zle.png");
    ImageIcon sciezka = new ImageIcon("src/Grafika/path.png");

    public Panel(){
        addKeyListener(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int y = 0; y < wysokoscPlanszy; y++) {
            for (int x = 0; x < szerokoscPlanszy; x++) {
                switch (plansza[x][y].rodzajPola) {
                    case PIONOWA -> g.drawImage(pionowa.getImage(), x * rozmiarPola * skala, y * rozmiarPola * skala, skala * rozmiarPola, skala * rozmiarPola, null);
                    case POZIOMA -> g.drawImage(pozioma.getImage(), x * rozmiarPola * skala, y * rozmiarPola * skala, skala * rozmiarPola, skala * rozmiarPola, null);
                    case DOLLEWY -> g.drawImage(dolLewy.getImage(), x * rozmiarPola * skala, y * rozmiarPola * skala, skala * rozmiarPola, skala * rozmiarPola, null);
                    case DOLPRAWY -> g.drawImage(dolPrawy.getImage(), x * rozmiarPola * skala, y * rozmiarPola * skala, skala * rozmiarPola, skala * rozmiarPola, null);
                    case GORALEWY -> g.drawImage(goraLewy.getImage(), x * rozmiarPola * skala, y * rozmiarPola * skala, skala * rozmiarPola, skala * rozmiarPola, null);
                    case GORAPRAWY -> g.drawImage(goraPrawy.getImage(), x * rozmiarPola * skala, y * rozmiarPola * skala, skala * rozmiarPola, skala * rozmiarPola, null);
                    case PUSTE -> {
                        if (plansza[x][y].czyBoost) {
                            g.drawImage(boost.getImage(), x * rozmiarPola * skala, y * rozmiarPola * skala, skala * rozmiarPola, skala * rozmiarPola, null);
                        } else if (plansza[x][y].czyKropka) {
                            g.drawImage(kropka.getImage(), x * rozmiarPola * skala, y * rozmiarPola * skala, skala * rozmiarPola, skala * rozmiarPola, null);
                        } else {
                            g.drawImage(puste.getImage(), x * rozmiarPola * skala, y * rozmiarPola * skala, skala * rozmiarPola, skala * rozmiarPola, null);
                        }
                    }
                }
            }
        }
        PrzesunPacMana();
        g.drawImage(pacmanpng.getImage(), pacman.getPozXPixelPacMana() * skala, pacman.getPozYPixelPacMana() * skala, skala * rozmiarPola, skala * rozmiarPola, null);
/*
        ArrayList<Pole> path = new ArrayList<Pole>();
        path = Pathfinding.znajdzSciezke(1,1,szerokoscPlanszy-2, wysokoscPlanszy-3);
        for (int x = 0; x < szerokoscPlanszy; x++) {
            for (int y = 0; y < wysokoscPlanszy; y++) {
                if (plansza[x][y].czyDaSieWejsc)
                    g.drawImage(dobre.getImage(), x * skala * rozmiarPola, y * skala * rozmiarPola, null);
                else
                    g.drawImage(zle.getImage(), x * skala * rozmiarPola, y * skala * rozmiarPola, null);
                if (path.contains(plansza[x][y]))
                    g.drawImage(sciezka.getImage(), x * skala * rozmiarPola, y * skala * rozmiarPola, null);
            }
        }
        */
        try {
            Thread.sleep(33);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        repaint();
    }

    public static void PrzesunPacMana(){

        switch (pacman.getKierunek()) {
            case PRAWO -> {if(plansza[pacman.getPozXPacMana() + 1][pacman.getPozYPacMana()].getRodzajPola() == Pole.RodzajPola.PUSTE || pacman.getPozXPixelPacMana()% szerokoscPlanszy == 0)
                pacman.setPozXPixelPacMana(pacman.getPozXPixelPacMana() + pacman.PredkoscRuchuPacMana);}
            case LEWO -> {if(plansza[pacman.getPozXPacMana() - 1][pacman.getPozYPacMana()].getRodzajPola() == Pole.RodzajPola.PUSTE || pacman.getPozXPixelPacMana()% szerokoscPlanszy == 0)
                pacman.setPozXPixelPacMana(pacman.getPozXPixelPacMana() - pacman.PredkoscRuchuPacMana);}
            case DOL -> {if(plansza[pacman.getPozXPacMana()][pacman.getPozYPacMana() + 1].getRodzajPola() == Pole.RodzajPola.PUSTE || pacman.getPozYPixelPacMana()% szerokoscPlanszy == 0)
                pacman.setPozYPixelPacMana(pacman.getPozYPixelPacMana() + pacman.PredkoscRuchuPacMana);}
            case GORA -> {if(plansza[pacman.getPozXPacMana()][pacman.getPozYPacMana() - 1].getRodzajPola() == Pole.RodzajPola.PUSTE || pacman.getPozYPixelPacMana()% szerokoscPlanszy == 0)
                pacman.setPozYPixelPacMana(pacman.getPozYPixelPacMana() - pacman.PredkoscRuchuPacMana);}

        }
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_D:
                if(plansza[pacman.getPozXPacMana()][pacman.getPozYPacMana()].kierunki.contains(Kierunek.PRAWO)) {
                    pacman.setKierunek(Kierunek.PRAWO);
               }
                break;
            case KeyEvent.VK_A:
                if(plansza[pacman.getPozXPacMana()][pacman.getPozYPacMana()].kierunki.contains(Kierunek.LEWO)) {
                    pacman.setKierunek(Kierunek.LEWO);
                }
                break;
            case KeyEvent.VK_W:
                if(plansza[pacman.getPozXPacMana()][pacman.getPozYPacMana()].kierunki.contains(Kierunek.GORA)) {
                    pacman.setKierunek(Kierunek.GORA);
                }
                break;
            case KeyEvent.VK_S:
                if(plansza[pacman.getPozXPacMana()][pacman.getPozYPacMana()].kierunki.contains(Kierunek.DOL)) {
                    pacman.setKierunek(Kierunek.DOL);
                }
                break;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
