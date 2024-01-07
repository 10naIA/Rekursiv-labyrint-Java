// Superklasse man ikke kan opprette objekter av. Har metoden som setter rute-naboer, samt en finn-metode som (egentlig) skal brukes naar finn(null).

import java.util.ArrayList;

abstract class Rute {
    protected int radNr;
    protected int kolNr;
    protected Labyrint labyrint;
    protected Rute[][] rutenett;
    protected Rute naboNord;
    protected Rute naboSyd;
    protected Rute naboVest;
    protected Rute naboOest;
    protected ArrayList<Rute> naboliste = new ArrayList<Rute>();
    protected boolean besokt = false;
     
    public Rute(int radNr, int kolNr, Labyrint labyrint) {
        this.radNr = radNr;
        this.kolNr = kolNr;
        this.labyrint = labyrint;
    }
    
    public boolean erBesokt() {
        return besokt;
    }

    // Brukes for at finn-metoden bare kalles paa en rute én gang.
    public void besok() {
        besokt = true;
    }

    public void nullstillBesok() {
        besokt = false;
    }

    public int hentRad() {
        return radNr;
    }

    public int hentKol() {
        return kolNr;
    }

    // Finner ruten sine naboer. Legges til i rutens naboliste.
    public void settNaboer() {
        rutenett = labyrint.hentRutenett();
        if(radNr-1 >= 0) {
            naboNord = rutenett[radNr-1][kolNr];
        }
        if(radNr+1 < labyrint.hentAntRad()) {
            naboSyd = rutenett[radNr+1][kolNr];
        }
        if(kolNr-1 >= 0) {
            naboVest = rutenett[radNr][kolNr-1];
        }
        if(kolNr+1 < labyrint.hentAntKol()) {
            naboOest = rutenett[radNr][kolNr+1];
        }
        naboliste.add(naboNord);
        naboliste.add(naboSyd);
        naboliste.add(naboVest);
        naboliste.add(naboOest);
    }

    // Hvis rute er null, skal denne metoden kalle paa alle rutens naboer (Kalles ikke paa siden null som parameter gir feilmelding!).
    public void finn(Rute fra) {
        if(fra == null) {
            for(int rad = 0; rad < labyrint.hentAntRad(); rad++) {
                for(int kol = 0; kol < labyrint.hentAntKol(); kol++) {
                    finn(labyrint.hentRutenett()[rad][kol]);
                }
            }
        }
        else {
            return;
        }
    }
}