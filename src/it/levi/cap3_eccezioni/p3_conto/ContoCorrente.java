package it.levi.cap3_eccezioni.p3_conto;

/**
 * Un conto corrente bancario: l'esempio del bancomat della teoria.
 *
 * <p>Usa due tipi di eccezione, per due problemi diversi:</p>
 * <ul>
 *   <li>{@code IllegalArgumentException} (unchecked): il PARAMETRO è sbagliato,
 *       ad esempio un importo negativo;</li>
 *   <li>{@code SaldoInsufficienteException} (checked, creata da noi): una situazione
 *       PREVEDIBILE del conto, che chi chiama deve per forza gestire.</li>
 * </ul>
 */
public class ContoCorrente {

    /* ===== ATTRIBUTI ===== */
    private String intestatario;
    private double saldo;   // euro disponibili

    /* ===== COSTRUTTORI ===== */

    /** Costruttore vuoto: intestatario "Sconosciuto", saldo 0. */
    public ContoCorrente() {
        intestatario = "Sconosciuto";
        saldo = 0;
    }

    /**
     * Costruttore con parametri.
     *
     * @param intestatario  il nome dell'intestatario (non vuoto)
     * @param saldoIniziale il saldo di partenza (non negativo)
     * @throws IllegalArgumentException se l'intestatario è vuoto o il saldo è negativo
     */
    public ContoCorrente(String intestatario, double saldoIniziale) {
        setIntestatario(intestatario);
        if (saldoIniziale < 0) {
            throw new IllegalArgumentException("Il saldo iniziale non può essere negativo");
        }
        saldo = saldoIniziale;
    }

    /* ===== GETTER ===== */

    /** @return l'intestatario */
    public String getIntestatario() { return intestatario; }

    /** @return il saldo in euro */
    public double getSaldo() { return saldo; }

    // Volutamente NON esiste setSaldo: il saldo cambia solo con versa() e preleva().

    /* ===== SETTER ===== */

    /**
     * Cambia l'intestatario.
     *
     * @param intestatario il nuovo intestatario
     * @throws IllegalArgumentException se è null o vuoto
     */
    public void setIntestatario(String intestatario) {
        if (intestatario == null || intestatario.trim().isEmpty()) {
            throw new IllegalArgumentException("L'intestatario non può essere vuoto");
        }
        this.intestatario = intestatario.trim();
    }

    /* ===== OPERAZIONI ===== */

    /**
     * Versa denaro sul conto.
     * Non ha "throws" nell'intestazione: IllegalArgumentException è unchecked,
     * quindi non è obbligatorio dichiararla.
     *
     * @param importo l'importo da versare
     * @throws IllegalArgumentException se l'importo è minore o uguale a 0
     */
    public void versa(double importo) {
        if (importo <= 0) {
            throw new IllegalArgumentException("L'importo da versare deve essere positivo");
        }
        saldo = saldo + importo;
    }

    /**
     * Preleva denaro dal conto.
     *
     * <p>"throws SaldoInsufficienteException" nell'intestazione è un AVVISO per chi chiama:
     * "questo metodo può lanciare questa eccezione, preparati a gestirla".
     * Attenzione a non confondere: throws (con la s) avvisa, throw (senza s) lancia.</p>
     *
     * @param importo l'importo da prelevare
     * @throws IllegalArgumentException     se l'importo è minore o uguale a 0
     * @throws SaldoInsufficienteException  se l'importo supera il saldo
     */
    public void preleva(double importo) throws SaldoInsufficienteException {
        if (importo <= 0) {
            throw new IllegalArgumentException("L'importo da prelevare deve essere positivo");
        }
        if (importo > saldo) {
            // String.format costruisce il messaggio come printf, ma lo restituisce invece di stamparlo
            throw new SaldoInsufficienteException(String.format(
                    "Saldo insufficiente: disponibili %.2f euro, richiesti %.2f euro", saldo, importo));
        }
        saldo = saldo - importo;   // arrivo qui solo se tutto è in regola
    }

    /**
     * Trasferisce denaro su un altro conto.
     *
     * <p>Questo metodo NON cattura l'eccezione di preleva(): la lascia RISALIRE
     * a chi ha chiamato bonifico(). Per questo anche qui serve "throws".
     * Se preleva() lancia, la riga con versa() non viene eseguita:
     * i soldi non spariscono e non compaiono dal nulla.</p>
     *
     * @param destinatario il conto che riceve il denaro (un altro oggetto ContoCorrente)
     * @param importo      l'importo da trasferire
     * @throws SaldoInsufficienteException se il saldo non basta
     */
    public void bonifico(ContoCorrente destinatario, double importo) throws SaldoInsufficienteException {
        preleva(importo);
        destinatario.versa(importo);
    }

    /* ===== OUTPUT ===== */

    /** Stampa intestatario e saldo. */
    public void visualizza() {
        System.out.printf("Conto di %s - saldo: %.2f euro%n", intestatario, saldo);   // %s = una stringa
    }
}
