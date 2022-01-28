package com.company;
import static com.company.Main.*;

public class PacMan {
    private int PozXPacMana;
    private int PozYPacMana;
    private static int Punkty;

    private int PredkoscRuchuPacMana;

    private int PozXPixelPacMana;
    private int PozYPixelPacMana;

    public static int IloscPkt;

    private Kierunek kierunekAktualny;
    private Kierunek kierunekNastepny;


    public PacMan(int pozXPixelPacMana, int pozYPixelPacMana) {
        PozXPixelPacMana = pozXPixelPacMana;
        PozYPixelPacMana = pozYPixelPacMana;
        PozXPacMana = pozXPixelPacMana/ rozmiarPola;
        PozYPacMana = pozYPixelPacMana/ rozmiarPola;
        kierunekAktualny = Kierunek.PRAWO;
        kierunekNastepny = kierunekAktualny;
        PredkoscRuchuPacMana = 1;
    }

    private void zmienKierunek(){
        if (kierunekAktualny == Kierunek.PRAWO || kierunekAktualny == Kierunek.LEWO){
            //Poziomy
            if (kierunekNastepny == Kierunek.PRAWO && plansza[PozXPacMana][PozYPacMana].getKierunki().contains(Kierunek.PRAWO)){
                kierunekAktualny = kierunekNastepny;
            }
            if (kierunekNastepny == Kierunek.LEWO && plansza[PozXPacMana][PozYPacMana].getKierunki().contains(Kierunek.LEWO)){
                kierunekAktualny = kierunekNastepny;
            }

            if (kierunekNastepny == Kierunek.DOL && plansza[PozXPacMana][PozYPacMana].getKierunki().contains(Kierunek.DOL)){
                if (PozXPixelPacMana % rozmiarPola == 0)
                {
                    kierunekAktualny = kierunekNastepny;
                }
            }
            if (kierunekNastepny == Kierunek.GORA && plansza[PozXPacMana][PozYPacMana].getKierunki().contains(Kierunek.GORA)){
                if (PozXPixelPacMana % rozmiarPola == 0)
                {
                    kierunekAktualny = kierunekNastepny;
                }
            }

        }else {
            //Pionowy

            if (kierunekNastepny == Kierunek.DOL && plansza[PozXPacMana][PozYPacMana].getKierunki().contains(Kierunek.DOL)){
                kierunekAktualny = kierunekNastepny;
            }
            if (kierunekNastepny == Kierunek.GORA && plansza[PozXPacMana][PozYPacMana].getKierunki().contains(Kierunek.GORA)){
                kierunekAktualny = kierunekNastepny;
            }

            if (kierunekNastepny == Kierunek.PRAWO && plansza[PozXPacMana][PozYPacMana].getKierunki().contains(Kierunek.PRAWO)){
                if (PozYPixelPacMana % rozmiarPola == 0)
                {
                    kierunekAktualny = kierunekNastepny;
                }
            }
            if (kierunekNastepny == Kierunek.LEWO && plansza[PozXPacMana][PozYPacMana].getKierunki().contains(Kierunek.LEWO)){
                if (PozYPixelPacMana % rozmiarPola == 0)
                {
                    kierunekAktualny = kierunekNastepny;
                }
            }
        }
    }

    private void przejdzDoPola(int x, int y) {
        int roznicaX = x * rozmiarPola - PozXPixelPacMana;
        int roznicaY = y * rozmiarPola - PozYPixelPacMana;
        if (roznicaX != 0) {
            if (roznicaX > 0)
                PozXPixelPacMana += PredkoscRuchuPacMana;
            else
                PozXPixelPacMana -= PredkoscRuchuPacMana;
        } else if (roznicaY != 0) {
            if (roznicaY > 0)
                PozYPixelPacMana += PredkoscRuchuPacMana;
            else
                PozYPixelPacMana -= PredkoscRuchuPacMana;
        }
        if (PozXPixelPacMana%rozmiarPola == 0){
            PozXPacMana = PozXPixelPacMana/rozmiarPola;
        }
        if (PozYPixelPacMana%rozmiarPola == 0){
            PozYPacMana = PozYPixelPacMana/rozmiarPola;
        }
    }

    public void rusz(){
        zmienKierunek();
        switch (kierunekAktualny) {
            case PRAWO -> {
                if (Pole.czyPoleIstnieje(PozXPacMana + 1, PozYPacMana)) {
                    if (plansza[PozXPacMana + 1][PozYPacMana].getCzyDaSieWejsc())
                        przejdzDoPola(PozXPacMana + 1, PozYPacMana);
                }
                else {
                    PozXPacMana = 0;
                    PozXPixelPacMana = 0;
                }
            }
            case LEWO -> {
                if (Pole.czyPoleIstnieje(PozXPacMana - 1, PozYPacMana)) {
                    if (plansza[PozXPacMana - 1][PozYPacMana].getCzyDaSieWejsc())
                        przejdzDoPola(PozXPacMana - 1, PozYPacMana);
                }
                else {
                    PozXPacMana = szerokoscPlanszy - 1;
                    PozXPixelPacMana = (szerokoscPlanszy - 1) * rozmiarPola;
                }
            }
            case GORA -> {
                if (plansza[PozXPacMana][PozYPacMana - 1].getCzyDaSieWejsc())
                    przejdzDoPola(PozXPacMana, PozYPacMana - 1);
            }
            case DOL -> {
                if (plansza[PozXPacMana][PozYPacMana + 1].getCzyDaSieWejsc())
                    przejdzDoPola(PozXPacMana, PozYPacMana + 1);
            }
        }
        //plansza[PozXPacMana][PozYPacMana].zjedzZawartosc();
        switch(plansza[PozXPacMana][PozYPacMana].zjedzZawartosc())
        {
            case 1:
                IloscPkt+= 10;
                break;
            case 2:
                IloscPkt+= 50;
                //METODA NA BOOSTA
                break;
        }
    }

    public int getPozXPacMana() { return PozXPacMana; }

    //public void setPozXPacMana(int pozXPacMana) { PozXPacMana = pozXPacMana; }

    public int getPozYPacMana() { return PozYPacMana; }

    //public void setPozYPacMana(int pozYPacMana) { PozYPacMana = pozYPacMana; }

    public int getPozXPixelPacMana() { return PozXPixelPacMana; }

    public void setPozXPixelPacMana(int pozXPixelPacMana) {
        PozXPixelPacMana = pozXPixelPacMana;
        PozXPacMana = PozXPixelPacMana / rozmiarPola;
    }

    public int getPozYPixelPacMana() { return PozYPixelPacMana; }

    public void setPozYPixelPacMana(int pozYPixelPacMana) {
        PozYPixelPacMana = pozYPixelPacMana;
        PozYPacMana = PozYPixelPacMana / rozmiarPola;
    }

    public Kierunek getKierunekNastepny() {
        return kierunekNastepny;
    }

    public void setKierunekNastepny(Kierunek kierunek) {
        this.kierunekNastepny = kierunek;
    }

    public Kierunek getKierunekAktualny() {
        return kierunekAktualny;
    }

    public void PunktyMetod()
    {
        if(plansza[PozXPacMana][PozYPacMana].getCzyKropka()) {Punkty++;}
    }




}