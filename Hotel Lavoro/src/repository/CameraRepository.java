package repository;

import model.Camera;
import java.util.Vector;

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
