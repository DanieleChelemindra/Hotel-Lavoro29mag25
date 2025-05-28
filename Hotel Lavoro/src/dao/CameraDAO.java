package dao;

import model.Camera;
import java.util.Vector;

public interface CameraDAO {
    void aggiungiCamera(Camera camera);
    Camera trovaCameraPerNumero(int numero);
    Vector<Camera> trovaTutte();
    void salvaTutte(Vector<Camera> camere);
}
