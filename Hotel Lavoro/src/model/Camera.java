package model;

import java.io.Serializable;

public class Camera implements Serializable {
    private int numero;
    private String tipo;
    private double prezzo;
    private boolean occupata;




    public Camera(int numero, String tipo, double prezzo) {
        this.numero = numero;
        this.tipo = tipo;
        this.prezzo = prezzo;
        this.occupata = false;
    }


    public int getNumero() { return numero; }

    public String getTipo() { return tipo; }

    public double getPrezzo() { return prezzo; }

    public boolean isOccupata() { return occupata; }

    public void setOccupata(boolean occupata) { this.occupata = occupata; }

    @Override
    public String toString() {
        return "Camera{" +
                "numero=" + numero +
                ", tipo='" + tipo + '\'' +
                ", prezzo=" + prezzo +
                ", occupata=" + (occupata ? "sì" : "no") +
                '}';


    }
}
