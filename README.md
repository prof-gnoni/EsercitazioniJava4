# EsercitazioniJava4 – Classi e oggetti, stringhe, eccezioni

Progetto IntelliJ IDEA unico: ogni esercizio sta nel suo package, con la classe di supporto e il suo `Main`.
La numerazione dei package segue i capitoli del libro "Java in laboratorio" e indica l'ordine in cui aprirli.

## Come aprirlo
1. Scompatta lo zip.
2. IntelliJ IDEA → File → Open → seleziona la cartella `EsercitazioniJava4`.
3. Se IntelliJ chiede l'SDK: File → Project Structure → Project → SDK (va bene qualsiasi JDK dal 17 in su).

## Come leggere la numerazione
- `cap1_`, `cap2_`, `cap3_` = il capitolo (1 Classi e oggetti, 2 Stringhe, 3 Eccezioni).
- `p1_`, `p2_`, ... = l'ordine dentro il capitolo.
- `es1_`, `es2_`, ... = il numero dell'esercizio, lo stesso usato nel documento della lezione.

Per eseguire un programma ci sono due modi:
- **Menu di avvio in alto a destra** (accanto al triangolo verde): le 18 configurazioni sono già pronte,
  numerate da 01 a 18 e raggruppate per capitolo. Si sceglie la successiva e si preme Run.
- **Tasto destro** sul `Main` (o sulla classe di esempio) del package → Run.

## Ordine di lettura

| N. | Configurazione di avvio | Package | Argomento |
|---|---|---|---|
| 01 | Lezione - Libro | `cap1_classi.p1_lezione_libro` | Lezione: classe `Libro` e main completo |
| 02 | Esercizio 1 - Lampadina | `cap1_classi.p2_es1_lampadina` | Esercizio base |
| 03 | Esercizio 2 - Termostato | `cap1_classi.p3_es2_termostato` | Esercizio intermedio, menu con switch |
| 04 | Esercizio 3 - Parcheggio | `cap1_classi.p4_es3_parcheggio` | Esercizio avanzato, overload di `entra` ed `esci` |
| 05 | Teoria - Esempi sulle stringhe | `cap2_stringhe.p1_teoria` | `EsempiStringhe`: tutti i metodi visti a lezione |
| 06 | Esercizio 1 - Messaggio | `cap2_stringhe.p2_es1_messaggio` | Vocali, parole, palindromo, censura |
| 07 | Esercizio 2 - Password | `cap2_stringhe.p3_es2_password` | Regole, sicurezza, maschera |
| 08 | Esercizio 3 - Studente ed email | `cap2_stringhe.p4_es3_email` | Email e username, overload di `generaEmail` |
| 09 | Teoria - Esempio 1 Crash | `cap3_eccezioni.p1_teoria` | Le prime eccezioni e lo stack trace |
| 10 | Teoria - Esempio 2 try catch finally | `cap3_eccezioni.p1_teoria` | Gestire le eccezioni |
| 11 | Teoria - Esempio 3 Input sicuro | `cap3_eccezioni.p1_teoria` | Chiedere un numero finché è valido |
| 12 | Teoria - Esempio 4 Propagazione | `cap3_eccezioni.p1_teoria` | L'eccezione risale i metodi |
| 13 | Libro con eccezioni | `cap3_eccezioni.p2_libro` | I setter lanciano `IllegalArgumentException` |
| 14 | ContoCorrente ed eccezione personalizzata | `cap3_eccezioni.p3_conto` | `SaldoInsufficienteException`, checked |
| 15 | Esercizio 1 - Tastiera | `cap3_eccezioni.p4_es1_tastiera` | Classe `Tastiera` per l'input sicuro |
| 16 | Esercizio 2 - Termostato con eccezioni | `cap3_eccezioni.p5_es2_termostato` | Riusa `Tastiera` |
| 17 | Esercizio 3 - Parcheggio con eccezioni | `cap3_eccezioni.p6_es3_parcheggio` | `ParcheggioPienoException` |
| 18 | Esercizio 4 - Voti (sfida) | `cap3_eccezioni.p7_es4_voti` | Stringhe + eccezioni |

## Dove sono le spiegazioni
- **Nel codice**: ogni file è commentato.
  - `/** ... */` (Javadoc) sopra ogni classe e ogni metodo: a cosa serve, parametri (`@param`),
    valore restituito (`@return`), eccezioni lanciate (`@throws`). In IntelliJ si leggono
    anche passando il mouse sul nome del metodo (o con Ctrl+Q).
  - `/* ... */` per le spiegazioni più lunghe e per dividere la classe in sezioni.
  - `//` per le singole righe chiave.
- **Nel libro "Java in laboratorio"** (o nel documento della lezione): la teoria completa,
  con tabelle, errori tipici, esercizi e checklist.

## Regole seguite in tutto il progetto
- Classi sempre in un package, attributi `private`, due costruttori in overload.
- Setter `void`. Prima delle eccezioni: un valore non valido viene ignorato. Dopo: il setter lancia `IllegalArgumentException`.
- Nelle classi di supporto stampa solo `visualizza()`; i messaggi per l'utente li stampa il `main`.
  Unica eccezione voluta: `Tastiera`, il cui compito è proprio dialogare con l'utente.
