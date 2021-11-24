package com.misiontic.futbolinms.models;

import org.springframework.data.annotation.Id;

import java.util.List;

//import java.io.Serializable;


public class Sala {
    @Id
    private String id;
    private String imagen;
    private String usernameAs;
    private String equipo1;
    private String equipo2;
    private String titulo;
    private List<String> equipoA;
    private List<String> equipoB;
    private Integer marcador1;
    private Integer marcador2;
    private Integer cuenta;
    private String tarifa;

    public Sala(String id,String imagen, String usernameAs,String equipo1,String equipo2, String titulo, List<String> equipoA, List<String> equipoB, Integer marcador1, Integer marcador2, Integer cuenta, String tarifa) {
        this.id = id;
        this.imagen= imagen;
        this.usernameAs = usernameAs;
        this.equipo1 = equipo1;
        this.equipo2 = equipo2;
        this.titulo = titulo;
        this.equipoA = equipoA;
        this.equipoB = equipoB;
        this.marcador1 = marcador1;
        this.marcador2 = marcador2;
        this.cuenta = cuenta;
        this.tarifa = tarifa;
    }

    public String getId() {
        return id;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsernameAs() {
        return usernameAs;
    }

    public void setUsernameAs(String usernameAs) {
        this.usernameAs = usernameAs;
    }

    public String getEquipo1() {
        return equipo1;
    }

    public void setEquipo1(String equipo1) {
        this.equipo1 = equipo1;
    }

    public String getEquipo2() {
        return equipo2;
    }

    public void setEquipo2(String equipo2) {
        this.equipo2 = equipo2;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<String> getEquipoA() {
        return equipoA;
    }

    public void setEquipoA(List<String> equipoA) {
        this.equipoA = equipoA;
    }

    public List<String> getEquipoB() {
        return equipoB;
    }

    public void setEquipoB(List<String> equipoB) {
        this.equipoB = equipoB;
    }

    public Integer getMarcador1() {
        return marcador1;
    }

    public void setMarcador1(Integer marcador1) {
        this.marcador1 = marcador1;
    }

    public Integer getMarcador2() {
        return marcador2;
    }

    public void setMarcador2(Integer marcador2) {
        this.marcador2 = marcador2;
    }

    public Integer getCuenta() {
        return cuenta;
    }

    public void setCuenta(Integer cuenta) {
        this.cuenta = cuenta;
    }

    public String getTarifa() {
        return tarifa;
    }

    public void setTarifa(String tarifa) {
        this.tarifa = tarifa;
    }

}
