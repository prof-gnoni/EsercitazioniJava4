package it.levi.cap3_eccezioni.p1_teoria;

/**
 * Esempio 1: le prime eccezioni, quelle che fanno "crashare" il programma.
 *
 * <p>COME USARLO: esegui il programma così com'è e leggi il messaggio rosso.
 * Poi rimetti il commento alla divisione, togli il commento a UNA delle righe
 * successive e riesegui. Una riga per volta.</p>
 *
 * <p>Come si legge il messaggio di errore (stack trace):</p>
 * <pre>
 * Exception in thread "main" java.lang.ArithmeticException: / by zero
 *     at it.levi.cap3_eccezioni.p1_teoria.Esempio1Crash.main(Esempio1Crash.java:34)
 * </pre>
 * <ol>
 *   <li>il TIPO di eccezione: ArithmeticException;</li>
 *   <li>il MESSAGGIO: / by zero;</li>
 *   <li>DOVE: file e riga (in IntelliJ è un link blu cliccabile).</li>
 * </ol>
 */
public class Esempio1Crash {

    /**
     * Punto di partenza del programma.
     *
     * @param args non usati
     */
    public static void main(String[] args) {
        System.out.println("Inizio del programma");

        // ArithmeticException: divisione INTERA per zero (10.0 / 0 invece dà Infinity)
        int a = 10;
        int b = 0;
        System.out.println(a / b);

        // StringIndexOutOfBoundsException: "ciao" ha solo le posizioni da 0 a 3
        // String parola = "ciao";
        // System.out.println(parola.charAt(10));

        // NullPointerException: la variabile non punta a nessun oggetto
        // String vuota = null;
        // System.out.println(vuota.length());

        // NumberFormatException: il testo non si può convertire in numero
        // int numero = Integer.parseInt("dodici");

        // InputMismatchException: quando il programma aspetta, scrivi "ciao"
        // java.util.Scanner in = new java.util.Scanner(System.in);
        // int eta = in.nextInt();

        /* Questa riga NON viene mai stampata: un'eccezione non gestita
           interrompe il programma e tutto ciò che viene dopo è saltato. */
        System.out.println("Fine del programma");
    }
}
