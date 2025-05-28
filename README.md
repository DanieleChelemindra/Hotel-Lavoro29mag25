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

* **Abstract Class**:
    * Può avere sia **metodi astratti** (solo la dichiarazione, l'implementazione deve essere fornita dalle sottoclassi) che **metodi concreti** (con implementazione).
    * Può avere **variabili d'istanza** (campi) e **costruttori**.
    * Una classe può **estendere (inherit)** solo una classe astratta.
    * Esempio: Ci potrebbe essere una classe base `Camera` con un metodo `descrivi()` e un campo `numeroCamera`, se fosse stata progettata in questo modo.

* **Interface**:
    * Può avere solo **metodi astratti**. Tutte le implementazioni devono essere fornite dalle classi che la implementano.
    * Non può avere variabili d'istanza (solo costanti `public static final`).
    * Una classe può **implementare** **molteplici** interfacce.

Abbiamo scelto le interfacce (ICameraRepository, ICameraDAO) per rendere il codice più flessibile e facile da modificare. Così, se cambiano i requisiti, possiamo adattarci senza rompere il resto.

-----

## Confronto Teorico: DAO vs. Repository

* **DAO**:
    * **Scopo**: DAO: È il pezzo di codice che sa come salvare e recuperare i dati da dove sono conservati (come un file o un database), senza che il resto del programma debba preoccuparsene.
    * **Responsabilità**: Contiene la logica per la **persistenza effettiva** (leggere, scrivere, aggiornare, cancellare) di un singolo tipo di entità. 
    * **Metodi Tipici**: `save()`, `findById()`, `update()`, `delete()`, `findAll()`.
    * **Esempio nel codice**: `src/main/java/dao/CameraFileDAO.java`. Questo DAO si occupa di convertire oggetti `Camera` in stringhe per il file e viceversa.

* **Repository**:
    * **Scopo**: È una "raccolta" di oggetti, come se fossero in memoria.
    * **Responsabilità**: Fornisce un'interfaccia per accedere e gestire oggetti del dominio (es. `Camera`). Può usare uno o più DAO al suo interno, ma aggiunge **logica di business o coordinamento** che va oltre il CRUD (Create, Read, Update, Delete) di un DAO.
    * **Metodi Tipici**: `aggiungiCamera()`, `trovaCameraPerNumero()`, `aggiornaStatoCamera()`.
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
