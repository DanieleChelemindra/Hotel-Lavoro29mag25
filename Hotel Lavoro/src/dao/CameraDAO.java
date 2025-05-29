package dao;

import model.Camera;
import java.util.Vector;

// Interfaccia che definisce le operazioni per lavorare con i dati delle camere
// Le classi che la implementano si occuperanno di salvare, cercare e caricare le camere
public interface CameraDAO {

    // Aggiunge una nuova camera ai dati esistenti
    void aggiungiCamera(Camera camera);

    // Cerca una camera tramite il numero
    Camera trovaCameraPerNumero(int numero);

    // Restituisce tutte le camere salvate
    Vector<Camera> trovaTutte();

    // Salva tutte le camere nel file (sovrascrivendo)
    void salvaTutte(Vector<Camera> camere);
}
