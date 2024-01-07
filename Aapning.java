// Klasse som oppretter aapninger i labyrinten. Finn-metoden printer ut koordinatene.

class Aapning extends HvitRute {

    public Aapning(int radNr, int kolNr, Labyrint labyrint) {
        super(radNr, kolNr, labyrint);
    }

    public String toString() {
        return "V";
    }

    /* Hvis bruker skriver inn aapning-koordinater, printes koordinatene, og saa skal finn-metoden fortsette aa lete etter andre aapninger. 
    Derfor brukes koden fra hvit rute. */
    @Override
    public void finn(Rute fra) {
        System.out.println("(" + radNr + "," + kolNr + ")");
        super.finn(fra);
    }
}