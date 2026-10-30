package it.levi.cap3_eccezioni.p5_es2_termostato;

// import: uso una classe che sta in un ALTRO package (quella dell'esercizio 1, p4_es1_tastiera)
import it.levi.cap3_eccezioni.p4_es1_tastiera.Tastiera;

/**
 * Main dell'esercizio eccezioni 2: menu del termostato con gestione degli errori.
 *
 * <p>Tutto lo switch è dentro UN try con due catch: qualunque comando sbagli,
 * il programma avvisa e ripropone il menu.</p>
 */
public class Main {

    /**
     * Punto di partenza del programma.
     *
     * @param args non usati
     */
    public static void main(String[] args) {
        Tastiera tastiera = new Tastiera();   // la lettura dei numeri è già "a prova di errore"

        Termostato t = new Termostato("Camera", 16.5, 21.0);
        t.visualizza();

        int scelta;
        do {
            System.out.println("1 accendi | 2 spegni | 3 alza | 4 abbassa | 5 simula | 6 imposta | 7 visualizza | 0 esci");
            scelta = tastiera.leggiInteroTra("Scelta: ", 0, 7);
            try {
                switch (scelta) {
                    case 1:
                        t.accendi();
                        break;
                    case 2:
                        t.spegni();
                        break;
                    case 3:
                        t.alza(tastiera.leggiDecimale("Di quanti gradi? "));
                        break;
                    case 4:
                        t.abbassa(tastiera.leggiDecimale("Di quanti gradi? "));
                        break;
                    case 5:
                        t.simula(tastiera.leggiIntero("Quanti minuti? "));
                        break;
                    case 6:
                        t.setTemperaturaDesiderata(tastiera.leggiDecimale("Nuova temperatura: "));
                        break;
                    case 7:
                        t.visualizza();
                        break;
                    default:   // rimane solo 0: leggiInteroTra garantisce un valore tra 0 e 7
                        System.out.println("Fine.");
                }
            } catch (IllegalArgumentException e) {
                // valore sbagliato: temperatura fuori intervallo, gradi o minuti negativi
                System.out.println("Valore non valido: " + e.getMessage());
            } catch (IllegalStateException e) {
                // operazione impossibile in questo momento: simulare a termostato spento
                System.out.println("Operazione non possibile: " + e.getMessage());
            }
        } while (scelta != 0);

        t.visualizza();
        tastiera.chiudi();
    }
}
