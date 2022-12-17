package com.francisconicolau.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "descuento")
public class Descuento {

    @Id
    private int id;


    private int descuento;

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
                ", descuento=" + descuento +
                '}';
    }
}