package it.levi.cap3_eccezioni.p3_conto;

/**
 * Main del conto corrente: gestione di eccezioni checked e unchecked.
 */
public class Main {

    /**
     * Punto di partenza del programma.
     *
     * @param args non usati
     */
    public static void main(String[] args) {
        ContoCorrente anna = new ContoCorrente("Anna Bianchi", 300);
        ContoCorrente marco = new ContoCorrente("Marco Verdi", 50);
        anna.visualizza();
        marco.visualizza();

        /* ECCEZIONE CHECKED: Java ci OBBLIGA a gestirla.
           ESPERIMENTO: togli try/catch lasciando solo le due chiamate a preleva:
           IntelliJ sottolinea in rosso con "Unhandled exception" e il codice non compila. */
        try {
            anna.preleva(100);
            System.out.println("Prelievo di 100 euro riuscito.");
            marco.preleva(80);                                      // Marco ha solo 50 euro
            System.out.println("Questa riga non viene eseguita");
        } catch (SaldoInsufficienteException e) {
            System.out.println("Operazione rifiutata. " + e.getMessage());
        }

        /* ECCEZIONE UNCHECKED: non siamo obbligati a gestirla,
           ma conviene farlo quando il dato può essere sbagliato. */
        try {
            marco.versa(-20);
        } catch (IllegalArgumentException e) {
            System.out.println("Errore: " + e.getMessage());
        }

        /* DUE CATCH: uno per ogni tipo di problema.
           Il primo bonifico riesce, il secondo no (ad Anna restano 50 euro). */
        try {
            anna.bonifico(marco, 150);
            System.out.println("Bonifico di 150 euro eseguito.");
            anna.bonifico(marco, 500);
        } catch (SaldoInsufficienteException e) {
            System.out.println("Bonifico rifiutato. " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Importo non valido: " + e.getMessage());
        }

        // Saldi finali: Anna 50 euro, Marco 200 euro
        anna.visualizza();
        marco.visualizza();
    }
}
