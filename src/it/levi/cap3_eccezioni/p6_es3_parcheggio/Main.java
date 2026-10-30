package it.levi.cap3_eccezioni.p6_es3_parcheggio;

/**
 * Main dell'esercizio eccezioni 3: un'eccezione checked creata da noi
 * e due eccezioni unchecked standard.
 */
public class Main {

    /**
     * Punto di partenza del programma.
     *
     * @param args non usati
     */
    public static void main(String[] args) {
        Parcheggio p = new Parcheggio("Parcheggio Stazione", 5, 2.00);

        /* 1) Riempio il parcheggio. Il ciclo proverebbe a far entrare 10 auto,
              ma alla sesta entra() lancia l'eccezione: il for si interrompe
              e si salta al catch. */
        try {
            for (int i = 1; i <= 10; i++) {
                p.entra();
                System.out.println("Entrata auto n. " + i);
            }
        } catch (ParcheggioPienoException e) {
            System.out.println("Stop: " + e.getMessage());
        }
        p.visualizza();

        // 2) Escono due auto (2 posti liberi), poi un gruppo di 3 viene rifiutato
        p.esci(2);
        p.esci(1, 30);
        try {
            p.entra(3);
        } catch (ParcheggioPienoException e) {
            System.out.println("Gruppo rifiutato. " + e.getMessage());
        }

        // 3) Parametro sbagliato: IllegalArgumentException
        try {
            p.esci(2, 75);
        } catch (IllegalArgumentException e) {
            System.out.println("Errore: " + e.getMessage());
        }

        // 4) Stato sbagliato: IllegalStateException (nessuna auto da far uscire)
        Parcheggio vuoto = new Parcheggio();
        try {
            vuoto.esci(1);
        } catch (IllegalStateException e) {
            System.out.println("Errore: " + e.getMessage());
        }

        // 5) Costruttore con dati sbagliati: l'oggetto non viene creato
        try {
            Parcheggio sbagliato = new Parcheggio("Test", 0, 1.0);
            sbagliato.visualizza();   // mai eseguita
        } catch (IllegalArgumentException e) {
            System.out.println("Parcheggio non creato: " + e.getMessage());
        }

        p.visualizza();
    }
}
