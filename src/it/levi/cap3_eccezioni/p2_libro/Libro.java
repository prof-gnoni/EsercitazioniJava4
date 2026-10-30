package it.levi.cap3_eccezioni.p2_libro;

/**
 * Il Libro della lezione di ripasso, in versione "professionale".
 *
 * <p>Differenza rispetto al package {@code cap1_classi.p1_lezione_libro}: i setter non IGNORANO più
 * i valori sbagliati, li SEGNALANO lanciando un'eccezione.</p>
 * <ul>
 *   <li>il setter resta void e continua a non stampare nulla;</li>
 *   <li>chi lo chiama (il main) sa subito che qualcosa non va e decide come reagire;</li>
 *   <li>il costruttore usa i setter: con un dato sbagliato l'oggetto NON viene creato.</li>
 * </ul>
 */
public class Libro {

    /* ===== ATTRIBUTI ===== */
    private String titolo;
    private String autore;
    private int pagine;
    private double prezzo;
    private int paginaCorrente;

    /* ===== COSTRUTTORI ===== */

    /** Costruttore vuoto: valori di default (sicuramente validi, non serve controllarli). */
    public Libro() {
        titolo = "Senza titolo";
        autore = "Anonimo";
        pagine = 100;
        prezzo = 0.0;
        paginaCorrente = 0;
    }

    /**
     * Costruttore con parametri: usa i setter, quindi controlla tutti i dati.
     * Se un dato è sbagliato, il setter lancia l'eccezione, il costruttore si
     * interrompe e l'oggetto non nasce: non può esistere un libro con -5 pagine.
     *
     * @param titolo il titolo (non vuoto)
     * @param autore l'autore (non vuoto)
     * @param pagine il numero di pagine (maggiore di 0)
     * @param prezzo il prezzo (non negativo)
     * @throws IllegalArgumentException se uno dei dati non è valido
     */
    public Libro(String titolo, String autore, int pagine, double prezzo) {
        setTitolo(titolo);
        setAutore(autore);
        setPagine(pagine);
        setPrezzo(prezzo);
        paginaCorrente = 0;
    }

    /* ===== GETTER ===== */

    /** @return il titolo */
    public String getTitolo() { return titolo; }

    /** @return l'autore */
    public String getAutore() { return autore; }

    /** @return il numero di pagine */
    public int getPagine() { return pagine; }

    /** @return il prezzo in euro */
    public double getPrezzo() { return prezzo; }

    /** @return la pagina del segnalibro */
    public int getPaginaCorrente() { return paginaCorrente; }

    /* =====================================================================
     * SETTER CHE LANCIANO ECCEZIONI
     * Schema: SE il valore è sbagliato -> throw (il metodo si ferma qui);
     *         altrimenti -> assegno. Si scrive prima il caso di errore.
     * ===================================================================== */

    /**
     * Cambia il titolo.
     *
     * @param titolo il nuovo titolo
     * @throws IllegalArgumentException se il titolo è null o vuoto
     */
    public void setTitolo(String titolo) {
        if (titolo == null || titolo.isEmpty()) {
            // throw new ...: creo l'oggetto eccezione (con new, come ogni oggetto) e lo lancio
            throw new IllegalArgumentException("Il titolo non può essere vuoto");
        }
        this.titolo = titolo;   // arrivo qui solo se il valore è valido
    }

    /**
     * Cambia l'autore.
     *
     * @param autore il nuovo autore
     * @throws IllegalArgumentException se l'autore è null o vuoto
     */
    public void setAutore(String autore) {
        if (autore == null || autore.isEmpty()) {
            throw new IllegalArgumentException("L'autore non può essere vuoto");
        }
        this.autore = autore;
    }

    /**
     * Cambia il numero di pagine.
     *
     * @param pagine il nuovo numero di pagine
     * @throws IllegalArgumentException se pagine è minore o uguale a 0
     */
    public void setPagine(int pagine) {
        if (pagine <= 0) {
            // buona pratica: nel messaggio metto anche il valore sbagliato
            throw new IllegalArgumentException("Pagine non valide: " + pagine);
        }
        this.pagine = pagine;
    }

    /**
     * Cambia il prezzo.
     *
     * @param prezzo il nuovo prezzo
     * @throws IllegalArgumentException se il prezzo è negativo
     */
    public void setPrezzo(double prezzo) {
        if (prezzo < 0) {
            throw new IllegalArgumentException("Il prezzo non può essere negativo: " + prezzo);
        }
        this.prezzo = prezzo;
    }

    /* ===== COMPORTAMENTO ===== */
    // Con le eccezioni, leggi() e applicaSconto() non hanno più bisogno di restituire boolean.

    /**
     * Fa avanzare il segnalibro, senza superare l'ultima pagina.
     *
     * @param numPagine le pagine lette
     * @throws IllegalArgumentException se numPagine è minore o uguale a 0
     */
    public void leggi(int numPagine) {
        if (numPagine <= 0) {
            throw new IllegalArgumentException("Numero di pagine da leggere non valido: " + numPagine);
        }
        paginaCorrente = paginaCorrente + numPagine;
        if (paginaCorrente > pagine) {
            paginaCorrente = pagine;
        }
    }

    /**
     * Applica uno sconto percentuale al prezzo.
     *
     * @param percentuale lo sconto (maggiore di 0, al massimo 100)
     * @throws IllegalArgumentException se la percentuale non è valida
     */
    public void applicaSconto(double percentuale) {
        if (percentuale <= 0 || percentuale > 100) {
            throw new IllegalArgumentException("Sconto non valido: " + percentuale + "%");
        }
        prezzo = prezzo - prezzo * percentuale / 100;
    }

    /* ===== CALCOLO ===== */

    /** @return le pagine che mancano alla fine */
    public int pagineMancanti() {
        return pagine - paginaCorrente;
    }

    /** @return la percentuale letta (100.0 evita la divisione intera) */
    public double percentualeLettura() {
        return paginaCorrente * 100.0 / pagine;
    }

    /** @return true se il libro è finito */
    public boolean isFinito() {
        return paginaCorrente == pagine;
    }

    /* ===== OUTPUT ===== */

    /** Stampa i dati del libro: resta l'unico metodo della classe che stampa. */
    public void visualizza() {
        System.out.println("--- LIBRO ---");
        System.out.println("Titolo:  " + titolo);
        System.out.println("Autore:  " + autore);
        System.out.println("Pagine:  " + pagine);
        System.out.printf("Prezzo:  %.2f euro%n", prezzo);
        System.out.println("Segnalibro a pagina " + paginaCorrente
                + " (" + pagineMancanti() + " mancanti)");
    }
}
