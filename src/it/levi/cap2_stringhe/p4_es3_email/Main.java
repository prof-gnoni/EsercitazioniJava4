package it.levi.cap2_stringhe.p4_es3_email;

import java.util.Scanner;

/**
 * Main dell'esercizio stringhe 3: nomi composti, apostrofi, accenti
 * e le due versioni (overload) di {@code generaEmail}.
 */
public class Main {

    /**
     * Punto di partenza del programma.
     *
     * @param args non usati
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        Studente s1 = new Studente();
        s1.visualizza();

        // Nome composto, apostrofo, classe in minuscolo: la classe sistema tutto
        Studente s2 = new Studente("maria chiara", "D'Agostino", "4b");
        s2.visualizza();                                   // mariachiara.dagostino@scuola.it
        // Seconda versione di generaEmail: con il dominio come parametro
        System.out.println("  email altro dominio: " + s2.generaEmail("esempio.edu.it"));

        // Lettera accentata e cognome con uno spazio
        Studente s3 = new Studente("Nicolò", "De Luca", "4A");
        s3.visualizza();                                   // nicolo.deluca@scuola.it

        // Dato non valido: il setter lo ignora, lo verifico con il getter
        s3.setClasse("quarta");
        System.out.println("Classe di s3 dopo il tentativo: " + s3.getClasse());   // ancora 4A

        // Studente letto da tastiera
        System.out.print("Nome: ");
        String nome = in.nextLine();
        System.out.print("Cognome: ");
        String cognome = in.nextLine();
        System.out.print("Classe (es. 4A): ");
        String classe = in.nextLine();
        Studente s4 = new Studente(nome, cognome, classe);
        s4.visualizza();

        // equalsIgnoreCase: "verdi" e "Verdi" sono considerati uguali
        if (s4.getCognome().equalsIgnoreCase(s2.getCognome())) {
            System.out.println("Stesso cognome di " + s2.nomeCompleto());
        }
        in.close();
    }
}
