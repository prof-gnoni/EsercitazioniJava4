package it.levi.cap3_eccezioni.p2_libro;

import java.util.Scanner;

/**
 * Main del Libro con eccezioni: la classe LANCIA, il main CATTURA e avvisa l'utente.
 */
public class Main {

    /**
     * Punto di partenza del programma.
     *
     * @param args non usati
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        Libro l1 = new Libro("Il barone rampante", "Italo Calvino", 280, 11.90);
        l1.visualizza();

        // 1) Setter con un valore sbagliato: catturo l'eccezione e avviso
        try {
            l1.setPagine(-5);
            System.out.println("Questa riga non viene eseguita");   // saltata: si va al catch
        } catch (IllegalArgumentException e) {
            System.out.println("Errore: " + e.getMessage());        // Errore: Pagine non valide: -5
        }
        System.out.println("Pagine: " + l1.getPagine());   // ancora 280: l'assegnazione non è avvenuta

        // 2) Anche il costruttore può lanciare: l'oggetto NON viene creato
        try {
            Libro sbagliato = new Libro("", "Nessuno", 100, 5);
            sbagliato.visualizza();                          // mai eseguita
        } catch (IllegalArgumentException e) {
            System.out.println("Libro non creato: " + e.getMessage());
        }

        /* 3) Input dell'utente: ripeto finché il valore è accettato.
              Se leggi() lancia l'eccezione, "fatto = true" viene saltato e il ciclo riparte. */
        boolean fatto = false;
        while (!fatto) {
            System.out.print("Quante pagine hai letto? ");
            int lette = in.nextInt();
            try {
                l1.leggi(lette);
                fatto = true;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage() + ". Riprova.");
            }
        }
        System.out.printf("Letto il %.1f%%%n", l1.percentualeLettura());

        /* 4) Senza try/catch un'eccezione interrompe il programma.
              Togli il commento alla riga seguente ed esegui per vedere lo stack trace. */
        // l1.applicaSconto(150);

        l1.visualizza();
        in.close();
    }
}
