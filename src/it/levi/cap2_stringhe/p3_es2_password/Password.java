package it.levi.cap2_stringhe.p3_es2_password;

/**
 * Esercizio stringhe 2 (intermedio): la password di un utente.
 *
 * <p>Regole di una password valida: almeno 8 caratteri, almeno una lettera maiuscola,
 * una minuscola e una cifra, nessuno spazio.</p>
 *
 * <p>Novità: i metodi {@code private} di aiuto. Servono solo dentro la classe,
 * quindi dall'esterno (dal main) non si possono chiamare.</p>
 */
public class Password {

    /* ===== ATTRIBUTI ===== */
    private String utente;
    private String valore;   // la password vera e propria

    /* ===== COSTRUTTORI ===== */

    /** Costruttore vuoto: utente "ospite" con password provvisoria "Cambiami1". */
    public Password() {
        utente = "ospite";
        valore = "Cambiami1";
    }

    /**
     * Costruttore con parametri. Se la password ricevuta non rispetta le regole,
     * resta quella provvisoria (il setter la ignora).
     *
     * @param utente il nome utente
     * @param valore la password scelta
     */
    public Password(String utente, String valore) {
        this.utente = "ospite";
        this.valore = "Cambiami1";   // valore di sicurezza
        setUtente(utente);
        setValore(valore);           // accettato solo se rispetta le regole
    }

    /* ===== GETTER ===== */

    /** @return il nome utente */
    public String getUtente() { return utente; }

    /* Volutamente NON esiste getValore(): una password non si legge dall'esterno,
       si VERIFICA con il metodo verifica(). È un altro esempio di incapsulamento. */

    /* ===== SETTER ===== */

    /**
     * Cambia il nome utente, salvandolo in minuscolo e senza spazi ai bordi.
     *
     * @param utente il nuovo nome utente
     */
    public void setUtente(String utente) {
        if (utente != null && !utente.trim().isEmpty()) {
            this.utente = utente.trim().toLowerCase();
        }
    }

    /**
     * Cambia la password, solo se rispetta tutte le regole.
     *
     * @param nuova la nuova password
     */
    public void setValore(String nuova) {
        if (nuova != null && rispettaRegole(nuova)) {
            valore = nuova;
        }
    }

    /* ===== METODI PUBBLICI ===== */

    /**
     * Controlla se il tentativo coincide con la password.
     * Si usa equals: == confronterebbe gli indirizzi degli oggetti.
     *
     * @param tentativo la password scritta dall'utente
     * @return true se è corretta
     */
    public boolean verifica(String tentativo) {
        return valore.equals(tentativo);
    }

    /**
     * Valuta la sicurezza con un punteggio: un punto per ogni requisito
     * (almeno 8 caratteri, almeno 12, una maiuscola, una cifra, un carattere speciale).
     *
     * @return "DEBOLE" (0-2 punti), "MEDIA" (3-4) o "FORTE" (5)
     */
    public String livelloSicurezza() {
        int punti = 0;
        if (valore.length() >= 8) {
            punti++;
        }
        if (valore.length() >= 12) {
            punti++;
        }
        if (haMaiuscola(valore)) {
            punti++;
        }
        if (haCifra(valore)) {
            punti++;
        }
        if (haSpeciale(valore)) {
            punti++;
        }
        if (punti <= 2) {
            return "DEBOLE";
        } else if (punti <= 4) {
            return "MEDIA";
        } else {
            return "FORTE";
        }
    }

    /**
     * Mostra solo la prima e l'ultima lettera, il resto diventa asterischi.
     * Esempio: "Cambiami1" diventa "C*******1".
     *
     * @return la password mascherata
     */
    public String mascherata() {
        int n = valore.length();
        // char + String = String: la concatenazione funziona anche partendo da un char
        return valore.charAt(0) + "*".repeat(n - 2) + valore.charAt(n - 1);
    }

    /* ===== OUTPUT ===== */

    /** Stampa utente, password mascherata (mai in chiaro!) e livello di sicurezza. */
    public void visualizza() {
        System.out.println("Utente: " + utente + " | Password: " + mascherata()
                + " | Sicurezza: " + livelloSicurezza());
    }

    /* =====================================================================
     * METODI DI AIUTO PRIVATI
     * Ricevono la stringa come parametro, così si possono usare sia sulla
     * password attuale (valore) sia su una password candidata (nel setter).
     * Tutti usano lo stesso schema: scorro i caratteri e appena trovo
     * quello che cerco restituisco true; se arrivo in fondo, false.
     * ===================================================================== */

    /** @return true se s rispetta tutte le regole di una password valida */
    private boolean rispettaRegole(String s) {
        return s.length() >= 8 && haMaiuscola(s) && haMinuscola(s) && haCifra(s) && !s.contains(" ");
    }

    /** @return true se s contiene almeno una lettera maiuscola */
    private boolean haMaiuscola(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (Character.isUpperCase(s.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    /** @return true se s contiene almeno una lettera minuscola */
    private boolean haMinuscola(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (Character.isLowerCase(s.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    /** @return true se s contiene almeno una cifra */
    private boolean haCifra(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    /** @return true se s contiene almeno un carattere che non è né lettera né cifra (es. ! - @) */
    private boolean haSpeciale(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (!Character.isLetterOrDigit(s.charAt(i))) {
                return true;
            }
        }
        return false;
    }
}
