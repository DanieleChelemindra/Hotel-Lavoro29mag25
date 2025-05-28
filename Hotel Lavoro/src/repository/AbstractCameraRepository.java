package repository;

import dao.CameraFileDAO;
import model.Camera;
import java.util.Vector;

public abstract class AbstractCameraRepository implements CameraRepository {
    protected CameraFileDAO cameraDAO;

    public AbstractCameraRepository(CameraFileDAO cameraDAO) {
        this.cameraDAO = cameraDAO;
    }

    @Override
    public Vector<Camera> trovaTutte() {
        return cameraDAO.trovaTutte();
    }

    @Override
    public Camera trovaPerNumero(int numero) {
        return cameraDAO.trovaCameraPerNumero(numero);
    }

    @Override
    public void salva(Camera camera) {
        cameraDAO.aggiungiCamera(camera);
    }
}
