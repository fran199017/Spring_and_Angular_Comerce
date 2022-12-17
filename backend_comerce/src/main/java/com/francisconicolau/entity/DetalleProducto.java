package com.francisconicolau.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;

@Entity
@Table(name = "detalle_producto")
@JsonSerialize
public class DetalleProducto {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)  //To add prices h2 database
    int id;

    private String material;

    private float peso;

    private float cost;

    private int impuesto;

    @ManyToOne
    @JoinColumn(name = "OFERTA_ID")
    private Descuento descuento;

    public void setOferta(Descuento descuento) {
        this.descuento = descuento;
    }

    public Descuento getOferta() {
        return descuento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public int getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(int impuesto) {
        this.impuesto = impuesto;
    }

    public Descuento getDescuento() {
        return descuento;
    }

    public void setDescuento(Descuento descuento) {
        this.descuento = descuento;
    }

    @Override
    public String toString() {
        return "DetalleProducto{" +
                "id=" + id +
                ", material='" + material + '\'' +
                ", peso=" + peso +
                ", cost=" + cost +
                ", impuesto=" + impuesto +
                ", descuento=" + descuento +
                '}';
    }
}
