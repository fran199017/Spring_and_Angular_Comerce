package com.francisconicolau.entity;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@Entity
@Table(name = "detalle_producto")
public class DetalleProducto {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)  //To add prices h2 database
    int id;

    private String material;

    private float peso;

    private float cost;

    private int impuesto;

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

    @Override
    public String toString() {
        return "DetalleProducto{" +
                "id=" + id +
                ", material='" + material + '\'' +
                ", peso=" + peso +
                ", cost=" + cost +
                ", impuesto=" + impuesto +
                '}';
    }
}
