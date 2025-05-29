package repository;

import model.Camera;
import java.util.Vector;

// Interfaccia che definisce le operazioni base per lavorare con le camere
// Le classi che la implementano devono fornire il codice per questi metodi
public interface CameraRepository {
    void aggiungiCameraDaInput();
    void visualizzaCamere();
    void cambiaStatoCamera();
    void resetCamere();
    void caricaCamereDaFile();
    Vector<Camera> trovaTutte();
    Camera trovaPerNumero(int numero);
    void salva(Camera camera);
}
