package es.cic.curso25.proy008.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Pantalon {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

   
    private String marca;

   
    private String color;

   
    private int talla;

 
    private boolean planchado = true;

    public Pantalon() {
    }

    public Pantalon(String marca, String color, int talla, boolean planchado) {
        this.marca = marca;
        this.color = color;
        this.talla = talla;
        this.planchado = planchado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getTalla() {
        return talla;
    }

    public void setTalla(int talla) {
        this.talla = talla;
    }

    public boolean isPlanchado() {
        return planchado;
    }

    public void setPlanchado(boolean planchado) {
        this.planchado = planchado;
    }

}
