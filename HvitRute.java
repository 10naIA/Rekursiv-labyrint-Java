// Klasse som oppretter stier i labyrinten. Har metode for aa finne alle aapninger i labyrinten.

class HvitRute extends Rute {

    public HvitRute(int radNr, int kolNr, Labyrint labyrint) {
        super(radNr, kolNr, labyrint);
    }

    public String toString() {
        return ".";
    }

    /* Finner aapninger ved aa rekursivt kalle paa finn-metoden paa hvite rutenaboer. 
    Bruker nabo.finn(nabo) fordi finn(nabo) bare sjekker naboene én gang!) */
    @Override
    public void finn(Rute fra) {
        if(!erBesokt()) {
            // Setter besoks-status til true saa den ikke sjekkes igjen.
            besok();
            for(Rute nabo : naboliste) {
                if(nabo != null && !nabo.erBesokt()) {
                    nabo.finn(nabo);
                }
            }
        }
        else {
            return;
        }
    }
}