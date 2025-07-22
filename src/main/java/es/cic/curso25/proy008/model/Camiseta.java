package es.cic.curso25.proy008.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Camiseta {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private int talla;

    @Column(nullable = false, length = 20)
    private String color;

    @Column(nullable = false, length = 50)
    private String marca;

    @Column(nullable = false)
    private boolean lavada;

    public Camiseta() {
    }

    public Camiseta(long id, int talla, String color, String marca, boolean lavada) {
        this.id = id;
        this.talla = talla;
        this.color = color;
        this.marca = marca;
        this.lavada = lavada;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getTalla() {
        return talla;
    }

    public void setTalla(int talla) {
        this.talla = talla;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public boolean isLavada() {
        return lavada;
    }

    public void setLavada(boolean lavada) {
        this.lavada = lavada;
    }
}