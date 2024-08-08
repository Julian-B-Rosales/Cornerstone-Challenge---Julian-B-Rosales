package com.cornerstone.challenge.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonRootName(value="Movimientos")
public class Movimiento {
    
    private int NroContrato;
    private Double SaldoCtaCte;
    private String Concepto;
    private Double Importe;

    public Movimiento() {
    }

    public Movimiento(int NroContrato, Double SaldoCtaCte, String Concepto, Double Importe) {
        this.NroContrato = NroContrato;
        this.SaldoCtaCte = SaldoCtaCte;
        this.Concepto = Concepto;
        this.Importe = Importe;
    }
    
    @JsonProperty(value="NroContrato")
    public int getNroContrato() {
        return NroContrato;
    }

    public void setNroContrato(int nroContrato) {
        NroContrato = nroContrato;
    }

    @JsonProperty(value="SaldoCtaCte")
    public Double getSaldoCtaCte() {
        return SaldoCtaCte;
    }

    public void setSaldoCtaCte(Double saldoCtaCte) {
        SaldoCtaCte = saldoCtaCte;
    }

    @JsonProperty(value="Concepto")
    public String getConcepto() {
        return Concepto;
    }

    public void setConcepto(String concepto) {
        Concepto = concepto;
    }

    @JsonProperty(value="Importe")
    public Double getImporte() {
        return Importe;
    }

    public void setImporte(Double importe) {
        Importe = importe;
    }


}
