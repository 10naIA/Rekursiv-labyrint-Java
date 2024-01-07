/* Klasse som oppretter labyrint. Leser enn tekstfil med labyrint, og oppretter alle rutene og setter naboene deres. 
Har metode som starter soek etter utveier fra brukers koordinater.*/

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

class Labyrint {
    protected int rader;
    protected int kolonner;
    protected Rute [][] rutenett;
    protected String filnavn;

    // Variabel er ment for aa sjekke om finn(null).
    protected boolean foersteRute = true;

    // Konstruktoren leser inn tekstfilen, og skriver ut labyrinten.
    public Labyrint(String filnavn) {
        this.filnavn = filnavn;
        lesFil();
        System.out.println(this);
    }
    
    // Leser inn tekstfilen med labyrinten.
    public void lesFil() {
        try {
            Scanner fil = new Scanner(new File(filnavn));
            int linjeNr = 0;

            // Hvis linje 0 = koordinater
            while(fil.hasNextLine()) {
                String linje = fil.nextLine();

                if(linjeNr == 0) {
                    String [] oppdelt = linje.split(" ");
                    int [] koordinater = new int[2];
                    koordinater[0] = Integer.parseInt(oppdelt[0]);
                    koordinater[1] = Integer.parseInt(oppdelt[1]);
                    // Forste linje har tall for antall rader og kolonner. Oppretter rutenettet.
                    rader = koordinater[0];
                    kolonner = koordinater[1];
                    rutenett = new Rute [rader][kolonner];
                }
                else {
                    /* Itererer gjennom tegnene paa linjen, og oppretter korrekte objekter. Stier som er paa forste eller siste rad i labyrinten, 
                    eller er forste eller siste element i raden blir til aapninger.*/
                    String [] tegn = linje.split("");
                    for(int i = 0; i < tegn.length; i++) {
                        if(tegn[i].contains(".")) {
                            if(linjeNr == 1 || linjeNr == rader || i == 0 || i == tegn.length-1) {
                                // Rad-koordinat maa ha indeks 1 mindre enn totalt antall rader (linjeNr-1).
                                Aapning aapning = new Aapning(linjeNr-1, i, this);
                                leggTilRute(aapning, linjeNr-1, i);
                            }
                            else {
                                HvitRute hvit = new HvitRute(linjeNr-1, i, this);
                                leggTilRute(hvit, linjeNr-1, i);
                            }
                        }
                        else if(tegn[i].contains("#")) {
                            SortRute sort = new SortRute(linjeNr-1, i, this);
                            leggTilRute(sort, linjeNr-1, i);
                        }
                    }
                }
                linjeNr ++;
            }
            // Setter ruters naboer.
            for(int rad = 0; rad < rader; rad++) {
                for(int kol = 0; kol < kolonner; kol++) {
                    rutenett[rad][kol].settNaboer();
                }
            }
        } 
        catch(FileNotFoundException e) {
            System.out.println("Filen finnes ikke");
        }
    }

    public Rute [][] hentRutenett() {
        return rutenett;
    }

    // Legger til rute i rutenettet til labyrinten.
    public void leggTilRute(Rute rute, int kolonnen, int raden) {
        rutenett[kolonnen][raden] = rute;
    }

    public int hentAntRad() {
        return rader;
    }

    public int hentAntKol() {
        return kolonner;
    }

    // Itererer gjennom rutenettet, og returnerer hele labyrinten i tegnet form.
    public String toString() {
        String tegnetNett = "\n";
        tegnetNett += rader + "   " + kolonner + "\n\n";
        for(int rad = 0; rad < rader; rad++) {
            for(int kol = 0; kol < kolonner; kol++) {
                tegnetNett += rutenett[rad][kol] + " ";
            }
            tegnetNett += "\n";
        }
        return tegnetNett;
    }

    // Kaller paa finn-metoden paa naborutene til startruten. Bruker instanceof for aa kunne printe feilmelding hvis bruker velger sort rute! 
    public void finnUtveiFra(int rad, int kol) {
        if(rutenett[rad][kol] instanceof SortRute) {
            System.out.println("Kan ikke starte i sort rute!");
        }
        // Kommentar: Gir feilmelding naar null er parameter!
        // else if(foersteRute) {
        //     rutenett[rad][kol].finn(null);
        //     foersteRute = false;
        // }
        else {
            System.out.println("Aapninger: ");
            rutenett[rad][kol].finn(rutenett[rad][kol]);
            // Setter alle rutenes besoksstatus til false, saa samme koordinater kan sjekkes om igjen i hovedprogrammet.
            for(int i = 0; i < rader; i++) {
                for(int j = 0; j < kolonner; j++) {
                    rutenett[i][j].nullstillBesok();
                }
            }
        }
    }
}