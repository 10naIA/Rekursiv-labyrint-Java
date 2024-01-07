// Klasse som oppretter 'blokkerings'-objekter i labyrinten. Finn-metoden skal ikke finne naboer.

class SortRute extends Rute {

    public SortRute(int radNr, int kolNr, Labyrint labyrint) {
        super(radNr, kolNr, labyrint);
    }

    public String toString() {
        return "#";
    }

    @Override
    public void finn(Rute fra) {
        return;
    }
}