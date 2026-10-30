package it.levi.cap3_eccezioni.p1_teoria;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Esempio 2: try, catch e finally. Il programma non si interrompe più.
 *
 * <p>Provalo tre volte:</p>
 * <ul>
 *   <li>10 e 2: nessuna eccezione, nessun catch eseguito;</li>
 *   <li>10 e 0: ArithmeticException, si salta al primo catch;</li>
 *   <li>10 e ciao: InputMismatchException, la divisione non viene mai fatta.</li>
 * </ul>
 * In tutti e tre i casi il programma arriva in fondo.
 */
public class Esempio2TryCatch {

    /**
     * Punto di partenza del programma.
     *
     * @param args non usati
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        /* try = "PROVA a eseguire questo codice".
           REGOLA: alla prima eccezione dentro il try, Java salta subito al catch
           giusto; le righe rimanenti del try NON vengono eseguite. */
        try {
            System.out.print("Dividendo: ");
            int a = in.nextInt();                      // può lanciare InputMismatchException
            System.out.print("Divisore: ");
            int b = in.nextInt();                      // può lanciare InputMismatchException
            int risultato = a / b;                     // può lanciare ArithmeticException
            System.out.println("Risultato: " + risultato);   // eseguita solo se tutto è andato bene
        } catch (ArithmeticException e) {
            // e è l'OGGETTO eccezione: contiene il tipo e il messaggio del problema
            System.out.println("Non si può dividere per zero!");
            System.out.println("Messaggio tecnico: " + e.getMessage());
        } catch (InputMismatchException e) {
            // un catch per ogni tipo di problema: ognuno ha la sua reazione
            System.out.println("Dovevi scrivere un numero intero.");
        } finally {
            // finally (facoltativo) viene eseguito SEMPRE: con o senza eccezione
            System.out.println("Fine del calcolo (finally viene eseguito SEMPRE).");
        }

        // Grazie al try/catch questa riga viene eseguita in tutti i casi
        System.out.println("Il programma continua normalmente...");
        in.close();
    }
}
