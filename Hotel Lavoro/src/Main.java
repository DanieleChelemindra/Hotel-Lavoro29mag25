import dao.CameraFileDAO;
import repository.CameraRepositoryImpl;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CameraRepositoryImpl
                repository = new CameraRepositoryImpl(new CameraFileDAO());
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        String[] menuOpzioni = {
                "1. Aggiungi camera",
                "2. Visualizza camere",
                "3. Cambia stato camera",
                "4. Reset file camere",
                "5. Carica camere da file",
                "6. Esci"
        };

        while (!exit) {
            System.out.println("\n--- Menu Hotel ------------");
            for (String opzione : menuOpzioni) {
                System.out.println(opzione);
            }

            String scelta = scanner.nextLine();

            switch (scelta) {
                case "1":
                    repository.aggiungiCameraDaInput();
                    break;
                case "2":
                    repository.visualizzaCamere();
                    break;
                case "3":
                    repository.cambiaStatoCamera();
                    break;
                case "4":
                    repository.resetCamere();
                    break;
                case "5":
                    repository.caricaCamereDaFile();
                    break;
                case "6":
                    exit = true;
                    break;
                default:
                    System.out.println("scelta non valida.");
            }
        }

        scanner.close();
    }
}
