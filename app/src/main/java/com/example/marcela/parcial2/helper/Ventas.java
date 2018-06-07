package com.example.marcela.parcial2.helper;

import android.content.Intent;

import java.io.Serializable;

/**
 * Created by Marcela on 15/05/2018.
 */

public class Ventas implements Serializable {

    private Integer codVenta;
    private Integer codCliente;
    private String TipoServicio;
    private String Fecha;
    private Integer Total;

    public Ventas(){

    }

    public Ventas(Integer codVenta, Integer codCliente, String TipoServicio, String Fecha, Integer Total){
        this.codVenta = codVenta;
        this.codCliente = codCliente;
        this.TipoServicio = TipoServicio;
        this.Fecha = Fecha;
        this.Total = Total;
    }

    public Integer getCodVenta() {
        return codVenta;
    }

    public void setCodVenta(Integer codVenta) {
        this.codVenta = codVenta;
    }

    public Integer getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(Integer codCliente) {
        this.codCliente = codCliente;
    }

    public String getTipoServicio() {
        return TipoServicio;
    }

    public void setTipoServicio(String tipoServicio) {
        this.TipoServicio = tipoServicio;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        this.Fecha = fecha;
    }

    public Integer getTotal() {
        return Total;
    }

    public void setTotal(Integer total) {
        this.Total = total;
    }
}
