package dao;

import model.Camera;

import java.io.*;
import java.util.Vector;

// Classe che gestisce il salvataggio e il caricamento delle camere da un file
public class CameraFileDAO implements CameraDAO {

    // Nome del file dove vengono salvate le camere
    private final String filePath = "camere.dat";

    // Aggiunge una nuova camera alla lista esistente e salva tutto nel file
    @Override
    public void aggiungiCamera(Camera camera) {
        Vector<Camera> camere = trovaTutte(); // legge tutte le camere dal file
        camere.add(camera); // aggiunge la nuova camera
        salvaTutte(camere); // riscrive tutto il file con la nuova lista
    }

    // Cerca una camera nel file tramite il numero
    @Override
    public Camera trovaCameraPerNumero(int numero) {
        for (Camera c : trovaTutte()) { // legge tutte le camere e cerca quella giusta
            if (c.getNumero() == numero) return c; // se trova la camera, la restituisce
        }
        return null; // altrimenti ritorna null
    }

    // Carica tutte le C3amere dal file
    @Override
    public Vector<Camera> trovaTutte() {
        Vector<Camera> camere = new Vector<>(); // crea un vettore vuoto
        File file = new File(filePath);
        if (!file.exists()) return camere; // se il file non esiste, ritorna il vettore vuoto

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            // legge l'oggetto salvato (che è un Vector<Camera>) e lo assegna alla variabile
            camere = (Vector<Camera>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Errore nella lettura del file: " + e.getMessage());
        }

        return camere; // restituisce tutte le camere lette
    }

    // salva tutte le camere nel file
    @Override
    public void salvaTutte(Vector<Camera> camere) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filePath))) {
            out.writeObject(camere); // scrive il vettore delle camere nel file
        } catch (IOException e) {
            System.out.println("Errore nella scrittura del file: " + e.getMessage());
        }
    }

    // cancella il contenuto del file (lo resetta)
    public void resetFile() {
        try {
            new FileOutputStream(filePath).close(); // apre il file e lo chiude subito: risultato = file vuoto
            System.out.println("file camere.dat resettato.");
        } catch (IOException e) {
            System.out.println("Errore nel reset del file: " + e.getMessage());
        }
    }
}
