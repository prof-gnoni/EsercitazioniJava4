package it.levi.cap3_eccezioni.p6_es3_parcheggio;

/**
 * Eccezione personalizzata (checked): non ci sono abbastanza posti nel parcheggio.
 * Stessa "formula" di SaldoInsufficienteException: extends Exception + super(messaggio).
 */
public class ParcheggioPienoException extends Exception {

    /**
     * Crea l'eccezione con il messaggio che descrive il problema.
     *
     * @param messaggio il dettaglio, es. "Posti liberi: 2, auto richieste: 3"
     */
    public ParcheggioPienoException(String messaggio) {
        super(messaggio);
    }
}
