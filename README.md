# Consegna

Questo progetto l'abbiamo creato con lo scopo di simulare la gestione delle camere di un hotel, permettendo operazioni come l'aggiunta, la visualizzazione, la modifica dello stato dei dati.

-----

## Descrizione del Progetto

L'utente interagisce tramite un menu, potendo:
* Aggiungere nuove camere;
* Visualizzare tutte le camere presenti;
* Cambiare lo stato di una camera (ad esempio, da "libera" a "occupata");
* Salvare e caricare i dati delle camere su file, facendo si che i dati si salvino anche dopo la chiusura del programma;
* Resettare i dati, riportando il sistema ad uno stato iniziale;

L'obiettivo era capire ed usare diverse funzionalità come le **DAO (Data Access Object)** e le **Repository**, oltre a comprendere le differenze tra **classi astratte** e **interfacce**.

-----

## Scelte Progettuali

1.  **Architettura a Livelli**: Il progetto è stato diviso in livelli logici distinti, ad esempio `main` per l'interfaccia utente, `repository` per la logica di business, `dao` per l'accesso ai dati. Questo separa le responsabilità e rende il codice più organizzato.
2.  **Utilizzo di Interfacce**: Abbiamo definito delle interfacce per i contratti (`IRepository`, `IDAO`) prima di implementare le classi.
3.  **Iniezione delle Dipendenze (Dependency Injection)**: La classe `CameraRepositoryImpl` riceve un'istanza di `CameraFileDAO` nel suo costruttore. Questo rende la `repository` meno "rigida" e più facile da testare o modificare.

---

## Confronto Teorico: Abstract Class vs. Interface

Sia le **Abstract Class** che le **Interface** sono strumenti con differenze sostanziali:

* **Abstract Class**:
    * Può avere sia **metodi astratti** (solo la dichiarazione, l'implementazione deve essere fornita dalle sottoclassi) che **metodi concreti** (con implementazione).
    * Può avere **variabili d'istanza** (campi) e **costruttori**.
    * Una classe può **estendere (inherit)** solo una classe astratta.
    * Viene usata per definire una relazione.
    * Esempio: Ci potrebbe essere una classe base `Camera` con un metodo `descrivi()` e un campo `numeroCamera`, se fosse stata progettata in questo modo.

* **Interface**:
    * Può avere solo **metodi astratti**. Tutte le implementazioni devono essere fornite dalle classi che la implementano.
    * Non può avere variabili d'istanza (solo costanti `public static final`).
    * Una classe può **implementare** **molteplici** interfacce.
    * Viene usata per definire una relazione, specificando un set di comportamenti che una classe deve garantire.

Abbiamo scelto le interfacce (ICameraRepository, ICameraDAO) per rendere il codice più flessibile e facile da modificare. Così, se cambiano i requisiti, possiamo adattarci senza rompere il resto.

-----

## Confronto Teorico: DAO vs. Repository

Queste due sono fondamentali per gestire l'accesso ai dati in un'applicazione:

* **DAO**:
    * **Scopo**: Incapsula e astrae l'accesso a una specifica fonte di dati (es. file system, database, API web).
    * **Responsabilità**: Contiene la logica per la **persistenza effettiva** (leggere, scrivere, aggiornare, cancellare) di un singolo tipo di entità. 
    * **Metodi Tipici**: `save()`, `findById()`, `update()`, `delete()`, `findAll()`.
    * **Dipendenza**: Spesso dipende direttamente da tecnologie di persistenza (es. `FileReader/FileWriter` per file).
    * **Esempio nel codice**: `src/main/java/dao/CameraFileDAO.java`. Questo DAO si occupa di convertire oggetti `Camera` in stringhe per il file e viceversa.

* **Repository**:
    * **Scopo**: È una "raccolta" di oggetti, come se fossero in memoria.
    * **Responsabilità**: Fornisce un'interfaccia per accedere e gestire oggetti del dominio (es. `Camera`). Può usare uno o più DAO al suo interno, ma aggiunge **logica di business o coordinamento** che va oltre il CRUD (Create, Read, Update, Delete) di un DAO.
    * **Metodi Tipici**: `aggiungiCamera()`, `trovaCameraPerNumero()`, `aggiornaStatoCamera()`.
    * **Dipendenza**: Dipende da uno o più DAO per le operazioni di basso livello.
    * **Esempio nel codice**: `src/main/java/repository/CameraRepositoryImpl.java`. Qui si trovano metodi come `aggiungiCameraDaInput()` che coordina l'input utente con l'operazione di salvataggio del DAO.

Nel progetto, `CameraFileDAO` gestisce l'interazione diretta con il file, invece `CameraRepositoryImpl` fornisce un'interfaccia per il `Main`, utilizzando il `DAO` al suo interno.

---

## Collegamenti al Codice

* **`src/main/java/Main.java`**: Contiene il loop principale del menu e coordina le chiamate al repository.
* **`src/main/java/model/Camera.java`**: La classe che rappresenta l'entità "Camera" (il modello di dominio).
* **`src/main/java/dao/ICameraDAO.java`**: L'interfaccia del Data Access Object.
* **`src/main/java/dao/CameraFileDAO.java`**: L'implementazione del DAO che gestisce la lettura/scrittura da file.
* **`src/main/java/repository/ICameraRepository.java`**: L'interfaccia del Repository.
* **`src/main/java/repository/CameraRepositoryImpl.java`**: L'implementazione del Repository, che usa il `CameraFileDAO`.


# Svolgimento

## Spiegazione di come è stato sviluppato il progetto

1.  **Definizione del Modello**: Abbiamo definito la classe `Camera.java`. Questa classe rappresenta una singola camera con attributi come numero, tipo e stato. (`src/main/java/model/Camera.java`).
2.  **Definizione delle Interfacce**: Abbiamo creato le interfacce `ICameraDAO.java` e `ICameraRepository.java` per definire i "contratti" (quali metodi avrebbero dovuto avere le classi che le implementavano).
3.  **Implementazione del DAO**: Abbiamo implementato `CameraFileDAO.java`. Focalizzato sulla lettura e scrittura di oggetti `Camera` da/verso un file di testo (conversione tra oggetti `Camera` e stringhe leggibili/scrivibili).
4.  **Implementazione del Repository**: Dopo aver fatto la dao, abbiamo sviluppato `CameraRepositoryImpl.java`. Questa classe usa `CameraFileDAO` per salvare o caricare i dati, (gestione delle liste di camere in memoria e l'interazione con l'utente per operazioni più complesse; es. "aggiungi camera da input").
5.  **Interfaccia Utente (Main)**: Infine, abbiamo creato la classe `Main.java`. Questa classe si occupa solo di mostrare il menu all'utente, leggere le sue scelte e chiamare i metodi su `CameraRepositoryImpl`. 

Questo approccio "a strati" ha permesso di testare e sviluppare ogni parte in modo più isolato e di concentrarsi su una singola responsabilità alla volta.

-----

## Le difficoltà affrontate

1.  **Persistenza su File**: La sfida maggiore è stata la **gestione del file di testo**.
    * **Lettura/Scrittura**: Assicurarsi che le operazioni di lettura non corrompessero il file e che la scrittura aggiungesse o modificasse correttamente le righe esistenti.
    * **Errori di I/O**: Gestire le eccezioni (`IOException`) che possono verificarsi durante le operazioni sui file, per evitare che il programma si blocchi.
2.  **Gestione delle Liste in Memoria**: Mantenere la coerenza tra la lista di `Camera` in memoria (nel `Repository`) e i dati salvati su file. Ogni modifica (aggiunta, cambio stato, reset) ha richiesto una sincronizzazione con il file.
3.  **Gestione dell'Input Utente**: Assicurarsi che l'input dell'utente fosse valido (es. numeri interi per il numero della camera, scelte valide dal menu). Ho usato cicli `do-while` e controlli `if` per richiedere l'input finché non fosse corretto.

-----

### ⚙️ Scelte implementative concrete

Ecco alcune scelte specifiche fatte durante la codifica:

* **Singleton Pattern (non esplicito, ma nell'uso del DAO)**: Sebbene non implementato con un pattern Singleton formale, `CameraFileDAO` è istanziato una sola volta in `Main.java` e passato al `Repository`. Questo garantisce che ci sia un'unica fonte per l'accesso ai file di dati delle camere.
* **Serializzazione Manuale**: Per la persistenza, ho optato per una serializzazione manuale (conversione dell'oggetto `Camera` in una stringa e viceversa) piuttosto che usare la serializzazione di Java. Questo ha reso il file più leggibile e facile da debuggare manualmente.
    * **Esempio di serializzazione `Camera` in `src/main/java/dao/CameraFileDAO.java`:**
        ```java
        // Metodo per convertire un oggetto Camera in una stringa per il file
        private String convertCameraToString(Camera camera) {
            return camera.getNumero() + "," + camera.getTipo() + "," + camera.statoToString();
        }

        // Metodo per convertire una stringa del file in un oggetto Camera
        private Camera convertStringToCamera(String line) {
            String[] parts = line.split(",");
            int numero = Integer.parseInt(parts[0]);
            String tipo = parts[1];
            Camera.Stato stato = Camera.stringToStato(parts[2]); // Conversione della stringa in enum
            return new Camera(numero, tipo, stato);
        }
        ```
* **Metodi di Convenienza in `Camera`**: Ho aggiunto metodi come `statoToString()` e `stringToStato()` nella classe `Camera` per facilitare la conversione tra l'enumerazione `Stato` e la sua rappresentazione stringa nel file. (Vedi `src/main/java/model/Camera.java`)
* **Gestione Errori User-Friendly**: Invece di far terminare il programma in caso di input non valido, ho stampato messaggi di errore chiari e ho permesso all'utente di riprovare.
    * **Esempio in `src/main/java/repository/CameraRepositoryImpl.java` (metodo `aggiungiCameraDaInput`):**
        ```java
        // ... (all'interno del metodo aggiungiCameraDaInput)
        try {
            System.out.print("Inserisci numero camera: ");
            int numero = Integer.parseInt(scanner.nextLine()); // Tenta di convertire
            // ... altro input ...
        } catch (NumberFormatException e) {
            System.out.println("❌ Errore: Il numero della camera deve essere un numero intero.");
            return; // Esci dal metodo corrente per tornare al menu
        }
        // ...
        ```
* **Reset del File**: Per la funzionalità "Reset file camere", ho semplicemente sovrascritto il file con una lista vuota o lo ho cancellato e ricreato. Questo è stato un modo semplice per "pulire" i dati. (Vedi `src/main/java/dao/CameraFileDAO.java` nel metodo `resetDataFile`)

Queste scelte hanno mirato a un buon equilibrio tra semplicità, efficacia e didattica per mostrare i concetti di programmazione.
