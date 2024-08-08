package com.cornerstone.challenge.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonRootName(value="Empresa")
public class Empresa {

    private int NroContrato;
    private String CUIT;
    private String Denominacion;
    private String Domicilio;
    private int CodigoPostal;
    private String Productor;
    private List<Movimiento> Movimientos; 

    public Empresa() {
    }

    public Empresa(int NroContrato, String CUIT, String Denominacion, String Domicilio, int CodigoPostal, String Productor, List<Movimiento> Movimientos) {
        this.NroContrato = NroContrato;
        this.CUIT = CUIT;
        this.Denominacion = Denominacion;
        this.Domicilio = Domicilio;
        this.CodigoPostal = CodigoPostal;
        this.Productor = Productor;
        this.Movimientos = Movimientos;
    }

    @JsonProperty(value="NroContrato")
    public int getNroContrato() {
        return NroContrato;
    }

    public void setNroContrato(int nroContrato) {
        NroContrato = nroContrato;
    }

    @JsonProperty(value="CUIT")
    public String getCUIT() {
        return CUIT;
    }

    public void setCUIT(String cUIT) {
        CUIT = cUIT;
    }

    @JsonProperty(value="Denominacion")
    public String getDenominacion() {
        return Denominacion;
    }

    public void setDenominacion(String denominacion) {
        Denominacion = denominacion;
    }

    @JsonProperty(value="Domicilio")
    public String getDomicilio() {
        return Domicilio;
    }

    public void setDomicilio(String domicilio) {
        Domicilio = domicilio;
    }

    @JsonProperty(value="CodigoPostal")
    public int getCodigoPostal() {
        return CodigoPostal;
    }

    public void setCodigoPostal(int codigoPostal) {
        CodigoPostal = codigoPostal;
    }

    @JsonProperty(value="Productor")
    public String getProductor() {
        return Productor;
    }

    public void setProductor(String productor) {
        Productor = productor;
    }

    @JsonProperty(value="Movimientos")
    public List<Movimiento> getMovimientos() {
        return Movimientos;
    }

    public void setMovimientos(List<Movimiento> Movimientos) {
        this.Movimientos = Movimientos;
    }
    
    
    
}
