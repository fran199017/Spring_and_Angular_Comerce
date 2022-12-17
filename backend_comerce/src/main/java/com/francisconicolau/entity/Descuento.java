package com.francisconicolau.entity;

import javax.persistence.*;

@Entity
@Table(name = "descuento")
public class Descuento {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)  //To add prices h2 database
    private int id;

    private String nombre;


    private int descuento;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDescuento() {
        return descuento;
    }

    public void setDescuento(int descuento) {
        this.descuento = descuento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Descuento{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descuento=" + descuento +
                '}';
    }
}