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
    ImageIcon pacmanFull = new ImageIcon("src/Grafika/pacmanFull.png");

    ImageIcon czerwonyDuszek = new ImageIcon("src/Grafika/czerwonyDuszek.png");
    ImageIcon pomaranczowyDuszek = new ImageIcon("src/Grafika/pomaranczowyDuszek.png");
    ImageIcon niebieskiDuszek = new ImageIcon("src/Grafika/niebieskiDuszek.png");
    ImageIcon rozowyDuszek = new ImageIcon("src/Grafika/rozowyDuszek.png");

    ImageIcon oczyDol = new ImageIcon("src/Grafika/oczyDol.png");
    ImageIcon oczyGora = new ImageIcon("src/Grafika/oczyGora.png");
    ImageIcon oczyLewo = new ImageIcon("src/Grafika/oczyLewo.png");
    ImageIcon oczyPrawo = new ImageIcon("src/Grafika/oczyPrawo.png");

    ImageIcon dobre = new ImageIcon("src/Grafika/dobre.png");
    ImageIcon zle = new ImageIcon("src/Grafika/zle.png");
    ImageIcon sciezka = new ImageIcon("src/Grafika/path.png");
    //public static ArrayList<Pole> path;

    public Panel(){
        addKeyListener(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        narysujMape(g);

        //Siatka do debugowania
        for (int i = 0; i < szerokoscPlanszy; i++) {
            g.drawLine(i*skala*rozmiarPola,0,i*skala*rozmiarPola,wysokoscPlanszy*rozmiarPola*skala);
        }
        for (int i = 0; i < wysokoscPlanszy; i++) {
            g.drawLine(0, i*skala*rozmiarPola,szerokoscPlanszy*rozmiarPola*skala, i*rozmiarPola*skala);
        }

        //g.drawImage(pacmanpng.getImage(), pacman.getPozXPixelPacMana() * skala, pacman.getPozYPixelPacMana() * skala, skala * rozmiarPola, skala * rozmiarPola, null);

        narysujDuszki(g);
        narysujPacmana(g);

        /*
        //ArrayList<Pole> path = new ArrayList<Pole>();
        //path = Pathfinding.znajdzSciezke(1,1,szerokoscPlanszy-2, wysokoscPlanszy-3);
        for (int x = 0; x < szerokoscPlanszy; x++) {
            for (int y = 0; y < wysokoscPlanszy; y++) {
                if (plansza[x][y].getCzyDaSieWejsc())
                    g.drawImage(dobre.getImage(), x * skala * rozmiarPola, y * skala * rozmiarPola, null);
                else
                    g.drawImage(zle.getImage(), x * skala * rozmiarPola, y * skala * rozmiarPola, null);
                //if (path!=null)
                //if (path.contains(plansza[x][y]))
                 //   g.drawImage(sciezka.getImage(), x * skala * rozmiarPola, y * skala * rozmiarPola, null);
            }
        }*/

        try {
            Thread.sleep(33);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        repaint();
    }

    private void narysujMape(Graphics g){
        for (int y = 0; y < wysokoscPlanszy; y++) {
            for (int x = 0; x < szerokoscPlanszy; x++) {
                switch (plansza[x][y].getRodzajPola()) {
                    case PIONOWA -> g.drawImage(pionowa.getImage(), x * rozmiarPola * skala, y * rozmiarPola * skala, skala * rozmiarPola, skala * rozmiarPola, null);
                    case POZIOMA -> g.drawImage(pozioma.getImage(), x * rozmiarPola * skala, y * rozmiarPola * skala, skala * rozmiarPola, skala * rozmiarPola, null);
                    case DOLLEWY -> g.drawImage(dolLewy.getImage(), x * rozmiarPola * skala, y * rozmiarPola * skala, skala * rozmiarPola, skala * rozmiarPola, null);
                    case DOLPRAWY -> g.drawImage(dolPrawy.getImage(), x * rozmiarPola * skala, y * rozmiarPola * skala, skala * rozmiarPola, skala * rozmiarPola, null);
                    case GORALEWY -> g.drawImage(goraLewy.getImage(), x * rozmiarPola * skala, y * rozmiarPola * skala, skala * rozmiarPola, skala * rozmiarPola, null);
                    case GORAPRAWY -> g.drawImage(goraPrawy.getImage(), x * rozmiarPola * skala, y * rozmiarPola * skala, skala * rozmiarPola, skala * rozmiarPola, null);
                    case PUSTE -> {
                        if (plansza[x][y].getCzyBoost()) {
                            g.drawImage(boost.getImage(), x * rozmiarPola * skala, y * rozmiarPola * skala, skala * rozmiarPola, skala * rozmiarPola, null);
                        } else if (plansza[x][y].getCzyKropka()) {
                            g.drawImage(kropka.getImage(), x * rozmiarPola * skala, y * rozmiarPola * skala, skala * rozmiarPola, skala * rozmiarPola, null);
                        } else {
                            g.drawImage(puste.getImage(), x * rozmiarPola * skala, y * rozmiarPola * skala, skala * rozmiarPola, skala * rozmiarPola, null);
                        }
                    }
                }
            }
        }
    }

    private int numerKlatkiDuszek = 0;
    private void narysujDuszki(Graphics g) {
        int n = 0;
        numerKlatkiDuszek++;
        if (numerKlatkiDuszek > 3)
            n = 1;
        if (numerKlatkiDuszek > 6)
            numerKlatkiDuszek = 0;
        g.drawImage(czerwonyDuszek.getImage(), DuszekAR.get(0).getPozXPixelduszka() * skala, DuszekAR.get(0).getPozYPixelduszka() * skala, (DuszekAR.get(0).getPozXPixelduszka() + rozmiarPola) * skala, (DuszekAR.get(0).getPozYPixelduszka() + rozmiarPola) * skala, rozmiarPola*n, 0, rozmiarPola*(n+1), rozmiarPola, null);
        g.drawImage(pomaranczowyDuszek.getImage(), DuszekAR.get(1).getPozXPixelduszka() * skala, DuszekAR.get(1).getPozYPixelduszka() * skala, (DuszekAR.get(1).getPozXPixelduszka() + rozmiarPola) * skala, (DuszekAR.get(1).getPozYPixelduszka() + rozmiarPola) * skala, rozmiarPola*n, 0, rozmiarPola*(n+1), rozmiarPola, null);
        g.drawImage(niebieskiDuszek.getImage(), DuszekAR.get(2).getPozXPixelduszka() * skala, DuszekAR.get(2).getPozYPixelduszka() * skala, (DuszekAR.get(2).getPozXPixelduszka() + rozmiarPola) * skala, (DuszekAR.get(2).getPozYPixelduszka() + rozmiarPola) * skala, rozmiarPola*n, 0, rozmiarPola*(n+1), rozmiarPola, null);
        g.drawImage(rozowyDuszek.getImage(), DuszekAR.get(3).getPozXPixelduszka() * skala, DuszekAR.get(3).getPozYPixelduszka() * skala, (DuszekAR.get(3).getPozXPixelduszka() + rozmiarPola) * skala, (DuszekAR.get(3).getPozYPixelduszka() + rozmiarPola) * skala, rozmiarPola*n, 0, rozmiarPola*(n+1), rozmiarPola, null);

        for (Duszek duszek : DuszekAR) {
            switch (duszek.getKierunek()){
                case PRAWO -> g.drawImage(oczyPrawo.getImage(), duszek.getPozXPixelduszka() * skala, duszek.getPozYPixelduszka() * skala, skala * rozmiarPola, skala * rozmiarPola, null);
                case LEWO -> g.drawImage(oczyLewo.getImage(), duszek.getPozXPixelduszka() * skala, duszek.getPozYPixelduszka() * skala, skala * rozmiarPola, skala * rozmiarPola, null);
                case GORA -> g.drawImage(oczyGora.getImage(), duszek.getPozXPixelduszka() * skala, duszek.getPozYPixelduszka() * skala, skala * rozmiarPola, skala * rozmiarPola, null);
                case DOL -> g.drawImage(oczyDol.getImage(), duszek.getPozXPixelduszka() * skala, duszek.getPozYPixelduszka() * skala, skala * rozmiarPola, skala * rozmiarPola, null);
            }
        }
    }

    private int numerKlatkiPacman = 0;
    private void narysujPacmana(Graphics g){
        int n = 0;
        numerKlatkiPacman++;
        if (numerKlatkiPacman > 2)
            n = 1;
        if (numerKlatkiPacman > 4)
            n = 2;
        if (numerKlatkiPacman > 6)
            n = 3;
        if (numerKlatkiPacman > 8)
            numerKlatkiPacman = 0;

        int kierunek = 0;
        switch (pacman.getKierunekAktualny()){
            case PRAWO -> kierunek = 0;
            case LEWO -> kierunek = 1;
            case DOL -> kierunek = 2;
            case GORA -> kierunek = 3;
        }
        g.drawImage(pacmanFull.getImage(), pacman.getPozXPixelPacMana() * skala, pacman.getPozYPixelPacMana() * skala, (pacman.getPozXPixelPacMana() + rozmiarPola) * skala, (pacman.getPozYPixelPacMana() + rozmiarPola) * skala, rozmiarPola*n,rozmiarPola*kierunek,rozmiarPola*(n+1),rozmiarPola*(kierunek+1), null);
    }

/*
    public static void PrzesunPacMana(){
        switch (pacman.getKierunekAktualny()) {
            case PRAWO -> {if(plansza[pacman.getPozXPacMana() + 1][pacman.getPozYPacMana()].getRodzajPola() == Pole.RodzajPola.PUSTE || pacman.getPozXPixelPacMana()% rozmiarPola > 1)
                pacman.setPozXPixelPacMana(pacman.getPozXPixelPacMana() + PacMan.PredkoscRuchuPacMana);}
            case LEWO -> {if(plansza[pacman.getPozXPacMana() - 1][pacman.getPozYPacMana()].getRodzajPola() == Pole.RodzajPola.PUSTE || pacman.getPozXPixelPacMana()% rozmiarPola > 1)
                pacman.setPozXPixelPacMana(pacman.getPozXPixelPacMana() - PacMan.PredkoscRuchuPacMana);}
            case DOL -> {if(plansza[pacman.getPozXPacMana()][pacman.getPozYPacMana() + 1].getRodzajPola() == Pole.RodzajPola.PUSTE || pacman.getPozYPixelPacMana()% rozmiarPola > 2)
                pacman.setPozYPixelPacMana(pacman.getPozYPixelPacMana() + PacMan.PredkoscRuchuPacMana);}
            case GORA -> {if(plansza[pacman.getPozXPacMana()][pacman.getPozYPacMana() - 1].getRodzajPola() == Pole.RodzajPola.PUSTE || pacman.getPozYPixelPacMana()% rozmiarPola > 2)
                pacman.setPozYPixelPacMana(pacman.getPozYPixelPacMana() - PacMan.PredkoscRuchuPacMana);}
        }
    }
*/

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_D:
                pacman.setKierunekNastepny(Kierunek.PRAWO);
                break;
            case KeyEvent.VK_A:
                pacman.setKierunekNastepny(Kierunek.LEWO);
                break;
            case KeyEvent.VK_W:
                pacman.setKierunekNastepny(Kierunek.GORA);
                break;
            case KeyEvent.VK_S:
                pacman.setKierunekNastepny(Kierunek.DOL);
                break;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}