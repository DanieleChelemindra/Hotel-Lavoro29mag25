package dao;

import model.Camera;

import java.io.*;
import java.util.Vector;

public class CameraFileDAO implements CameraDAO {
    private final String filePath = "camere.dat";

    @Override
    public void aggiungiCamera(Camera camera) {
        Vector<Camera> camere = trovaTutte();
        camere.add(camera);
        salvaTutte(camere);
    }

    @Override
    public Camera trovaCameraPerNumero(int numero) {
        for (Camera c : trovaTutte()) {
            if (c.getNumero() == numero) return c;
        }
        return null;
    }

    @Override
    public Vector<Camera> trovaTutte() {
        Vector<Camera> camere = new Vector<>();
        File file = new File(filePath);
        if (!file.exists()) return camere;

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            camere = (Vector<Camera>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Errore nella lettura del file: " + e.getMessage());
        }

        return camere;
    }

    @Override
    public void salvaTutte(Vector<Camera> camere) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filePath))) {
            out.writeObject(camere);
        } catch (IOException e) {
            System.out.println("Errore nella scrittura del file: " + e.getMessage());
        }
    }

    public void resetFile() {
        try {
            new FileOutputStream(filePath).close();
            System.out.println("file camere.dat resettato.");
        } catch (IOException e) {
            System.out.println("Errore nel reset del file: " + e.getMessage());
        }
    }
}
