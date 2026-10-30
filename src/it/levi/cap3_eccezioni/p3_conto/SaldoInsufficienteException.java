package it.levi.cap3_eccezioni.p3_conto;

/**
 * Eccezione personalizzata: il saldo del conto non basta per l'operazione richiesta.
 *
 * <p>Non abbiamo ancora studiato l'ereditarietà, quindi per ora questa struttura
 * si prende come una FORMULA da copiare:</p>
 * <ul>
 *   <li>{@code extends Exception} dice a Java "questa classe è un'eccezione".
 *       Poiché deriva da Exception (e non da RuntimeException) è una eccezione CHECKED:
 *       chi chiama un metodo che la lancia è OBBLIGATO a gestirla;</li>
 *   <li>{@code super(messaggio)} salva il messaggio, che poi si legge con {@code getMessage()};</li>
 *   <li>il nome della classe finisce sempre con "Exception".</li>
 * </ul>
 */
public class SaldoInsufficienteException extends Exception {

    /**
     * Crea l'eccezione con il messaggio che descrive il problema.
     *
     * @param messaggio il dettaglio, es. "Saldo insufficiente: disponibili 50 euro"
     */
    public SaldoInsufficienteException(String messaggio) {
        super(messaggio);
    }
}
