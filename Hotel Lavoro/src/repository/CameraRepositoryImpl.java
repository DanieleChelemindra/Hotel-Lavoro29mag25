package repository;

import dao.CameraFileDAO;
import model.Camera;

import java.util.Scanner;
import java.util.Vector;

public class CameraRepositoryImpl extends AbstractCameraRepository {

    private Scanner scanner;

    public CameraRepositoryImpl(CameraFileDAO cameraDAO) {
        super(cameraDAO);
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void aggiungiCameraDaInput() {
        System.out.print("numero camera: ");
        int numero = Integer.parseInt(scanner.nextLine());

        if (trovaPerNumero(numero) != null) {
            System.out.println("camera già esistente.");
            return;
        }

        System.out.print("tipo camera: ");
        String tipo = scanner.nextLine();
        System.out.print("prezzo camera: ");
        double prezzo = Double.parseDouble(scanner.nextLine());

        Camera camera = new Camera(numero, tipo, prezzo);
        salva(camera);
        System.out.println("camera salvata: " + camera);
    }

    @Override
    public void visualizzaCamere() {
        Vector<Camera> camere = trovaTutte();
        if (camere.isEmpty()) {
            System.out.println("nessuna camera trovata.");
        } else {
            for (Camera c : camere) {
                System.out.println(c);
            }
        }
    }

    @Override
    public void cambiaStatoCamera() {
        System.out.print("numero camera da modificare: ");
        int numero = Integer.parseInt(scanner.nextLine());

        Vector<Camera> camere = trovaTutte();
        for (int i = 0; i < camere.size(); i++) {
            if (camere.get(i).getNumero() == numero) {
                Camera camera = camere.get(i);
                camera.setOccupata(!camera.isOccupata());
                camere.set(i, camera);
                cameraDAO.salvaTutte(camere);
                System.out.println("stato aggiornato.");
                return;
            }
        }

        System.out.println("camera non trovata.");
    }

    @Override
    public void resetCamere() {
        cameraDAO.resetFile();
        System.out.println("file camere.dat resettato.");
    }

    @Override
    public void caricaCamereDaFile() {
        Vector<Camera> camere = trovaTutte();
        System.out.println("camere caricate da file:");
        for (Camera c : camere) {
            System.out.println(c);
        }
    }
}
