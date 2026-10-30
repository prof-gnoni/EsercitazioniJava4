package it.levi.cap3_eccezioni.p1_teoria;

/**
 * Esempio 4: la propagazione. Un'eccezione non catturata "risale"
 * al metodo chiamante, poi a quello prima, fino a chi la cattura
 * (o fino al main: se nemmeno lui la cattura, il programma termina).
 *
 * <p>Qui: main chiama primo(), primo() chiama secondo(), secondo() lancia.
 * primo() non cattura, quindi l'eccezione arriva al main.</p>
 *
 * <p>Nota: i metodi primo() e secondo() sono static perché li chiamiamo
 * direttamente dal main, senza creare un oggetto. Qui servono solo per l'esempio.</p>
 */
public class Esempio4Propagazione {

    /**
     * Punto di partenza: l'unico punto in cui l'eccezione viene catturata.
     *
     * @param args non usati
     */
    public static void main(String[] args) {
        System.out.println("main: inizio, chiamo primo()");
        try {
            primo();
        } catch (IllegalStateException e) {
            System.out.println("main: ho catturato l'eccezione -> " + e.getMessage());
        }
        System.out.println("main: fine");
    }

    /** Chiama secondo() ma NON cattura l'eccezione: la lascia passare verso il main. */
    static void primo() {
        System.out.println("  primo: chiamo secondo()");
        secondo();
        // L'eccezione attraversa primo() senza fermarsi: questa riga viene saltata
        System.out.println("  primo: questa riga NON viene eseguita");
    }

    /** Lancia un'eccezione con throw: il metodo si interrompe subito. */
    static void secondo() {
        System.out.println("    secondo: lancio un'eccezione");
        throw new IllegalStateException("qualcosa è andato storto in secondo()");
    }
}
