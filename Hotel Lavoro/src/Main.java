    import dao.CameraFileDAO;
    import repository.CameraRepositoryImpl;
    import java.util.Scanner;

    public class Main {
        public static void main(String[] args) {

            // crea un oggetto repository usando il file dao
            CameraRepositoryImpl repository = new CameraRepositoryImpl(new CameraFileDAO());

            // oggetto scanner per leggere da tastiera
            Scanner scanner = new Scanner(System.in);

            // variabile per controllare se uscire dal ciclo
            boolean exit = false;

            // array con le tutte l opzioni del menu
            String[] menuOpzioni = {
                    "1. aggiungi camera",
                    "2. visualizza camere",
                    "3. cambia stato camera",
                    "4. reset file camere",
                    "5. carica camere da file",
                    "6. esci"
            };

            // ciclo principale finché l utente non sceglie di uscire
            while (!exit) {

                // stampa il menu
                System.out.println("\n--- menu hotel ------------");
                for (String opzione : menuOpzioni) {
                    System.out.println(opzione);
                }

                // legge la scelta del utente
                String scelta = scanner.nextLine();

                // esegue un azione in base alla scelta usando uno switch
                switch (scelta) {
                    case "1":
                        repository.aggiungiCameraDaInput(); // aggiunge una nuova camera
                        break;
                    case "2":
                        repository.visualizzaCamere(); // mostra tutte le camere
                        break;
                    case "3":
                        repository.cambiaStatoCamera(); // cambia lo stato di una camera
                        break;
                    case "4":
                        repository.resetCamere(); // resetta il file delle camere
                        break;
                    case "5":
                        repository.caricaCamereDaFile(); // carica le camere dal file
                        break;
                    case "6":
                        exit = true; // esce dal programma
                        break;
                    default:
                        System.out.println("scelta non valida"); // messaggio per scelta sbagliata
                }
            }

            // chiude lo scanner
            scanner.close();
        }
    }
