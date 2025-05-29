package repository;

import dao.CameraFileDAO;
import model.Camera;
import java.util.Vector;

// Classe astratta che implementa una parte comune del repository per le camere
public abstract class AbstractCameraRepository implements CameraRepository {

    // Riferimento al DAO, usato per accedere al file
    protected CameraFileDAO cameraDAO;

    // Costruttore: riceve il DAO per poterlo usare nei metodi comuni
    public AbstractCameraRepository(CameraFileDAO cameraDAO) {
        this.cameraDAO = cameraDAO;
    }

    // Metodo per ottenere tutte le camere dal DAO (cioè dal file)
    @Override
    public Vector<Camera> trovaTutte() {
        return cameraDAO.trovaTutte();
    }

    // Metodo per cercare una camera per numero, usando il DAO
    @Override
    public Camera trovaPerNumero(int numero) {
        return cameraDAO.trovaCameraPerNumero(numero);
    }

    // Metodo per salvare una nuova camera, usando il DAO
    @Override
    public void salva(Camera camera) {
        cameraDAO.aggiungiCamera(camera);
    }
}
