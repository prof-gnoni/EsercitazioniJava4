package it.levi.cap1_classi.p3_es2_termostato;

/**
 * Esercizio 2 (intermedio): un termostato che riscalda una stanza
 * fino alla temperatura desiderata, compresa tra 5 e 30 °C.
 */
public class Termostato {

    /* ===== ATTRIBUTI ===== */
    private String stanza;
    private double temperaturaAttuale;      // °C misurati nella stanza
    private double temperaturaDesiderata;   // °C impostati dall'utente (5-30)
    private boolean acceso;

    /* ===== COSTRUTTORI ===== */

    /** Costruttore vuoto: soggiorno a 18 °C, desiderata 20 °C, spento. */
    public Termostato() {
        stanza = "Soggiorno";
        temperaturaAttuale = 18.0;
        temperaturaDesiderata = 20.0;
        acceso = false;
    }

    /**
     * Costruttore con parametri. Riusa il setter per controllare la temperatura
     * desiderata: il controllo 5-30 è scritto in un solo punto della classe.
     *
     * @param stanza                il nome della stanza
     * @param temperaturaAttuale    la temperatura di partenza
     * @param temperaturaDesiderata la temperatura da raggiungere (5-30)
     */
    public Termostato(String stanza, double temperaturaAttuale, double temperaturaDesiderata) {
        this.stanza = stanza;
        this.temperaturaAttuale = temperaturaAttuale;
        this.temperaturaDesiderata = 20.0;                // default di sicurezza
        setTemperaturaDesiderata(temperaturaDesiderata);  // il setter controlla
        acceso = false;
    }

    /* ===== GETTER ===== */

    /** @return il nome della stanza */
    public String getStanza() { return stanza; }

    /** @return la temperatura attuale in °C */
    public double getTemperaturaAttuale() { return temperaturaAttuale; }

    /** @return la temperatura desiderata in °C */
    public double getTemperaturaDesiderata() { return temperaturaDesiderata; }

    /** @return true se il termostato è acceso */
    public boolean isAcceso() { return acceso; }

    /* ===== SETTER ===== */

    /**
     * Cambia il nome della stanza, solo se non è vuoto.
     *
     * @param stanza il nuovo nome
     */
    public void setStanza(String stanza) {
        if (stanza != null && !stanza.isEmpty()) {
            this.stanza = stanza;
        }
    }

    /**
     * Imposta la temperatura desiderata, solo se è tra 5 e 30 °C.
     *
     * @param t la nuova temperatura desiderata
     */
    public void setTemperaturaDesiderata(double t) {
        if (t >= 5.0 && t <= 30.0) {
            temperaturaDesiderata = t;
        }
    }

    // Non c'è setTemperaturaAttuale: la temperatura della stanza cambia solo con simula().

    /* ===== COMPORTAMENTO ===== */

    /** Accende il termostato. */
    public void accendi() { acceso = true; }

    /** Spegne il termostato. */
    public void spegni() { acceso = false; }

    /**
     * Alza la temperatura desiderata. Se si supera 30 °C, si ferma a 30
     * (non è un errore: il termostato arriva al massimo e si ferma lì).
     *
     * @param gradi di quanti gradi alzare
     */
    public void alza(double gradi) {
        temperaturaDesiderata = temperaturaDesiderata + gradi;
        if (temperaturaDesiderata > 30.0) {
            temperaturaDesiderata = 30.0;
        }
    }

    /**
     * Abbassa la temperatura desiderata, senza scendere sotto i 5 °C.
     *
     * @param gradi di quanti gradi abbassare
     */
    public void abbassa(double gradi) {
        temperaturaDesiderata = temperaturaDesiderata - gradi;
        if (temperaturaDesiderata < 5.0) {
            temperaturaDesiderata = 5.0;
        }
    }

    /**
     * Simula il passare del tempo: se il termostato è acceso e la stanza è più fredda
     * del desiderato, la temperatura sale di 0,1 °C al minuto, senza superare la desiderata.
     *
     * @param minuti i minuti da simulare (maggiori di 0)
     * @return true se la simulazione è avvenuta, false se il termostato è spento o i minuti non sono validi
     */
    public boolean simula(int minuti) {
        if (!acceso || minuti <= 0) {
            return false;
        }
        if (temperaturaAttuale < temperaturaDesiderata) {
            temperaturaAttuale = temperaturaAttuale + minuti * 0.1;
            if (temperaturaAttuale > temperaturaDesiderata) {
                temperaturaAttuale = temperaturaDesiderata;   // non si scalda oltre il desiderato
            }
        }
        return true;
    }

    /* ===== CALCOLO ===== */

    /** @return i gradi che mancano per arrivare alla temperatura desiderata */
    public double differenza() {
        return temperaturaDesiderata - temperaturaAttuale;
    }

    /** @return true se il termostato è acceso e la stanza non è ancora calda abbastanza */
    public boolean staRiscaldando() {
        return acceso && temperaturaAttuale < temperaturaDesiderata;   // si restituisce direttamente la condizione
    }

    /* ===== OUTPUT ===== */

    /** Stampa lo stato del termostato. L'unico metodo che stampa. */
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
