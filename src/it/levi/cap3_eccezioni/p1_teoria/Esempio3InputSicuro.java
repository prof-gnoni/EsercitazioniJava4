package it.levi.cap3_eccezioni.p1_teoria;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Esempio 3: l'input sicuro. Si chiede un numero finché l'utente
 * non lo scrive correttamente, senza mai far crashare il programma.
 */
public class Esempio3InputSicuro {

    /**
     * Punto di partenza del programma.
     *
     * @param args non usati
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int eta = 0;
        boolean valido = false;     // diventa true solo quando la lettura riesce
        while (!valido) {
            System.out.print("Quanti anni hai? ");
            try {
                eta = in.nextInt();
                valido = true;      // arrivo qui SOLO se nextInt() non ha lanciato eccezioni
            } catch (InputMismatchException e) {
                System.out.println("Non è un numero intero, riprova.");
                /* IMPORTANTE: quando nextInt() fallisce, il testo sbagliato (es. "ciao")
                   RESTA nello Scanner. Senza nextLine(), al giro successivo nextInt()
                   lo ritroverebbe e fallirebbe di nuovo: un ciclo infinito! */
                in.nextLine();
            }
        }
        System.out.println("Fra 10 anni avrai " + (eta + 10) + " anni.");   // parentesi: prima la somma
        in.close();
    }
}
