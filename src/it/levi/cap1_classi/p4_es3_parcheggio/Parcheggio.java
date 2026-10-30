package it.levi.cap1_classi.p4_es3_parcheggio;

/**
 * Esercizio 3 (avanzato): un parcheggio a pagamento.
 *
 * <p>Oltre all'overload dei costruttori, mostra l'overload di due METODI:</p>
 * <ul>
 *   <li>{@code entra()} ed {@code entra(int numeroAuto)};</li>
 *   <li>{@code esci(int ore)} ed {@code esci(int ore, int minuti)}.</li>
 * </ul>
 * Java sceglie la versione giusta in base al numero e al tipo dei parametri.
 */
public class Parcheggio {

    /* ===== ATTRIBUTI ===== */
    private String nome;
    private int postiTotali;
    private int postiOccupati;
    private double tariffaOraria;   // euro all'ora
    private double incasso;         // euro incassati in totale

    /* ===== COSTRUTTORI ===== */

    /** Costruttore vuoto: "Parcheggio Centro", 50 posti, 1,50 euro/h, vuoto. */
    public Parcheggio() {
        nome = "Parcheggio Centro";
        postiTotali = 50;
        postiOccupati = 0;
        tariffaOraria = 1.50;
        incasso = 0;
    }

    /**
     * Costruttore con parametri: il parcheggio nasce vuoto e senza incasso.
     *
     * @param nome          il nome del parcheggio
     * @param postiTotali   il numero di posti
     * @param tariffaOraria la tariffa in euro all'ora
     */
    public Parcheggio(String nome, int postiTotali, double tariffaOraria) {
        this.nome = nome;
        this.postiTotali = postiTotali;
        this.tariffaOraria = tariffaOraria;
        postiOccupati = 0;
        incasso = 0;
    }

    /* ===== GETTER ===== */

    /** @return il nome del parcheggio */
    public String getNome() { return nome; }

    /** @return il numero totale di posti */
    public int getPostiTotali() { return postiTotali; }

    /** @return il numero di posti occupati */
    public int getPostiOccupati() { return postiOccupati; }

    /** @return la tariffa in euro all'ora */
    public double getTariffaOraria() { return tariffaOraria; }

    /** @return l'incasso totale in euro */
    public double getIncasso() { return incasso; }

    /* ===== SETTER ===== */
    // Niente setter per postiOccupati e incasso: cambiano solo con entra() ed esci().

    /**
     * Cambia il nome, solo se non è vuoto.
     *
     * @param nome il nuovo nome
     */
    public void setNome(String nome) {
        if (nome != null && !nome.isEmpty()) {
            this.nome = nome;
        }
    }

    /**
     * Cambia la tariffa, solo se è maggiore di zero.
     *
     * @param tariffaOraria la nuova tariffa in euro all'ora
     */
    public void setTariffaOraria(double tariffaOraria) {
        if (tariffaOraria > 0) {
            this.tariffaOraria = tariffaOraria;
        }
    }

    /* =====================================================================
     * OVERLOAD DI entra: stesso nome, parametri diversi
     * ===================================================================== */

    /**
     * Fa entrare UNA auto, se c'è posto.
     *
     * @return true se l'auto è entrata, false se il parcheggio è pieno
     */
    public boolean entra() {
        if (postiOccupati < postiTotali) {
            postiOccupati++;
            return true;
        }
        return false;
    }

    /**
     * Fa entrare un GRUPPO di auto, solo se ci stanno tutte
     * (non ne entra solo una parte).
     *
     * @param numeroAuto quante auto vogliono entrare
     * @return true se il gruppo è entrato, false altrimenti
     */
    public boolean entra(int numeroAuto) {
        if (numeroAuto > 0 && postiOccupati + numeroAuto <= postiTotali) {
            postiOccupati = postiOccupati + numeroAuto;
            return true;
        }
        return false;
    }

    /* =====================================================================
     * OVERLOAD DI esci
     * ===================================================================== */

    /**
     * Fa uscire un'auto che paga le ore indicate.
     *
     * @param ore le ore di sosta (maggiori di 0)
     * @return true se l'auto è uscita, false se il parcheggio è vuoto o ore non è valido
     */
    public boolean esci(int ore) {
        if (postiOccupati == 0 || ore <= 0) {
            return false;
        }
        postiOccupati--;
        incasso = incasso + ore * tariffaOraria;
        return true;
    }

    /**
     * Fa uscire un'auto con una sosta espressa in ore e minuti:
     * un'ora iniziata si paga intera (2 ore e 10 minuti = 3 ore).
     * Invece di riscrivere il codice, RIUSA la versione con un parametro.
     *
     * @param ore    le ore intere di sosta
     * @param minuti i minuti oltre le ore (da 0 a 59)
     * @return true se l'auto è uscita, false altrimenti
     */
    public boolean esci(int ore, int minuti) {
        if (minuti < 0 || minuti > 59) {
            return false;
        }
        int oreDaPagare = ore;
        if (minuti > 0) {
            oreDaPagare++;          // un'ora iniziata si paga intera
        }
        return esci(oreDaPagare);   // chiama l'altra versione di esci
    }

    /* ===== CALCOLO ===== */

    /** @return i posti ancora liberi */
    public int postiLiberi() {
        return postiTotali - postiOccupati;
    }

    /** @return la percentuale di posti occupati (100.0 evita la divisione intera) */
    public double percentualeOccupazione() {
        return postiOccupati * 100.0 / postiTotali;
    }

    /** @return true se non ci sono più posti liberi */
    public boolean isPieno() {
        return postiOccupati == postiTotali;
    }

    /* ===== OUTPUT ===== */

    /** Stampa lo stato del parcheggio. */
    public void visualizza() {
        System.out.println("=== " + nome + " ===");
        System.out.println("Posti occupati: " + postiOccupati + "/" + postiTotali
                + " (liberi: " + postiLiberi() + ")");
        System.out.printf("Occupazione: %.1f%% | Tariffa: %.2f euro/h | Incasso: %.2f euro%n",
                percentualeOccupazione(), tariffaOraria, incasso);
    }
}
