package es.cic.curso25.proy008.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Propietario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nombre;
    private int talla;
    private int peso;

    @JsonIgnore
    @OneToOne(mappedBy = "propietario", cascade = CascadeType.REMOVE)
    private Pantalon pantalon;

    public Propietario() {
    }

    public Propietario(String nombre, int talla, int peso) {

        this.nombre = nombre;
        this.talla = talla;
        this.peso = peso;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTalla() {
        return talla;
    }

    public void setTalla(int talla) {
        this.talla = talla;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public Pantalon getPantalon() {
        return pantalon;
    }

    public void setPantalon(Pantalon pantalon) {
        this.pantalon = pantalon;
    }

    @Override
    public String toString() {
        return "Propietario [id=" + id + ", nombre=" + nombre + ", talla=" + talla + ", peso=" + peso + ", pantalon="
                + pantalon + "]";
    }

    

}
