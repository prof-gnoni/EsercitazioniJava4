package it.levi.cap2_stringhe.p3_es2_password;

import java.util.Scanner;

/**
 * Main dell'esercizio stringhe 2: password deboli e forti, creazione da tastiera
 * e simulazione di un accesso con al massimo 3 tentativi.
 */
public class Main {

    /**
     * Punto di partenza del programma.
     *
     * @param args non usati
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        Password p1 = new Password();
        p1.visualizza();

        // Password troppo debole: il setter la ignora, resta quella vecchia
        p1.setValore("ciao");
        System.out.println("Accetta ancora la vecchia? " + p1.verifica("Cambiami1"));   // true

        p1.setValore("Scuola-2026!");   // 12 caratteri, maiuscola, cifra, speciale: FORTE
        p1.visualizza();

        // Password scelta dall'utente: se non rispetta le regole resta quella provvisoria
        System.out.print("Nome utente: ");
        String utente = in.nextLine();
        System.out.print("Password: ");
        String pwd = in.nextLine();
        Password p2 = new Password(utente, pwd);
        if (p2.verifica(pwd)) {
            System.out.println("Password accettata.");
        } else {
            System.out.println("Password non conforme: resta quella provvisoria.");
        }
        p2.visualizza();

        /* Accesso con al massimo 3 tentativi.
           Il ciclo si ferma quando i tentativi finiscono OPPURE quando l'utente entra. */
        int tentativi = 0;
        boolean entrato = false;
        while (tentativi < 3 && !entrato) {
            System.out.print("Accesso - password: ");
            entrato = p2.verifica(in.nextLine());
            tentativi++;
        }
        if (entrato) {
            System.out.println("Benvenuto, " + p2.getUtente() + "!");
        } else {
            System.out.println("Account bloccato.");
        }
        in.close();
    }
}
