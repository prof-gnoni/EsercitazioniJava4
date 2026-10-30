package it.levi.cap1_classi.p2_es1_lampadina;

/**
 * Main dell'esercizio 1: usa tutti i metodi di {@link Lampadina}.
 */
public class Main {

    /**
     * Punto di partenza del programma.
     *
     * @param args non usati
     */
    public static void main(String[] args) {
        // Un oggetto per costruttore
        Lampadina l1 = new Lampadina();
        Lampadina l2 = new Lampadina("Philips", 60);
        l1.visualizza();
        l2.visualizza();

        // usa() da spenta: il metodo restituisce false e il main avvisa l'utente
        if (!l1.usa(3)) {
            System.out.println("Impossibile: la lampadina " + l1.getMarca() + " è spenta.");
        }

        // usa() da accesa: ora funziona
        l1.accendi();
        l1.usa(5);
        l2.accendi();
        l2.usa(2.5);

        /* Potenza non valida: il setter la ignora in silenzio.
           Verifico con il getter che il valore non sia cambiato. */
        l2.setPotenzaWatt(500);
        System.out.println("Potenza di l2 dopo il tentativo con 500 W: " + l2.getPotenzaWatt() + " W");

        l1.spegni();
        l1.visualizza();
        l2.visualizza();

        // Confronto tra due oggetti usando i valori restituiti dai metodi
        if (l1.consumoKWh() > l2.consumoKWh()) {
            System.out.println(l1.getMarca() + " consuma di più.");
        } else if (l2.consumoKWh() > l1.consumoKWh()) {
            System.out.println(l2.getMarca() + " consuma di più.");
        } else {
            System.out.println("Consumano uguale.");
        }
    }
}
