package it.levi.cap3_eccezioni.p4_es1_tastiera;

/**
 * Main dell'esercizio eccezioni 1.
 * PROVALO scrivendo apposta lettere al posto dei numeri, numeri fuori
 * intervallo e invio a vuoto: il programma non deve mai interrompersi.
 */
public class Main {

    /**
     * Punto di partenza del programma.
     *
     * @param args non usati
     */
    public static void main(String[] args) {
        Tastiera t = new Tastiera();

        // Tutta la gestione degli errori è dentro Tastiera: qui il codice resta pulito
        String nome = t.leggiTesto("Come ti chiami? ");
        int eta = t.leggiInteroTra("Quanti anni hai? ", 0, 120);
        double altezza = t.leggiDecimale("Quanto sei alto (in metri)? ");
        int voto = t.leggiInteroTra("Voto dell'ultima verifica (1-10)? ", 1, 10);

        System.out.println();
        System.out.println("Ciao " + nome + "!");
        System.out.println("Hai " + eta + " anni e sei alto " + altezza + " m.");
        if (voto >= 6) {
            System.out.println("Verifica sufficiente, bravo!");
        } else {
            System.out.println("Verifica insufficiente: si recupera!");
        }
        t.chiudi();
    }
}
