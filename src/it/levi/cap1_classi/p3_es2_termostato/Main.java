package it.levi.cap1_classi.p3_es2_termostato;

import java.util.Scanner;

/**
 * Main dell'esercizio 2: un menu ripetuto con switch per comandare un termostato.
 */
public class Main {

    /**
     * Punto di partenza del programma.
     *
     * @param args non usati
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        Termostato t1 = new Termostato();                       // costruttore vuoto
        Termostato t2 = new Termostato("Camera", 16.5, 21.0);   // costruttore con parametri
        t1.visualizza();
        t2.visualizza();

        /* Menu ripetuto: do-while perché il menu va mostrato almeno una volta.
           Si esce quando l'utente sceglie 0. */
        int scelta;
        do {
            System.out.println("1 accendi | 2 spegni | 3 alza | 4 abbassa | 5 simula | 6 visualizza | 0 esci");
            System.out.print("Scelta: ");
            scelta = in.nextInt();
            switch (scelta) {
                case 1:
                    t2.accendi();
                    break;                 // senza break si eseguirebbe anche il case successivo
                case 2:
                    t2.spegni();
                    break;
                case 3:
                    System.out.print("Di quanti gradi? ");
                    t2.alza(in.nextDouble());   // il valore letto va direttamente come parametro
                    break;
                case 4:
                    System.out.print("Di quanti gradi? ");
                    t2.abbassa(in.nextDouble());
                    break;
                case 5:
                    System.out.print("Quanti minuti? ");
                    if (!t2.simula(in.nextInt())) {   // simula restituisce false se non può simulare
                        System.out.println("Termostato spento (o minuti non validi): nessun riscaldamento.");
                    }
                    break;
                case 6:
                    t2.visualizza();
                    break;
                case 0:
                    System.out.println("Fine.");
                    break;
                default:                   // qualsiasi altro numero
                    System.out.println("Scelta non valida.");
            }
        } while (scelta != 0);

        // Stato finale: t1 non è stato toccato, t2 sì
        t1.visualizza();
        t2.visualizza();
        in.close();
    }
}
