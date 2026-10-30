package it.levi.cap3_eccezioni.p6_es3_parcheggio;

/**
 * Esercizio eccezioni 3 (intermedio): il Parcheggio dell'esercitazione, con le eccezioni.
 *
 * <p>Confronto con la versione della lezione:</p>
 * <ul>
 *   <li>entra() ed entra(int) prima restituivano boolean, ora sono void
 *       e lanciano ParcheggioPienoException (checked, creata da noi);</li>
 *   <li>esci() lancia IllegalStateException se il parcheggio è vuoto
 *       e IllegalArgumentException se le ore o i minuti non sono validi;</li>
 *   <li>il costruttore rifiuta dati sbagliati: l'oggetto non nasce.</li>
 * </ul>
 * L'overload di entra ed esci resta identico.
 */
public class Parcheggio {

    /* ===== ATTRIBUTI ===== */
    private String nome;
    private int postiTotali;
    private int postiOccupati;
    private double tariffaOraria;
    private double incasso;

    /* ===== COSTRUTTORI ===== */

    /** Costruttore vuoto: "Parcheggio Centro", 50 posti, 1,50 euro/h. */
    public Parcheggio() {
        nome = "Parcheggio Centro";
        postiTotali = 50;
        postiOccupati = 0;
        tariffaOraria = 1.50;
        incasso = 0;
    }

    /**
     * Costruttore con parametri. Tutti i controlli vengono fatti PRIMA di assegnare:
     * se un dato è sbagliato, l'eccezione blocca la creazione dell'oggetto.
     *
     * @param nome          il nome (non vuoto)
     * @param postiTotali   i posti (almeno 1)
     * @param tariffaOraria la tariffa in euro all'ora (positiva)
     * @throws IllegalArgumentException se un dato non è valido
     */
    public Parcheggio(String nome, int postiTotali, double tariffaOraria) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Il nome non può essere vuoto");
        }
        if (postiTotali <= 0) {
            throw new IllegalArgumentException("I posti devono essere almeno 1");
        }
        this.nome = nome.trim();
        this.postiTotali = postiTotali;
        setTariffaOraria(tariffaOraria);   // il setter lancia se la tariffa non è valida
        postiOccupati = 0;
        incasso = 0;
    }

    /* ===== GETTER ===== */

    /** @return il nome */
    public String getNome() { return nome; }

    /** @return i posti totali */
    public int getPostiTotali() { return postiTotali; }

    /** @return i posti occupati */
    public int getPostiOccupati() { return postiOccupati; }

    /** @return la tariffa in euro all'ora */
    public double getTariffaOraria() { return tariffaOraria; }

    /** @return l'incasso totale in euro */
    public double getIncasso() { return incasso; }

    /* ===== SETTER ===== */

    /**
     * Cambia la tariffa oraria.
     *
     * @param tariffaOraria la nuova tariffa
     * @throws IllegalArgumentException se la tariffa non è positiva
     */
    public void setTariffaOraria(double tariffaOraria) {
        if (tariffaOraria <= 0) {
            throw new IllegalArgumentException("La tariffa deve essere positiva");
        }
        this.tariffaOraria = tariffaOraria;
    }

    /* =====================================================================
     * OVERLOAD DI entra, con eccezione CHECKED
     * "throws ParcheggioPienoException" obbliga chi chiama a usare try/catch.
     * ===================================================================== */

    /**
     * Fa entrare una auto.
     *
     * @throws ParcheggioPienoException se non ci sono posti liberi
     */
    public void entra() throws ParcheggioPienoException {
        if (isPieno()) {
            throw new ParcheggioPienoException(nome + " è pieno");
        }
        postiOccupati++;
    }

    /**
     * Fa entrare un gruppo di auto, solo se ci stanno tutte.
     *
     * @param numeroAuto quante auto vogliono entrare
     * @throws IllegalArgumentException  se numeroAuto non è positivo (parametro sbagliato)
     * @throws ParcheggioPienoException  se i posti liberi non bastano (situazione del parcheggio)
     */
    public void entra(int numeroAuto) throws ParcheggioPienoException {
        if (numeroAuto <= 0) {
            throw new IllegalArgumentException("Numero di auto non valido: " + numeroAuto);
        }
        if (postiOccupati + numeroAuto > postiTotali) {
            throw new ParcheggioPienoException("Posti liberi: " + postiLiberi()
                    + ", auto richieste: " + numeroAuto);
        }
        postiOccupati = postiOccupati + numeroAuto;
    }

    /* =====================================================================
     * OVERLOAD DI esci, con eccezioni UNCHECKED (nessun throws obbligatorio)
     * ===================================================================== */

    /**
     * Fa uscire un'auto che paga le ore indicate.
     *
     * @param ore le ore di sosta
     * @throws IllegalStateException    se il parcheggio è vuoto
     * @throws IllegalArgumentException se le ore non sono positive
     */
    public void esci(int ore) {
        if (postiOccupati == 0) {
            throw new IllegalStateException("Il parcheggio è vuoto: nessuna auto può uscire");
        }
        if (ore <= 0) {
            throw new IllegalArgumentException("Le ore devono essere positive");
        }
        postiOccupati--;
        incasso = incasso + ore * tariffaOraria;
    }

    /**
     * Fa uscire un'auto con sosta in ore e minuti (l'ora iniziata si paga intera).
     * Riusa esci(int): se quella lancia un'eccezione, l'eccezione risale da qui al chiamante.
     *
     * @param ore    le ore intere
     * @param minuti i minuti in più (0-59)
     * @throws IllegalArgumentException se i minuti non sono tra 0 e 59
     */
    public void esci(int ore, int minuti) {
        if (minuti < 0 || minuti > 59) {
            throw new IllegalArgumentException("Minuti non validi: " + minuti);
        }
        int oreDaPagare = ore;
        if (minuti > 0) {
            oreDaPagare++;
        }
        esci(oreDaPagare);
    }

    /* ===== CALCOLO ===== */

    /** @return i posti liberi */
    public int postiLiberi() {
        return postiTotali - postiOccupati;
    }

    /** @return la percentuale di occupazione */
    public double percentualeOccupazione() {
        return postiOccupati * 100.0 / postiTotali;
    }

    /** @return true se il parcheggio è pieno */
    public boolean isPieno() {
        return postiOccupati == postiTotali;
    }

    /* ===== OUTPUT ===== */

    /** Stampa lo stato del parcheggio. */
    public void visualizza() {
        System.out.println("=== " + nome + " ===");
        System.out.println("Posti occupati: " + postiOccupati + "/" + postiTotali
                + " (liberi: " + postiLiberi() + ")");
        System.out.printf("Occupazione: %.1f%% | Incasso: %.2f euro%n", percentualeOccupazione(), incasso);
    }
}
