package com.cornerstone.challenge.controllers;

import java.io.ByteArrayOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cornerstone.challenge.entities.Empresas;
import com.cornerstone.challenge.services.Interfaces.IEmpresaService;
import com.fasterxml.jackson.databind.JsonMappingException;

@RestController
@RequestMapping("/api/challenge")
public class EmpresaController {

    @Autowired
    private IEmpresaService empresaService;

    // POST /api/challenge/XMLtoXLSX
    @PostMapping("/XMLtoXLSX")
    public ResponseEntity<byte[]> XMLtoXLSX(@RequestBody String xmlString){
        
        Empresas empresas = new Empresas();

        try {    

            empresas = empresaService.leerDatos(xmlString);
            ByteArrayOutputStream file = empresaService.convertirAXLSX(empresas);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "attachment; filename=empresas.xlsx");
            headers.add("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

            return new ResponseEntity<>(file.toByteArray(), headers, HttpStatus.OK);
        
        } catch(JsonMappingException je){
            System.err.println(je.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        } catch (Exception e) {
            System.err.println(e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
