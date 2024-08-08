package com.cornerstone.challenge.services.Interfaces;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.cornerstone.challenge.entities.Empresas;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

public interface IEmpresaService {
    public Empresas leerDatos(String xmlString) throws JsonMappingException, JsonProcessingException;
    public ByteArrayOutputStream convertirAXLSX(Empresas empresas) throws IOException;
}
