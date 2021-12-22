package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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

    public Panel(){
        addKeyListener(this);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        for (int y = 0; y < 31; y++) {
            for (int x = 0; x < 28; x++) {
                switch (plansza[x][y].rodzajPola) {
                    case PIONOWA -> g.drawImage(pionowa.getImage(), x * rozmiarPola, y * rozmiarPola, null);
                    case POZIOMA -> g.drawImage(pozioma.getImage(), x * rozmiarPola, y * rozmiarPola, null);
                    case DOLLEWY -> g.drawImage(dolLewy.getImage(), x * rozmiarPola, y * rozmiarPola, null);
                    case DOLPRAWY -> g.drawImage(dolPrawy.getImage(), x * rozmiarPola, y * rozmiarPola, null);
                    case GORALEWY -> g.drawImage(goraLewy.getImage(), x * rozmiarPola, y * rozmiarPola, null);
                    case GORAPRAWY -> g.drawImage(goraPrawy.getImage(), x * rozmiarPola, y * rozmiarPola, null);
                    case PUSTE -> {
                        if(plansza[x][y].czyBoost){
                            g.drawImage(boost.getImage(), x * rozmiarPola, y * rozmiarPola, null);
                        } else if(plansza[x][y].czyKropka) {
                            g.drawImage(kropka.getImage(), x * rozmiarPola, y * rozmiarPola, null);
                        } else {
                            g.drawImage(puste.getImage(), x * rozmiarPola, y * rozmiarPola, null);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
