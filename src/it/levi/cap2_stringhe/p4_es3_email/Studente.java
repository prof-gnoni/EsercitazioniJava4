package it.levi.cap2_stringhe.p4_es3_email;

/**
 * Esercizio stringhe 3 (avanzato): uno studente per cui generare
 * email e username a partire da nome e cognome.
 *
 * <p>Mostra la "pulizia" delle stringhe (minuscolo, niente spazi, apostrofi e accenti)
 * e l'overload di un metodo: {@code generaEmail()} e {@code generaEmail(String dominio)}.</p>
 */
public class Studente {

    /* ===== ATTRIBUTI ===== */
    private String nome;
    private String cognome;
    private String classe;   // es. "4A"

    /* ===== COSTRUTTORI ===== */

    /** Costruttore vuoto: Mario Rossi, classe 4A. */
    public Studente() {
        nome = "Mario";
        cognome = "Rossi";
        classe = "4A";
    }

    /**
     * Costruttore con parametri: valori di sicurezza, poi i setter controllano.
     *
     * @param nome    il nome (anche composto, es. "Maria Chiara")
     * @param cognome il cognome (anche con apostrofo, es. "D'Agostino")
     * @param classe  la classe, due caratteri (es. "4B")
     */
    public Studente(String nome, String cognome, String classe) {
        this.nome = "Mario";
        this.cognome = "Rossi";
        this.classe = "4A";
        setNome(nome);
        setCognome(cognome);
        setClasse(classe);
    }

    /* ===== GETTER ===== */

    /** @return il nome */
    public String getNome() { return nome; }

    /** @return il cognome */
    public String getCognome() { return cognome; }

    /** @return la classe */
    public String getClasse() { return classe; }

    /* ===== SETTER ===== */

    /**
     * Cambia il nome, solo se non è vuoto (gli spazi ai bordi vengono tolti).
     *
     * @param nome il nuovo nome
     */
    public void setNome(String nome) {
        if (nome != null && !nome.trim().isEmpty()) {
            this.nome = nome.trim();
        }
    }

    /**
     * Cambia il cognome, solo se non è vuoto.
     *
     * @param cognome il nuovo cognome
     */
    public void setCognome(String cognome) {
        if (cognome != null && !cognome.trim().isEmpty()) {
            this.cognome = cognome.trim();
        }
    }

    /**
     * Cambia la classe, solo se ha esattamente 2 caratteri; la salva in maiuscolo.
     *
     * @param classe la nuova classe (es. "4c" diventa "4C")
     */
    public void setClasse(String classe) {
        if (classe != null && classe.trim().length() == 2) {
            this.classe = classe.trim().toUpperCase();
        }
    }

    /* ===== METODI SULLE STRINGHE ===== */

    /**
     * Prima lettera del nome e del cognome, in maiuscolo.
     *
     * @return le iniziali, es. "M.R."
     */
    public String getIniziali() {
        // Character.toUpperCase lavora su un singolo char
        return Character.toUpperCase(nome.charAt(0)) + "." + Character.toUpperCase(cognome.charAt(0)) + ".";
    }

    /**
     * Nome e cognome con le iniziali maiuscole.
     *
     * @return es. "maria chiara", "d'agostino" diventano "Maria Chiara D'Agostino"
     */
    public String nomeCompleto() {
        return capitalizza(nome) + " " + capitalizza(cognome);
    }

    /* =====================================================================
     * OVERLOAD DI generaEmail
     * La versione senza parametri usa il dominio della scuola e chiama
     * l'altra versione: il codice per costruire l'email è scritto una volta sola.
     * ===================================================================== */

    /**
     * Genera l'email con il dominio predefinito.
     *
     * @return es. "mario.rossi@scuola.it"
     */
    public String generaEmail() {
        return generaEmail("scuola.it");
    }

    /**
     * Genera l'email con il dominio indicato: nome.cognome normalizzati.
     *
     * @param dominio il dominio dopo la chiocciola (es. "esempio.edu.it")
     * @return l'email, es. "mariachiara.dagostino@esempio.edu.it"
     */
    public String generaEmail(String dominio) {
        return normalizza(nome) + "." + normalizza(cognome) + "@" + dominio;
    }

    /**
     * Username: prime 5 lettere del cognome (o tutte, se sono meno),
     * più l'iniziale del nome, più la classe in minuscolo.
     *
     * @return es. "rossim4a", "dagosm4b"
     */
    public String generaUsername() {
        String c = normalizza(cognome);
        if (c.length() > 5) {
            c = c.substring(0, 5);   // posizioni da 0 a 4 (la 5 è esclusa)
        }
        return c + normalizza(nome).charAt(0) + classe.toLowerCase();
    }

    /* ===== OUTPUT ===== */

    /** Stampa nome completo, iniziali, classe, email e username. */
    public void visualizza() {
        System.out.println(nomeCompleto() + " (" + getIniziali() + ") - classe " + classe);
        System.out.println("  email:    " + generaEmail());
        System.out.println("  username: " + generaUsername());
    }

    /* =====================================================================
     * METODI DI AIUTO PRIVATI
     * ===================================================================== */

    /**
     * "Pulisce" una stringa per usarla in un'email: minuscolo,
     * senza spazi, senza apostrofi e senza lettere accentate.
     * Ogni replace restituisce una nuova stringa, che riassegno a r.
     *
     * @param s la stringa da pulire
     * @return la stringa normalizzata, es. "D'Agostino" diventa "dagostino"
     */
    private String normalizza(String s) {
        String r = s.trim().toLowerCase();
        r = r.replace(" ", "").replace("'", "");
        r = r.replace("à", "a").replace("è", "e").replace("é", "e");
        r = r.replace("ì", "i").replace("ò", "o").replace("ù", "u");
        return r;
    }

    /**
     * Mette la maiuscola all'inizio e dopo ogni spazio o apostrofo.
     * La variabile boolean "prossimaMaiuscola" ricorda se la prossima
     * lettera che incontro deve diventare maiuscola.
     *
     * @param s la stringa da sistemare
     * @return es. "d'agostino" diventa "D'Agostino"
     */
    private String capitalizza(String s) {
        String testo = s.trim().toLowerCase();
        String risultato = "";
        boolean prossimaMaiuscola = true;   // la prima lettera è sempre maiuscola
        for (int i = 0; i < testo.length(); i++) {
            char c = testo.charAt(i);
            if (prossimaMaiuscola && Character.isLetter(c)) {
                risultato = risultato + Character.toUpperCase(c);
                prossimaMaiuscola = false;
            } else {
                risultato = risultato + c;
            }
            if (c == ' ' || c == '\'') {     // '\'' è il carattere apostrofo
                prossimaMaiuscola = true;
            }
        }
        return risultato;
    }
}
