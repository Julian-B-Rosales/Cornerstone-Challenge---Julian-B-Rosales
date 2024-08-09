package com.cornerstone.challenge.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.cornerstone.challenge.entities.Empresas;
import com.cornerstone.challenge.services.Interfaces.IEmpresaService;
import com.fasterxml.jackson.databind.JsonMappingException;

@SpringBootTest
@AutoConfigureMockMvc
public class TestEmpresaController {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private IEmpresaService empresaService;

    @Test
public void testOK_XMLtoXLSX() throws Exception {
    MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/api/challenge/XMLtoXLSX")
            .contentType(MediaType.APPLICATION_XML)
            .content("<Test><Test>")
            .accept(MediaType.APPLICATION_JSON);

    Empresas empresas = new Empresas();
    when(empresaService.leerDatos(anyString())).thenReturn(empresas);
    when(empresaService.convertirAXLSX(empresas)).thenReturn(new ByteArrayOutputStream());

    mockMvc.perform(request)
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")));
}

    @Test
    public void testKO_XMLtoXLSX_InvalidXML() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/api/challenge/XMLtoXLSX")
                .contentType(MediaType.APPLICATION_XML)
                .content("<invalid-xml></invalid-xml>")
                .accept(MediaType.APPLICATION_JSON);

        when(empresaService.leerDatos(anyString())).thenThrow(new JsonMappingException(null ,"Error de mapeo"));

        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void testKO_XMLtoXLSX_InternalServerError() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/api/challenge/XMLtoXLSX")
        .contentType(MediaType.APPLICATION_XML)
        .content("<xml>contenido del xml</xml>")
        .accept(MediaType.APPLICATION_JSON);

        when(empresaService.convertirAXLSX(new Empresas())).thenThrow(new IOException());

        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isInternalServerError());
    }
}
