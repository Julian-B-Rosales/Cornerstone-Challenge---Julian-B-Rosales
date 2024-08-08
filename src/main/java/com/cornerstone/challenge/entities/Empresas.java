package com.cornerstone.challenge.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Empresas {
    
    @JacksonXmlElementWrapper(useWrapping=false)
    private List<Empresa> empresaLista;

    public Empresas() {
    }

    public Empresas(List<Empresa> empresaLista) {
        this.empresaLista = empresaLista;
    }

    @JsonProperty("Empresa")
    public List<Empresa> getEmpresaLista() {
        return empresaLista;
    }

    public void setEmpresaLista(List<Empresa> empresaLista) {
        this.empresaLista = empresaLista;
    }

}
