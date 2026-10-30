package it.levi.cap2_stringhe.p2_es1_messaggio;

/**
 * Esercizio stringhe 1 (base): un messaggio di testo da analizzare.
 *
 * <p>Allena i metodi fondamentali delle stringhe: length, charAt, toLowerCase,
 * indexOf, contains, replace, split, repeat e il ciclo carattere per carattere.</p>
 */
public class Messaggio {

    /* ===== ATTRIBUTI ===== */
    private String mittente;
    private String testo;

    /* ===== COSTRUTTORI ===== */

    /** Costruttore vuoto: mittente "Anonimo" e testo vuoto. */
    public Messaggio() {
        mittente = "Anonimo";
        testo = "";
    }

    /**
     * Costruttore con parametri: assegna valori di sicurezza e poi usa i setter,
     * così i controlli sono scritti una volta sola.
     *
     * @param mittente chi scrive il messaggio
     * @param testo    il testo del messaggio
     */
    public Messaggio(String mittente, String testo) {
        this.mittente = "Anonimo";
        this.testo = "";
        setMittente(mittente);
        setTesto(testo);
    }

    /* ===== GETTER ===== */

    /** @return il mittente */
    public String getMittente() { return mittente; }

    /** @return il testo del messaggio */
    public String getTesto() { return testo; }

    /* ===== SETTER ===== */

    /**
     * Cambia il mittente. Toglie gli spazi iniziali e finali con trim();
     * un nome fatto di soli spazi viene ignorato.
     *
     * @param mittente il nuovo mittente
     */
    public void setMittente(String mittente) {
        if (mittente != null && !mittente.trim().isEmpty()) {
            this.mittente = mittente.trim();   // salvo la versione SENZA spazi ai bordi
        }
    }

    /**
     * Cambia il testo. Il testo vuoto è ammesso, null no.
     *
     * @param testo il nuovo testo
     */
    public void setTesto(String testo) {
        if (testo != null) {
            this.testo = testo;
        }
    }

    /* ===== METODI SULLE STRINGHE ===== */

    /** @return il numero di caratteri del testo, spazi compresi */
    public int lunghezza() {
        return testo.length();
    }

    /**
     * Conta le vocali, sia maiuscole sia minuscole.
     * Trucco: "aeiou".indexOf(c) restituisce -1 se c NON è una vocale.
     *
     * @return il numero di vocali nel testo
     */
    public int contaVocali() {
        int conta = 0;
        String minuscolo = testo.toLowerCase();   // così basta confrontare con le minuscole
        for (int i = 0; i < minuscolo.length(); i++) {
            char c = minuscolo.charAt(i);
            if ("aeiou".indexOf(c) >= 0) {
                conta++;
            }
        }
        return conta;
    }

    /**
     * Conta le parole. split("\\s+") divide il testo dove ci sono uno o più spazi,
     * così due spazi consecutivi non creano una "parola vuota".
     *
     * @return il numero di parole (0 se il testo è vuoto)
     */
    public int contaParole() {
        String pulito = testo.trim();
        if (pulito.isEmpty()) {
            return 0;   // split su una stringa vuota darebbe 1, non 0
        }
        return pulito.split("\\s+").length;
    }

    /**
     * Costruisce il testo al contrario, partendo dall'ultimo carattere.
     *
     * @return il testo invertito
     */
    public String invertito() {
        String risultato = "";
        for (int i = testo.length() - 1; i >= 0; i--) {   // ciclo all'indietro
            risultato = risultato + testo.charAt(i);
        }
        return risultato;
    }

    /**
     * Controlla se il testo si legge uguale nei due sensi, ignorando maiuscole e spazi.
     * Usa due indici: i parte dall'inizio, j dalla fine, e si incontrano a metà.
     *
     * @return true se il testo è palindromo
     */
    public boolean isPalindromo() {
        String s = testo.toLowerCase().replace(" ", "");   // "I topi..." -> "itopi..."
        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {   // i char si confrontano con != (sono primitivi)
                return false;                   // al primo carattere diverso so già la risposta
            }
            i++;
            j--;
        }
        return true;
    }

    /**
     * Cerca una parola nel testo senza distinguere maiuscole e minuscole.
     *
     * @param parola la parola da cercare
     * @return true se la parola è presente
     */
    public boolean contiene(String parola) {
        return testo.toLowerCase().contains(parola.toLowerCase());
    }

    /**
     * Sostituisce una parola con tanti asterischi quante sono le sue lettere.
     * Restituisce una NUOVA stringa: l'attributo testo NON cambia (immutabilità).
     *
     * @param parolaVietata la parola da censurare (attenzione: replace distingue le maiuscole)
     * @return il testo censurato
     */
    public String censura(String parolaVietata) {
        String stelle = "*".repeat(parolaVietata.length());   // es. "ciao" -> "****"
        return testo.replace(parolaVietata, stelle);
    }

    /* ===== OUTPUT ===== */

    /** Stampa mittente, testo e statistiche. Le virgolette nel testo si scrivono \". */
    public void visualizza() {
        System.out.println("Da: " + mittente);
        System.out.println("Testo: \"" + testo + "\"");
        System.out.println("Caratteri: " + lunghezza() + " | Parole: " + contaParole()
                + " | Vocali: " + contaVocali());
    }
}
