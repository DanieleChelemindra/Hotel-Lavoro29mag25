package repository;

import model.Camera;
import java.util.Vector;

public interface CameraRepository {

    void salva(Camera camera);

    Camera trovaPerNumero(int numero);

    Vector<Camera> trovaTutte();
}
