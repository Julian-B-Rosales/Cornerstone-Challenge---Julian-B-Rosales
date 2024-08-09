package com.cornerstone.challenge.services.Interfaces;

import java.io.ByteArrayOutputStream;

import com.cornerstone.challenge.entities.Empresas;

public interface IEmpresaService {
    public Empresas leerDatos(String xmlString) throws Exception;
    public ByteArrayOutputStream convertirAXLSX(Empresas empresas) throws Exception;
}
