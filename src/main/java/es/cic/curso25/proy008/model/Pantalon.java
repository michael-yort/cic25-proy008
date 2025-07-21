package es.cic.curso25.proy008.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Pantalon {

    private Long id;

    @Column(nullable = false, length = 50)
    private String marca;

    @Column(nullable = false, length = 20)
    private String color;

    @Column(nullable = false)
    private int talla;

    private boolean planchado = true;

    public Pantalon() {
    }

    public Pantalon(Long id, String marca, String color, boolean planchado) {
        this.id = id;
        this.marca = marca;
        this.color = color;
        this.planchado = planchado;
    }

    @Id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return marca;
    }

    public void setTipo(String tipo) {
        this.marca = tipo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isPlanchado() {
        return planchado;
    }

    public void setPlanchado(boolean planchado) {
        this.planchado = planchado;
    }

}
