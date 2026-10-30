package it.levi.cap1_classi.p1_lezione_libro;

import java.util.Scanner;

/**
 * Programma principale della lezione: usa la classe {@link Libro} in modo completo.
 *
 * <p>Regola da seguire: ogni metodo della classe deve essere chiamato almeno una volta
 * e ogni modifica va verificata con {@code visualizza()} o con un getter.</p>
 */
public class Main {

    /**
     * Punto di partenza del programma.
     *
     * @param args argomenti da riga di comando (non usati)
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        // 1) Oggetto creato con il costruttore VUOTO: ha i valori di default
        Libro l1 = new Libro();
        l1.visualizza();

        // 2) Lo completo con i setter
        l1.setTitolo("Il barone rampante");
        l1.setAutore("Italo Calvino");
        l1.setPagine(280);
        l1.setPrezzo(11.90);
        l1.visualizza();

        /* 3) Provo valori sbagliati.
         *    I setter li ignorano senza stampare nulla: per sapere se hanno
         *    funzionato controllo il valore con i getter. */
        l1.setPagine(-5);
        l1.setPrezzo(-3);
        System.out.println("Pagine dopo il tentativo errato: " + l1.getPagine());   // ancora 280
        System.out.println("Prezzo dopo il tentativo errato: " + l1.getPrezzo());   // ancora 11.9

        // 4) Oggetto creato con il costruttore CON PARAMETRI
        Libro l2 = new Libro("I promessi sposi", "Alessandro Manzoni", 720, 12.50);
        l2.visualizza();

        // 5) Metodi di comportamento, con un dato letto da tastiera
        System.out.print("Quante pagine hai letto de " + l2.getTitolo() + "? ");
        int lette = in.nextInt();
        if (l2.leggi(lette)) {                 // leggi() restituisce true se ha funzionato
            System.out.printf("Hai letto il %.1f%% del libro.%n", l2.percentualeLettura());  // %% stampa il simbolo %
        } else {
            System.out.println("Numero di pagine non valido.");   // il messaggio lo decide il main
        }

        if (l2.isFinito()) {
            System.out.println("Complimenti, libro finito!");
        } else {
            System.out.println("Ti mancano " + l2.pagineMancanti() + " pagine.");
        }

        // 6) Sconto e confronto tra due oggetti: si confrontano i VALORI letti con i getter
        if (!l2.applicaSconto(20)) {
            System.out.println("Sconto non valido.");
        }
        if (l1.getPrezzo() < l2.getPrezzo()) {
            System.out.println(l1.getTitolo() + " costa meno.");
        } else {
            System.out.println(l2.getTitolo() + " costa meno (o uguale).");
        }

        // 7) Stato finale dei due oggetti: ognuno ha i SUOI attributi
        l1.visualizza();
        l2.visualizza();
        in.close();   // lo Scanner si chiude quando non serve più
    }
}
