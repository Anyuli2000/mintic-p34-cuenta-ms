package com.misiontic.futbolinms.models;

import org.springframework.data.annotation.Id;
import java.util.List;
import java.util.Date;

public class Account {

    @Id
    private String username;
    private String perfil;
    private Integer balance;
    private Date lastChange;
    private List<String> apuestasExitosas;
    private List<String> apuestasPerdidas;
    private Integer inversion;
    private Integer activo;
    private Integer activoNeto;
    private Integer pasivo;
    private Integer ultimaApuesta;
    private Integer apuestasGanadas;
    private String nivel;
    public Account(String username,String perfil, Integer balance, Date lastChange,List<String> apuestasExitosas, List<String> apuestasPerdidas, Integer inversion,Integer activo, Integer activoNeto,Integer pasivo,Integer ultimaApuesta,Integer apuestasGanadas ,String nivel){
        this.username = username;
        this.perfil = perfil;
        this.balance = balance;
        this.lastChange = lastChange;
        this.apuestasExitosas = apuestasExitosas;
        this.apuestasPerdidas = apuestasPerdidas;
        this.inversion = inversion;
        this.activo = activo;
        this.activoNeto = activoNeto;
        this.pasivo = pasivo;
        this.ultimaApuesta = ultimaApuesta;
        this.apuestasGanadas= apuestasGanadas;
        this.nivel = nivel;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }
    public Date getLastChange() {
        return lastChange;
    }
    public void setLastChange(Date lastChange) {
        this.lastChange = lastChange;
    }

    public List<String> getApuestasExitosas() {
        return apuestasExitosas;
    }

    public void setApuestasExitosas(List<String> apuestasExitosas) {
        this.apuestasExitosas = apuestasExitosas;
    }

    public List<String> getApuestasPerdidas() {
        return apuestasPerdidas;
    }

    public void setApuestasPerdidas(List<String> apuestasPerdidas) {
        this.apuestasPerdidas = apuestasPerdidas;
    }

    public Integer getInversion() {
        return inversion;
    }

    public void setInversion(Integer inversion) {
        this.inversion = inversion;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }

    public Integer getActivoNeto() {
        return activoNeto;
    }

    public void setActivoNeto(Integer activoNeto) {
        this.activoNeto = activoNeto;
    }

    public Integer getPasivo() {
        return pasivo;
    }

    public void setPasivo(Integer pasivo) {
        this.pasivo = pasivo;
    }

    public Integer getUltimaApuesta() {
        return ultimaApuesta;
    }

    public void setUltimaApuesta(Integer ultimaApuesta) {
        this.ultimaApuesta = ultimaApuesta;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public Integer getApuestasGanadas() {
        return apuestasGanadas;
    }

    public void setApuestasGanadas(Integer apuestasGanadas) {
        this.apuestasGanadas = apuestasGanadas;
    }
}
