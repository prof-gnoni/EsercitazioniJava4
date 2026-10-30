package it.levi.cap3_eccezioni.p7_es4_voti;

/**
 * Esercizio eccezioni 4 (sfida): stringhe + eccezioni.
 *
 * <p>I voti di una materia arrivano come un unico testo, separati da punto e virgola,
 * ad esempio {@code "7; 8,5; sei; 11; 6.5"}. I voti non numerici ("sei") o fuori
 * dalla scala 1-10 ("11") vengono scartati senza interrompere il calcolo.</p>
 *
 * <p>Idea chiave: un try/catch DENTRO il ciclo. Se un voto non è valido,
 * il catch lo salta e il ciclo prosegue con il voto successivo.</p>
 */
public class PagellaMateria {

    /* ===== ATTRIBUTI ===== */
    private String materia;
    private String votiGrezzi;   // il testo dei voti così come è stato scritto

    /* ===== COSTRUTTORI ===== */

    /** Costruttore vuoto: Informatica, nessun voto. */
    public PagellaMateria() {
        materia = "Informatica";
        votiGrezzi = "";
    }

    /**
     * Costruttore con parametri.
     *
     * @param materia    il nome della materia (non vuoto)
     * @param votiGrezzi il testo dei voti, separati da ";"
     * @throws IllegalArgumentException se la materia è vuota o il testo è null
     */
    public PagellaMateria(String materia, String votiGrezzi) {
        setMateria(materia);
        setVotiGrezzi(votiGrezzi);
    }

    /* ===== GETTER ===== */

    /** @return il nome della materia */
    public String getMateria() { return materia; }

    /** @return il testo dei voti */
    public String getVotiGrezzi() { return votiGrezzi; }

    /* ===== SETTER ===== */

    /**
     * Cambia la materia.
     *
     * @param materia il nuovo nome
     * @throws IllegalArgumentException se è vuota
     */
    public void setMateria(String materia) {
        if (materia == null || materia.trim().isEmpty()) {
            throw new IllegalArgumentException("La materia non può essere vuota");
        }
        this.materia = materia.trim();
    }

    /**
     * Cambia il testo dei voti. Il testo può contenere voti non validi:
     * verranno scartati nei calcoli.
     *
     * @param votiGrezzi il nuovo testo
     * @throws IllegalArgumentException se è null
     */
    public void setVotiGrezzi(String votiGrezzi) {
        if (votiGrezzi == null) {
            throw new IllegalArgumentException("Il testo dei voti non può essere null");
        }
        this.votiGrezzi = votiGrezzi;
    }

    /* ===== OPERAZIONI ===== */

    /**
     * Aggiunge un voto in fondo al testo, solo se è valido.
     * convertiVoto() viene chiamato solo per CONTROLLARE il voto: se non è valido
     * lancia l'eccezione, questo metodo si interrompe e l'eccezione arriva al main.
     *
     * @param voto il voto da aggiungere, es. "7,5"
     * @throws NumberFormatException    se il voto non è un numero
     * @throws IllegalArgumentException se il voto è fuori dalla scala 1-10
     */
    public void aggiungiVoto(String voto) {
        convertiVoto(voto);                       // se non è valido, l'eccezione esce da qui
        if (votiGrezzi.trim().isEmpty()) {
            votiGrezzi = voto.trim();             // primo voto: niente separatore davanti
        } else {
            votiGrezzi = votiGrezzi + "; " + voto.trim();
        }
    }

    /**
     * Conta i voti validi: spezza il testo con split(";") e prova a convertire ogni pezzo.
     *
     * @return il numero di voti validi
     */
    public int contaVotiValidi() {
        int validi = 0;
        String[] pezzi = votiGrezzi.split(";");
        for (int i = 0; i < pezzi.length; i++) {
            try {
                convertiVoto(pezzi[i]);
                validi++;                 // eseguito solo se la conversione è riuscita
            } catch (IllegalArgumentException e) {
                // voto scartato: non lo conto (il commento spiega perché il catch è "vuoto")
            }
        }
        return validi;
    }

    /**
     * Conta i voti scartati: tutti i pezzi meno quelli validi.
     *
     * @return il numero di voti non validi
     */
    public int contaScartati() {
        if (votiGrezzi.trim().isEmpty()) {
            return 0;
        }
        return votiGrezzi.split(";").length - contaVotiValidi();
    }

    /**
     * Calcola la media dei soli voti validi.
     *
     * @return la media
     * @throws IllegalStateException se non c'è nessun voto valido (non si può dividere per 0)
     */
    public double media() {
        double somma = 0;
        int validi = 0;
        String[] pezzi = votiGrezzi.split(";");
        for (int i = 0; i < pezzi.length; i++) {
            try {
                somma = somma + convertiVoto(pezzi[i]);
                validi++;
            } catch (IllegalArgumentException e) {
                // voto scartato: il ciclo continua con il pezzo successivo
            }
        }
        if (validi == 0) {
            throw new IllegalStateException("Nessun voto valido per " + materia);
        }
        return somma / validi;
    }

    /* ===== OUTPUT ===== */

    /**
     * Stampa materia, voti e statistiche. Qui è la classe stessa a catturare
     * l'eccezione di media(): visualizza() deve funzionare anche senza voti validi.
     */
    public void visualizza() {
        System.out.println("Materia: " + materia);
        System.out.println("Voti inseriti: " + votiGrezzi);
        System.out.println("Validi: " + contaVotiValidi() + " | Scartati: " + contaScartati());
        try {
            System.out.printf("Media: %.2f%n", media());
        } catch (IllegalStateException e) {
            System.out.println("Media: non calcolabile");
        }
    }

    /* ===== METODO DI AIUTO PRIVATO ===== */

    /**
     * Converte un pezzo di testo in un voto.
     *
     * <p>Può lanciare DUE eccezioni:</p>
     * <ul>
     *   <li>NumberFormatException, lanciata da Double.parseDouble se il testo non è un numero;</li>
     *   <li>IllegalArgumentException, lanciata da noi se il voto è fuori scala.</li>
     * </ul>
     * NumberFormatException è un "tipo particolare" di IllegalArgumentException:
     * per questo nei cicli sopra un solo catch (IllegalArgumentException e) le cattura entrambe.
     *
     * @param testo il pezzo di testo, es. " 8,5 "
     * @return il voto come numero, es. 8.5
     */
    private double convertiVoto(String testo) {
        double voto = Double.parseDouble(testo.trim().replace(',', '.'));
        if (voto < 1 || voto > 10) {
            throw new IllegalArgumentException("Voto fuori scala: " + voto);
        }
        return voto;
    }
}
