package com.example.marcela.parcial2.helper;

import java.io.Serializable;

/**
 * Created by Marcela on 15/05/2018.
 */

public class Clientes implements Serializable {

    private Integer noId;
    private String Nombre;
    private String Apellidos;
    private String Direccion;
    private Integer Telefono;

    public Clientes(){

    }

    public Clientes(Integer noId, String Nombre, String Apellidos, String Direccion, Integer Telefono){
        this.noId = noId;
        this.Nombre = Nombre;
        this.Apellidos = Apellidos;
        this.Direccion = Direccion;
        this.Telefono = Telefono;
    }


    public Integer getNoId() {
        return noId;
    }

    public void setNoId(Integer noId) {
        this.noId = noId;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        this.Nombre = nombre;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String apellidos) {
        this.Apellidos = apellidos;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String direccion) {
        this.Direccion = direccion;
    }

    public Integer getTelefono() {
        return Telefono;
    }

    public void setTelefono(Integer telefono) {
        this.Telefono = telefono;
    }
}
