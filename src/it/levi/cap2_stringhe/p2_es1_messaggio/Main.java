package it.levi.cap2_stringhe.p2_es1_messaggio;

import java.util.Scanner;

/**
 * Main dell'esercizio stringhe 1: usa tutti i metodi di {@link Messaggio}.
 */
public class Main {

    /**
     * Punto di partenza del programma.
     *
     * @param args non usati
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        // Messaggio scritto nel codice: è un palindromo famoso
        Messaggio m1 = new Messaggio();
        m1.setMittente("   Luca   ");          // gli spazi ai bordi vengono tolti dal setter
        m1.setTesto("I topi non avevano nipoti");
        m1.visualizza();
        System.out.println("Invertito: " + m1.invertito());
        System.out.println("Palindromo? " + m1.isPalindromo());   // true

        /* Messaggio letto da tastiera.
           Uso nextLine() perché il testo può contenere spazi (next() leggerebbe una sola parola). */
        System.out.print("Chi scrive? ");
        String chi = in.nextLine();
        System.out.print("Scrivi un messaggio: ");
        String testo = in.nextLine();
        Messaggio m2 = new Messaggio(chi, testo);
        m2.visualizza();

        System.out.print("Parola da cercare: ");
        String cerca = in.nextLine();
        if (m2.contiene(cerca)) {
            System.out.println("La parola c'è.");
        } else {
            System.out.println("La parola non c'è.");
        }

        // censura() restituisce una nuova stringa: il testo originale non cambia
        System.out.print("Parola da censurare: ");
        String vietata = in.nextLine();
        System.out.println("Censurato: " + m2.censura(vietata));
        System.out.println("Originale (immutato): " + m2.getTesto());

        // Le stringhe si confrontano con equals, mai con ==
        if (m1.getMittente().equals(m2.getMittente())) {
            System.out.println("Stesso mittente.");
        } else {
            System.out.println("Mittenti diversi.");
        }
        in.close();
    }
}
