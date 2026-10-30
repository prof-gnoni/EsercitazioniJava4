package it.levi.cap2_stringhe.p1_teoria;

/**
 * Raccolta degli esempi della teoria sulle stringhe.
 *
 * <p>Eseguilo e confronta l'output con i commenti a destra di ogni riga.
 * Una String è un OGGETTO: per questo si usano i suoi metodi con il punto,
 * come per le classi che scriviamo noi ({@code s.length()}, {@code s.charAt(0)}).</p>
 */
public class EsempiStringhe {

    /**
     * Punto di partenza del programma.
     *
     * @param args non usati
     */
    public static void main(String[] args) {

        /* ------------------------------------------------------------------
         * 1) LUNGHEZZA E CARATTERI
         *    Le posizioni partono da 0: l'ultimo carattere è in length() - 1.
         * ------------------------------------------------------------------ */
        String s = "Informatica";
        System.out.println(s.length());                 // 11
        System.out.println(s.charAt(0));                // I
        System.out.println(s.charAt(s.length() - 1));   // a (ultimo carattere)

        /* ------------------------------------------------------------------
         * 2) SOTTOSTRINGHE
         *    substring(inizio, fine): la posizione "fine" è ESCLUSA.
         *    substring(inizio): dalla posizione indicata fino alla fine.
         * ------------------------------------------------------------------ */
        System.out.println(s.substring(0, 5));   // Infor (posizioni 0,1,2,3,4)
        System.out.println(s.substring(5));      // matica

        /* ------------------------------------------------------------------
         * 3) RICERCA
         *    indexOf restituisce la posizione, oppure -1 se non trova nulla.
         * ------------------------------------------------------------------ */
        System.out.println(s.indexOf("for"));      // 2
        System.out.println(s.indexOf("xyz"));      // -1 (non trovato)
        System.out.println(s.contains("mat"));     // true
        System.out.println(s.startsWith("Info"));  // true
        System.out.println(s.endsWith("ca"));      // true

        /* ------------------------------------------------------------------
         * 4) IMMUTABILITÀ
         *    Nessun metodo modifica la stringa: ne restituisce una NUOVA.
         *    Se non la salviamo in una variabile, il risultato si perde.
         * ------------------------------------------------------------------ */
        String nome = "  Mario Rossi  ";
        nome.toUpperCase();                       // ERRORE tipico: il risultato si perde!
        System.out.println("[" + nome + "]");     // [  Mario Rossi  ]
        nome = nome.trim().toUpperCase();         // GIUSTO: si riassegna (e si possono concatenare i metodi)
        System.out.println("[" + nome + "]");     // [MARIO ROSSI]

        /* ------------------------------------------------------------------
         * 5) CONFRONTO: equals, MAI ==
         *    == confronta gli indirizzi (sono lo stesso oggetto?),
         *    equals confronta il contenuto (hanno gli stessi caratteri?).
         * ------------------------------------------------------------------ */
        String a = "ciao";
        String b = new String("ciao");                     // un oggetto diverso con lo stesso testo
        System.out.println(a == b);                        // false
        System.out.println(a.equals(b));                   // true
        System.out.println("CIAO".equalsIgnoreCase(a));    // true: ignora maiuscole/minuscole
        System.out.println("albero".compareTo("banana"));  // negativo: "albero" viene prima in ordine alfabetico

        /* ------------------------------------------------------------------
         * 6) SOSTITUIRE E SPEZZARE
         *    split restituisce un array di stringhe.
         * ------------------------------------------------------------------ */
        String frase = "il gatto e il cane";
        System.out.println(frase.replace("il", "un"));    // un gatto e un cane (sostituisce TUTTE le occorrenze)
        String[] parole = frase.split(" ");
        System.out.println(parole.length);                // 5
        System.out.println(parole[1]);                    // gatto

        /* ------------------------------------------------------------------
         * 7) SCORRERE UNA STRINGA CARATTERE PER CARATTERE
         *    Lo schema più usato negli esercizi: for + charAt.
         *    La classe Character ha i metodi per riconoscere i caratteri.
         * ------------------------------------------------------------------ */
        String parola = "Java 21!";
        int lettere = 0;
        int cifre = 0;
        for (int i = 0; i < parola.length(); i++) {   // i < length(), NON <=
            char c = parola.charAt(i);                // char: un singolo carattere, tra apici ' '
            if (Character.isLetter(c)) {
                lettere++;
            } else if (Character.isDigit(c)) {
                cifre++;
            }
        }
        System.out.println("Lettere: " + lettere + ", cifre: " + cifre);   // Lettere: 4, cifre: 2

        /* ------------------------------------------------------------------
         * 8) DA STRINGA A NUMERO E VICEVERSA
         *    Attenzione al +: con una stringa concatena, tra numeri somma.
         * ------------------------------------------------------------------ */
        int n = Integer.parseInt("42");          // "42" -> 42
        double d = Double.parseDouble("3.5");    // "3.5" -> 3.5
        String t = String.valueOf(n + 1);        // 43 -> "43"
        System.out.println(n + d);               // 45.5 (somma tra numeri)
        System.out.println(t + 1);               // 431  (concatenazione!)
        // Integer.parseInt("dodici") lancerebbe NumberFormatException: vedi le eccezioni

        /* ------------------------------------------------------------------
         * 9) STRINGA VUOTA E STRINGA DI SOLI SPAZI
         * ------------------------------------------------------------------ */
        String vuota = "";
        String spazi = "   ";
        System.out.println(vuota.isEmpty());     // true
        System.out.println(spazi.isEmpty());     // false: contiene 3 caratteri
        System.out.println(spazi.isBlank());     // true: solo spazi (Java 11+)
    }
}
