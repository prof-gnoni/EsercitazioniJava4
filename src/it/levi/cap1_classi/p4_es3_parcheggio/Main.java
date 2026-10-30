package it.levi.cap1_classi.p4_es3_parcheggio;

/**
 * Main dell'esercizio 3: dimostra che Java sceglie la versione giusta
 * di {@code entra} ed {@code esci} in base ai parametri passati.
 */
public class Main {

    /**
     * Punto di partenza del programma.
     *
     * @param args non usati
     */
    public static void main(String[] args) {
        Parcheggio p1 = new Parcheggio();                              // costruttore vuoto
        Parcheggio p2 = new Parcheggio("Parcheggio Stazione", 5, 2.00); // costruttore con parametri
        p1.visualizza();
        p2.visualizza();

        // entra() SENZA parametri: entra una sola auto
        p2.entra();

        // entra(int) CON un parametro: entra un gruppo, solo se ci stanno tutte
        if (p2.entra(3)) {
            System.out.println("Gruppo di 3 auto entrato.");
        }
        if (!p2.entra(4)) {   // restano 1 posto: il gruppo di 4 viene rifiutato
            System.out.println("Gruppo di 4 auto rifiutato: posti liberi " + p2.postiLiberi());
        }

        /* Riempio il parcheggio: il ciclo continua finché entra() restituisce true.
           La chiamata a entra() dentro la condizione FA entrare l'auto. */
        while (p2.entra()) {
            System.out.println("Entrata un'auto, liberi: " + p2.postiLiberi());
        }
        System.out.println("Pieno? " + p2.isPieno());
        p2.visualizza();

        // esci(int) ed esci(int, int): due versioni dello stesso metodo
        p2.esci(2);          // paga 2 ore
        p2.esci(2, 10);      // paga 3 ore (l'ora iniziata si paga intera)
        p2.esci(0, 45);      // paga 1 ora
        p2.visualizza();     // incasso: (2 + 3 + 1) ore x 2 euro = 12 euro

        // Uscita da un parcheggio vuoto: esci restituisce false
        if (!p1.esci(1)) {
            System.out.println(p1.getNome() + " è vuoto: nessuna uscita.");
        }
    }
}
