/* Hovedprogram som lar brukeren sende inn tekstfil fra terminalen, med en labyrint som leses inn, 
og deretter finner utveier i denne ut ifra brukers koordinater.*/

import java.util.Scanner;

class Oblig7 {
    public static void main(String[] args) {
        Labyrint labyrint = new Labyrint(args[0]);
        boolean avsluttet = false;

        // Loopen lar brukeren skrive koordinater frem til input er -1.
        while(!avsluttet) {
            System.out.println("Skriv inn nye koordinater <rad> <kolonne> ('-1' for å avslutte)");
            Scanner sc = new Scanner(System.in);
            String koordinater = sc.nextLine();
            try {
                // Deler opp input i int-koordinater, og prover aa finne utveier hvis baade rad- og kolonne-koordinater.
                String [] oppdelt = koordinater.split(" ");
                int rad = Integer.parseInt(oppdelt[0]);
                if(oppdelt.length > 1) {
                    int kol = Integer.parseInt(oppdelt[1]);
                    labyrint.finnUtveiFra(rad, kol);
                }
                else if(koordinater.contains("-1")) {
                    sc.close();
                    avsluttet = true;
                }
            }
            catch(Exception e) {
                System.out.println("Ikke gyldig input!");
            }
        }
    }
}