package repository;

import dao.CameraFileDAO;
import model.Camera;

import java.util.Scanner;
import java.util.Vector;

public class CameraRepositoryImpl implements CameraRepository {
    private CameraFileDAO cameraDAO;
    private Scanner scanner;

    public CameraRepositoryImpl(CameraFileDAO cameraDAO) {
        this.cameraDAO = cameraDAO;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void salva(Camera camera) {
        cameraDAO.aggiungiCamera(camera);
    }

    @Override
    public Camera trovaPerNumero(int numero) {
        return cameraDAO.trovaCameraPerNumero(numero);
    }

    @Override
    public Vector<Camera> trovaTutte() {
        return cameraDAO.trovaTutte();
    }

    public void aggiungiCameraDaInput() {
        System.out.print("Numero camera: ");
        int numero = Integer.parseInt(scanner.nextLine());

        if (trovaPerNumero(numero) != null) {
            System.out.println("️camera già esistente.");
            return;
        }

        System.out.print("Tipo camera: ");
        String tipo = scanner.nextLine();
        System.out.print("Prezzo camera: ");
        double prezzo = Double.parseDouble(scanner.nextLine());

        Camera camera = new Camera(numero, tipo, prezzo);
        salva(camera);
        System.out.println("camera salvata: " + camera);
    }

    public void visualizzaCamere() {
        Vector<Camera> camere = trovaTutte();
        if (camere.isEmpty()) {
            System.out.println("Nessuna Camera trovata.");
        } else {
            for (Camera c : camere) {
                System.out.println(c);
            }
        }
    }

    public void cambiaStatoCamera() {
        System.out.print("Numero camera da modificare: ");
        int numero = Integer.parseInt(scanner.nextLine());

        Vector<Camera> camere = trovaTutte();
        for (int i = 0; i < camere.size(); i++) {
            if (camere.get(i).getNumero() == numero) {
                Camera camera = camere.get(i);
                camera.setOccupata(!camera.isOccupata());
                camere.set(i, camera);
                cameraDAO.salvaTutte(camere);
                System.out.println("Stato aggiornato.");
                return;
            }
        }

        System.out.println("Camera non trovata.");
    }

    public void resetCamere() {
        cameraDAO.resetFile();
    }

    public void caricaCamereDaFile() {
        Vector<Camera> camere = trovaTutte();
        System.out.println("Camere caricate da file:");
        for (Camera c : camere) {
            System.out.println(c);
        }
    }
}
