package com.claujulian.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "editorial")
public class Editorial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "alta")
    private Boolean alta;

    public Editorial() {
        this.alta = true;
    }

    public Editorial(String nombre, Boolean alta) {
        this.nombre = nombre;
        this.alta = alta;
    }

    @Override
    public String toString() {
        return String.format(
                "Editorial:\n"
                + "  ID: %d\n"
                + "  Nombre: %s\n"
                + "  Alta: %b\n",
                id, nombre, alta
        );
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getAlta() {
        return alta;
    }

    public void setAlta(Boolean alta) {
        this.alta = alta;
    }

}
