package com.misiontic.futbolinms.models;

import org.springframework.data.annotation.Id;
import java.util.Date;
import java.util.List;

public class Transaction {
    @Id
    private String id;
    private String cuentaOrigen;
    private String cuentaDestino;
    private Integer dinero;
    private String equipo;
    private Date date;

    public Transaction(String id, String cuentaOrigen, String cuentaDestino, Integer dinero, String equipo, Date date) {
        this.id = id;
        this.cuentaOrigen = cuentaOrigen;
        this.cuentaDestino = cuentaDestino;
        this.dinero = dinero;
        this.equipo = equipo;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCuentaOrigen() {
        return cuentaOrigen;
    }

    public void setCuentaOrigen(String cuentaOrigen) {
        this.cuentaOrigen = cuentaOrigen;
    }

    public String getCuentaDestino() {
        return cuentaDestino;
    }

    public void setCuentaDestino(String cuentaDestino) {
        this.cuentaDestino = cuentaDestino;
    }

    public Integer getDinero() {
        return dinero;
    }

    public void setDinero(Integer dinero) {
        this.dinero = dinero;
    }

    public String getEquipo() {
        return equipo;
    }

    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
