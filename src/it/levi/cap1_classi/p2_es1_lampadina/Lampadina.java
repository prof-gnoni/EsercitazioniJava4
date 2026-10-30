package it.levi.cap1_classi.p2_es1_lampadina;

/**
 * Esercizio 1 (base): una lampadina che si accende, si spegne
 * e tiene il conto delle ore di utilizzo e dei consumi.
 */
public class Lampadina {

    /* ===== ATTRIBUTI (privati) ===== */
    private String marca;
    private int potenzaWatt;      // potenza in watt, da 1 a 200
    private boolean accesa;       // true = accesa, false = spenta
    private double oreUtilizzo;   // ore totali in cui è rimasta accesa

    /* ===== COSTRUTTORI (overload) ===== */

    /** Costruttore vuoto: lampadina "Generica" da 10 W, spenta, mai usata. */
    public Lampadina() {
        marca = "Generica";
        potenzaWatt = 10;
        accesa = false;
        oreUtilizzo = 0;
    }

    /**
     * Costruttore con parametri. La potenza viene controllata dal setter:
     * prima assegno un valore di sicurezza, poi il setter lo sostituisce
     * solo se il valore ricevuto è valido. Così il controllo è scritto una volta sola.
     *
     * @param marca       la marca della lampadina
     * @param potenzaWatt la potenza in watt (accettata tra 1 e 200)
     */
    public Lampadina(String marca, int potenzaWatt) {
        this.marca = marca;
        this.potenzaWatt = 10;        // valore di sicurezza
        setPotenzaWatt(potenzaWatt);  // il setter controlla l'intervallo
        accesa = false;
        oreUtilizzo = 0;
    }

    /* ===== GETTER ===== */

    /** @return la marca */
    public String getMarca() { return marca; }

    /** @return la potenza in watt */
    public int getPotenzaWatt() { return potenzaWatt; }

    /**
     * Per gli attributi boolean il getter per convenzione si chiama isXxx invece di getXxx.
     *
     * @return true se la lampadina è accesa
     */
    public boolean isAccesa() { return accesa; }

    /** @return le ore totali di utilizzo */
    public double getOreUtilizzo() { return oreUtilizzo; }

    /* ===== SETTER (void, valori non validi ignorati) ===== */

    /**
     * Cambia la marca, solo se non è vuota.
     *
     * @param marca la nuova marca
     */
    public void setMarca(String marca) {
        if (marca != null && !marca.isEmpty()) {
            this.marca = marca;
        }
    }

    /**
     * Cambia la potenza, solo se è tra 1 e 200 W.
     *
     * @param potenzaWatt la nuova potenza in watt
     */
    public void setPotenzaWatt(int potenzaWatt) {
        if (potenzaWatt >= 1 && potenzaWatt <= 200) {
            this.potenzaWatt = potenzaWatt;
        }
    }

    /* ===== COMPORTAMENTO ===== */

    /** Accende la lampadina. Non serve controllo: accenderla due volte non è un errore. */
    public void accendi() {
        accesa = true;
    }

    /** Spegne la lampadina. */
    public void spegni() {
        accesa = false;
    }

    /**
     * Registra un periodo di utilizzo. Si può usare solo se la lampadina è accesa.
     *
     * @param ore le ore di utilizzo da aggiungere (maggiori di 0)
     * @return true se le ore sono state aggiunte, false se la lampadina è spenta o ore non è valido
     */
    public boolean usa(double ore) {
        if (!accesa || ore <= 0) {   // ! = NOT: "se NON è accesa oppure ore non valide"
            return false;
        }
        oreUtilizzo = oreUtilizzo + ore;
        return true;
    }

    /**
     * Calcola il consumo totale. 1 kWh = 1000 W consumati per un'ora.
     *
     * @return il consumo in kilowattora
     */
    public double consumoKWh() {
        return potenzaWatt * oreUtilizzo / 1000;   // oreUtilizzo è double: niente divisione intera
    }

    /* ===== OUTPUT ===== */

    /** Stampa i dati della lampadina, con ACCESA/SPENTA al posto di true/false. */
    public void visualizza() {
        String stato;
        if (accesa) {
            stato = "ACCESA";
        } else {
            stato = "SPENTA";
        }
        System.out.println("--- Lampadina " + marca + " ---");
        System.out.println("Potenza: " + potenzaWatt + " W | Stato: " + stato);
        System.out.printf("Ore di utilizzo: %.1f | Consumo: %.3f kWh%n", oreUtilizzo, consumoKWh());
    }
}
