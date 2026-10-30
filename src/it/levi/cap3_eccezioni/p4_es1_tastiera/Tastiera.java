package it.levi.cap3_eccezioni.p4_es1_tastiera;

import java.util.Scanner;

/**
 * Esercizio eccezioni 1 (base): una classe di utilità per leggere dati
 * dalla tastiera senza mai far crashare il programma.
 *
 * <p>ECCEZIONE VOLUTA ALLA REGOLA: a differenza delle classi "modello"
 * (Libro, ContoCorrente...), il compito di questa classe è proprio dialogare
 * con l'utente. Per questo stampa le domande e gli avvisi.</p>
 *
 * <p>Tecnica usata: si legge sempre una RIGA intera con nextLine() e la si converte
 * con Integer.parseInt o Double.parseDouble. Se il testo non è un numero, arriva
 * una NumberFormatException: la si cattura e si richiede il dato. Così non serve
 * "ripulire" lo Scanner come con nextInt().</p>
 */
public class Tastiera {

    /** Lo Scanner è un attributo: viene creato una volta e usato da tutti i metodi. */
    private Scanner in;

    /** Crea la tastiera collegata a System.in (la console). */
    public Tastiera() {
        in = new Scanner(System.in);
    }

    /**
     * Legge un numero intero, ripetendo la domanda finché la risposta non è valida.
     *
     * @param domanda il testo da mostrare all'utente
     * @return il numero letto
     */
    public int leggiIntero(String domanda) {
        int numero = 0;
        boolean valido = false;
        while (!valido) {
            System.out.print(domanda);
            String riga = in.nextLine();
            try {
                numero = Integer.parseInt(riga.trim());   // trim: " 16 " va bene lo stesso
                valido = true;                            // arrivo qui solo se la conversione è riuscita
            } catch (NumberFormatException e) {
                System.out.println("\"" + riga + "\" non è un numero intero, riprova.");
            }
        }
        return numero;
    }

    /**
     * Legge un intero compreso in un intervallo. Riusa leggiIntero():
     * il controllo "è un numero?" è già scritto lì, qui controllo solo l'intervallo.
     *
     * @param domanda il testo da mostrare
     * @param min     il valore minimo accettato
     * @param max     il valore massimo accettato
     * @return il numero letto, sicuramente tra min e max
     */
    public int leggiInteroTra(String domanda, int min, int max) {
        int numero = leggiIntero(domanda);
        while (numero < min || numero > max) {
            System.out.println("Il valore deve essere tra " + min + " e " + max + ".");
            numero = leggiIntero(domanda);
        }
        return numero;
    }

    /**
     * Legge un numero decimale. Accetta sia il punto sia la virgola
     * (1.75 e 1,75): la virgola viene trasformata in punto prima di convertire.
     *
     * @param domanda il testo da mostrare
     * @return il numero letto
     */
    public double leggiDecimale(String domanda) {
        double numero = 0;
        boolean valido = false;
        while (!valido) {
            System.out.print(domanda);
            String riga = in.nextLine();
            try {
                numero = Double.parseDouble(riga.trim().replace(',', '.'));   // replace tra char: ',' -> '.'
                valido = true;
            } catch (NumberFormatException e) {
                System.out.println("\"" + riga + "\" non è un numero, riprova.");
            }
        }
        return numero;
    }

    /**
     * Legge un testo non vuoto. Qui non servono eccezioni: un controllo con if basta.
     * (Le eccezioni sono per gli imprevisti, non per i controlli normali.)
     *
     * @param domanda il testo da mostrare
     * @return il testo letto, senza spazi ai bordi
     */
    public String leggiTesto(String domanda) {
        System.out.print(domanda);
        String riga = in.nextLine().trim();
        while (riga.isEmpty()) {
            System.out.print("Non puoi lasciarlo vuoto. " + domanda);
            riga = in.nextLine().trim();
        }
        return riga;
    }

    /** Chiude lo Scanner: si chiama alla fine del programma. */
    public void chiudi() {
        in.close();
    }
}
