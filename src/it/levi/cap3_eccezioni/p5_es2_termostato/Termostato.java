package it.levi.cap3_eccezioni.p5_es2_termostato;

/**
 * Esercizio eccezioni 2 (intermedio): il Termostato dell'esercitazione, con le eccezioni.
 *
 * <p>Si usano due eccezioni standard di Java, entrambe unchecked:</p>
 * <ul>
 *   <li>{@code IllegalArgumentException}: il PARAMETRO ricevuto è sbagliato
 *       (es. una temperatura di 40 °C);</li>
 *   <li>{@code IllegalStateException}: il parametro andrebbe bene, ma l'oggetto
 *       non è nello STATO giusto per quell'operazione (es. simulare a termostato spento).</li>
 * </ul>
 */
public class Termostato {

    /* ===== ATTRIBUTI ===== */
    private String stanza;
    private double temperaturaAttuale;
    private double temperaturaDesiderata;   // tra 5 e 30 °C
    private boolean acceso;

    /* ===== COSTRUTTORI ===== */

    /** Costruttore vuoto: soggiorno, 18 °C, desiderata 20 °C, spento. */
    public Termostato() {
        stanza = "Soggiorno";
        temperaturaAttuale = 18.0;
        temperaturaDesiderata = 20.0;
        acceso = false;
    }

    /**
     * Costruttore con parametri: i setter controllano i dati.
     *
     * @param stanza                il nome della stanza (non vuoto)
     * @param temperaturaAttuale    la temperatura di partenza
     * @param temperaturaDesiderata la temperatura da raggiungere (5-30)
     * @throws IllegalArgumentException se la stanza è vuota o la temperatura è fuori intervallo
     */
    public Termostato(String stanza, double temperaturaAttuale, double temperaturaDesiderata) {
        setStanza(stanza);
        this.temperaturaAttuale = temperaturaAttuale;
        setTemperaturaDesiderata(temperaturaDesiderata);
        acceso = false;
    }

    /* ===== GETTER ===== */

    /** @return il nome della stanza */
    public String getStanza() { return stanza; }

    /** @return la temperatura attuale */
    public double getTemperaturaAttuale() { return temperaturaAttuale; }

    /** @return la temperatura desiderata */
    public double getTemperaturaDesiderata() { return temperaturaDesiderata; }

    /** @return true se acceso */
    public boolean isAcceso() { return acceso; }

    /* ===== SETTER ===== */

    /**
     * Cambia il nome della stanza.
     *
     * @param stanza il nuovo nome
     * @throws IllegalArgumentException se il nome è vuoto
     */
    public void setStanza(String stanza) {
        if (stanza == null || stanza.trim().isEmpty()) {
            throw new IllegalArgumentException("Il nome della stanza non può essere vuoto");
        }
        this.stanza = stanza.trim();
    }

    /**
     * Imposta la temperatura desiderata. È l'UNICO punto della classe
     * in cui si controlla l'intervallo 5-30: alza() e abbassa() lo riusano.
     *
     * @param t la nuova temperatura
     * @throws IllegalArgumentException se t è fuori dall'intervallo 5-30
     */
    public void setTemperaturaDesiderata(double t) {
        if (t < 5.0 || t > 30.0) {
            throw new IllegalArgumentException("Temperatura " + t + " fuori intervallo (5-30 °C)");
        }
        temperaturaDesiderata = t;
    }

    /* ===== COMPORTAMENTO ===== */

    /** Accende il termostato. */
    public void accendi() { acceso = true; }

    /** Spegne il termostato. */
    public void spegni() { acceso = false; }

    /**
     * Alza la temperatura desiderata. Il limite dei 30 °C lo controlla il setter:
     * se si supera, l'eccezione parte dal setter e "attraversa" questo metodo.
     *
     * @param gradi di quanti gradi alzare
     * @throws IllegalArgumentException se gradi non è positivo o si supererebbero i 30 °C
     */
    public void alza(double gradi) {
        if (gradi <= 0) {
            throw new IllegalArgumentException("I gradi devono essere positivi");
        }
        setTemperaturaDesiderata(temperaturaDesiderata + gradi);
    }

    /**
     * Abbassa la temperatura desiderata.
     *
     * @param gradi di quanti gradi abbassare
     * @throws IllegalArgumentException se gradi non è positivo o si scenderebbe sotto i 5 °C
     */
    public void abbassa(double gradi) {
        if (gradi <= 0) {
            throw new IllegalArgumentException("I gradi devono essere positivi");
        }
        setTemperaturaDesiderata(temperaturaDesiderata - gradi);
    }

    /**
     * Simula il riscaldamento: +0,1 °C al minuto, fino alla temperatura desiderata.
     *
     * @param minuti i minuti da simulare
     * @throws IllegalStateException    se il termostato è spento (problema di STATO)
     * @throws IllegalArgumentException se i minuti non sono positivi (problema di PARAMETRO)
     */
    public void simula(int minuti) {
        if (!acceso) {
            throw new IllegalStateException("Il termostato è spento: accendilo prima di simulare");
        }
        if (minuti <= 0) {
            throw new IllegalArgumentException("I minuti devono essere positivi");
        }
        if (temperaturaAttuale < temperaturaDesiderata) {
            temperaturaAttuale = temperaturaAttuale + minuti * 0.1;
            if (temperaturaAttuale > temperaturaDesiderata) {
                temperaturaAttuale = temperaturaDesiderata;
            }
        }
    }

    /* ===== CALCOLO ===== */

    /** @return i gradi che mancano alla temperatura desiderata */
    public double differenza() {
        return temperaturaDesiderata - temperaturaAttuale;
    }

    /** @return true se acceso e la stanza è ancora più fredda del desiderato */
    public boolean staRiscaldando() {
        return acceso && temperaturaAttuale < temperaturaDesiderata;
    }

    /* ===== OUTPUT ===== */

    /** Stampa lo stato del termostato. */
    public void visualizza() {
        System.out.println("--- Termostato " + stanza + " ---");
        if (acceso) {
            System.out.println("Stato: ACCESO");
        } else {
            System.out.println("Stato: SPENTO");
        }
        System.out.printf("Attuale: %.1f °C | Desiderata: %.1f °C | Differenza: %.1f °C%n",
                temperaturaAttuale, temperaturaDesiderata, differenza());
        if (staRiscaldando()) {
            System.out.println("Sta riscaldando...");
        }
    }
}
