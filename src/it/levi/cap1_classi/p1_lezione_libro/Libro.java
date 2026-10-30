package it.levi.cap1_classi.p1_lezione_libro;

/**
 * Classe di supporto della lezione di ripasso: rappresenta un libro.
 *
 * <p>Concetti mostrati in questa classe:</p>
 * <ul>
 *   <li>attributi {@code private} (incapsulamento);</li>
 *   <li>due costruttori in overload (stesso nome, parametri diversi);</li>
 *   <li>getter e setter, con il controllo dei valori nei setter;</li>
 *   <li>metodi di comportamento e metodi di calcolo;</li>
 *   <li>{@code visualizza()}: l'unico metodo della classe che stampa su console.</li>
 * </ul>
 */
public class Libro {

    /* =====================================================================
     * ATTRIBUTI (lo STATO dell'oggetto)
     * Sono private: dall'esterno si leggono con i getter e si modificano
     * solo con i metodi della classe, che controllano i valori.
     * ===================================================================== */
    private String titolo;
    private String autore;
    private int pagine;
    private double prezzo;
    private int paginaCorrente;   // segnalibro: si modifica solo leggendo, non con un setter

    /* =====================================================================
     * COSTRUTTORI (overload)
     * Hanno lo stesso nome della classe e NESSUN tipo di ritorno (nemmeno void).
     * Java sceglie quale chiamare guardando i parametri passati a new.
     * ===================================================================== */

    /**
     * Costruttore vuoto: crea un libro con valori di default sensati.
     * Esempio: {@code Libro l = new Libro();}
     */
    public Libro() {
        titolo = "Senza titolo";
        autore = "Anonimo";
        pagine = 100;
        prezzo = 0.0;
        paginaCorrente = 0;
    }

    /**
     * Costruttore con parametri: crea un libro con i dati indicati.
     * {@code this.titolo} è l'attributo dell'oggetto, {@code titolo} il parametro:
     * senza {@code this} assegneremmo il parametro a se stesso.
     *
     * @param titolo il titolo del libro
     * @param autore l'autore
     * @param pagine il numero di pagine
     * @param prezzo il prezzo in euro
     */
    public Libro(String titolo, String autore, int pagine, double prezzo) {
        this.titolo = titolo;
        this.autore = autore;
        this.pagine = pagine;
        this.prezzo = prezzo;
        this.paginaCorrente = 0;   // un libro nuovo parte sempre dalla prima pagina
    }

    /* =====================================================================
     * GETTER: restituiscono il valore di un attributo senza modificarlo.
     * ===================================================================== */

    /** @return il titolo del libro */
    public String getTitolo() { return titolo; }

    /** @return l'autore del libro */
    public String getAutore() { return autore; }

    /** @return il numero di pagine */
    public int getPagine() { return pagine; }

    /** @return il prezzo in euro */
    public double getPrezzo() { return prezzo; }

    /** @return la pagina a cui è arrivato il segnalibro */
    public int getPaginaCorrente() { return paginaCorrente; }

    /* =====================================================================
     * SETTER con controllo
     * Convenzione Java: i setter sono void. Se il valore non è valido,
     * l'attributo resta com'era e NON si stampa nulla (la classe non fa output).
     * Quando studieremo le eccezioni, qui lanceremo IllegalArgumentException.
     * ===================================================================== */

    /**
     * Cambia il titolo, solo se non è null e non è vuoto.
     *
     * @param titolo il nuovo titolo
     */
    public void setTitolo(String titolo) {
        if (titolo != null && !titolo.isEmpty()) {   // prima controllo null, poi isEmpty
            this.titolo = titolo;
        }
    }

    /**
     * Cambia l'autore, solo se non è null e non è vuoto.
     *
     * @param autore il nuovo autore
     */
    public void setAutore(String autore) {
        if (autore != null && !autore.isEmpty()) {
            this.autore = autore;
        }
    }

    /**
     * Cambia il numero di pagine, solo se è maggiore di zero.
     *
     * @param pagine il nuovo numero di pagine
     */
    public void setPagine(int pagine) {
        if (pagine > 0) {
            this.pagine = pagine;
        }
    }

    /**
     * Cambia il prezzo, solo se non è negativo (un libro in regalo costa 0).
     *
     * @param prezzo il nuovo prezzo in euro
     */
    public void setPrezzo(double prezzo) {
        if (prezzo >= 0) {
            this.prezzo = prezzo;
        }
    }
    // Volutamente NON esiste setPaginaCorrente: il segnalibro avanza solo con leggi(),
    // così lo stato dell'oggetto resta sempre coerente.

    /* =====================================================================
     * METODI DI COMPORTAMENTO
     * Non sono setter: se l'operazione può fallire, comunicano l'esito
     * restituendo un boolean (true = fatto, false = rifiutato).
     * ===================================================================== */

    /**
     * Fa avanzare il segnalibro del numero di pagine indicato,
     * senza mai superare l'ultima pagina.
     *
     * @param numPagine le pagine lette (deve essere maggiore di 0)
     * @return true se la lettura è stata registrata, false se numPagine non è valido
     */
    public boolean leggi(int numPagine) {
        if (numPagine <= 0) {
            return false;                  // valore non valido: non cambio nulla
        }
        paginaCorrente = paginaCorrente + numPagine;
        if (paginaCorrente > pagine) {
            paginaCorrente = pagine;       // non si legge oltre la fine del libro
        }
        return true;
    }

    /**
     * Applica uno sconto percentuale al prezzo.
     * Esempio: con prezzo 20 e sconto 10, il nuovo prezzo è 18.
     *
     * @param percentuale lo sconto, maggiore di 0 e al massimo 100
     * @return true se lo sconto è stato applicato, false se la percentuale non è valida
     */
    public boolean applicaSconto(double percentuale) {
        if (percentuale > 0 && percentuale <= 100) {
            prezzo = prezzo - prezzo * percentuale / 100;
            return true;
        }
        return false;
    }

    /* =====================================================================
     * METODI DI CALCOLO: non modificano nulla, restituiscono un valore.
     * ===================================================================== */

    /** @return quante pagine mancano alla fine del libro */
    public int pagineMancanti() {
        return pagine - paginaCorrente;
    }

    /**
     * Calcola la percentuale di libro già letta.
     * ATTENZIONE: con 100 (intero) la divisione tra int darebbe un risultato
     * troncato; con 100.0 il calcolo diventa decimale.
     *
     * @return la percentuale letta, da 0 a 100
     */
    public double percentualeLettura() {
        return paginaCorrente * 100.0 / pagine;
    }

    /** @return true se il segnalibro è arrivato all'ultima pagina */
    public boolean isFinito() {
        return paginaCorrente == pagine;
    }

    /* =====================================================================
     * OUTPUT
     * ===================================================================== */

    /**
     * Stampa su console tutti i dati del libro.
     * È l'unico metodo della classe che usa System.out.
     */
    public void visualizza() {
        System.out.println("--- LIBRO ---");
        System.out.println("Titolo:  " + titolo);
        System.out.println("Autore:  " + autore);
        System.out.println("Pagine:  " + pagine);
        System.out.printf("Prezzo:  %.2f euro%n", prezzo);    // %.2f = 2 decimali, %n = a capo
        System.out.println("Segnalibro a pagina " + paginaCorrente
                + " (" + pagineMancanti() + " mancanti)");      // riuso un metodo già scritto
    }
}
