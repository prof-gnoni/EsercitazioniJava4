package it.levi.cap3_eccezioni.p7_es4_voti;

import java.util.Scanner;

/**
 * Main dell'esercizio eccezioni 4: voti da un testo e voti aggiunti da tastiera.
 */
public class Main {

    /**
     * Punto di partenza del programma.
     *
     * @param args non usati
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        // Testo con 2 voti non validi: "sei" (non è un numero) e "11" (fuori scala)
        PagellaMateria p1 = new PagellaMateria("Informatica", "7; 8,5; sei; 11; 6.5");
        p1.visualizza();                           // validi 3, scartati 2, media 7.33

        /* Aggiunta di voti da tastiera: si esce premendo invio a vuoto.
           ORDINE DEI CATCH: prima il più SPECIFICO (NumberFormatException),
           poi il più GENERICO (IllegalArgumentException). Al contrario il codice
           non compilerebbe, perché il primo catch "ruberebbe" tutte le eccezioni. */
        PagellaMateria p2 = new PagellaMateria();
        boolean continua = true;
        while (continua) {
            System.out.print("Voto da aggiungere (invio per finire): ");
            String riga = in.nextLine();
            if (riga.trim().isEmpty()) {
                continua = false;
            } else {
                try {
                    p2.aggiungiVoto(riga);
                    System.out.println("Voto aggiunto.");
                } catch (NumberFormatException e) {
                    System.out.println("\"" + riga + "\" non è un numero.");
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());   // es. "Voto fuori scala: 12.0"
                }
            }
        }
        p2.visualizza();

        // Costruttore con materia vuota: l'oggetto non viene creato
        try {
            PagellaMateria vuota = new PagellaMateria("", "8");
            vuota.visualizza();
        } catch (IllegalArgumentException e) {
            System.out.println("Pagella non creata: " + e.getMessage());
        }
        in.close();
    }
}
