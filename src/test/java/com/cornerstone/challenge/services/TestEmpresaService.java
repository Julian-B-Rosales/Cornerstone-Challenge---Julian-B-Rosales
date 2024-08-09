package com.cornerstone.challenge.services;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cornerstone.challenge.entities.Empresa;
import com.cornerstone.challenge.entities.Empresas;
import com.cornerstone.challenge.entities.Movimiento;
import com.fasterxml.jackson.core.JsonProcessingException;

@ExtendWith(MockitoExtension.class)
public class TestEmpresaService {

    @InjectMocks
    private EmpresaService empresaService;

    @Test
    public void testLeerDatos_Vacio() throws JsonProcessingException {
        try {
            empresaService.leerDatos("");
        } catch (Exception e) {
            assertEquals("No hay datos de empresas validas en el archivo", e.getMessage());
        }
    }

    @Test
    public void testLeerDatos_UnElemento() {
        try {
            Empresas empresas = empresaService.leerDatos("""
                <Empresas>\r
                  <Empresa>\r
                    <NroContrato>1</NroContrato>\r
                    <CUIT>111111111111</CUIT>\r
                    <Denominacion>Test 1</Denominacion>\r
                    <Domicilio>Test Domicilio 1</Domicilio>\r
                    <CodigoPostal>1111</CodigoPostal>\r
                    <FechaDesdeNov>2021-01-13T00:00:00</FechaDesdeNov>\r
                    <FechaHastaNov>2021-01-14T00:00:00</FechaHastaNov>\r
                    <Organizador>1111</Organizador>\r
                    <Productor>11111</Productor>\r
                    <CIIU>111111</CIIU>\r
                    <Movimientos>\r
                      <Movimiento>\r
                \t    <SaldoCtaCte>-21802.12</SaldoCtaCte>\r
                        <Tipo>C</Tipo>\r
                        <CodigoMovimiento>1</CodigoMovimiento>\r
                        <Concepto>Pago</Concepto>\r
                        <Importe>21802.19</Importe>\r
                      </Movimiento>\r
                    </Movimientos>\r
                  </Empresa>\r
                  </Empresas>""");

                  assertEquals(1, empresas.getEmpresaLista().size());
                  assertEquals(Empresa.class, empresas.getEmpresaLista().get(0).getClass());

        } catch (Exception e) {
            e.getMessage();
        }
    }

    @Test
    public void testLeerDatos_VariosElementos() {
        try {
            Empresas empresas = empresaService.leerDatos("""
                <Empresas>\r
                  <Empresa>\r
                    <NroContrato>1</NroContrato>\r
                    <CUIT>111111111111</CUIT>\r
                    <Denominacion>Test 1</Denominacion>\r
                    <Domicilio>Test Domicilio 1</Domicilio>\r
                    <CodigoPostal>1111</CodigoPostal>\r
                    <FechaDesdeNov>2021-01-13T00:00:00</FechaDesdeNov>\r
                    <FechaHastaNov>2021-01-14T00:00:00</FechaHastaNov>\r
                    <Organizador>1111</Organizador>\r
                    <Productor>11111</Productor>\r
                    <CIIU>111111</CIIU>\r
                    <Movimientos>\r
                      <Movimiento>\r
                \t    <SaldoCtaCte>-21802.12</SaldoCtaCte>\r
                        <Tipo>C</Tipo>\r
                        <CodigoMovimiento>1</CodigoMovimiento>\r
                        <Concepto>Pago</Concepto>\r
                        <Importe>21802.19</Importe>\r
                      </Movimiento>\r
                    </Movimientos>\r
                  </Empresa>\r
                  <Empresa>\r
                    <NroContrato>2</NroContrato>\r
                    <CUIT>222222222222</CUIT>\r
                    <Denominacion>Test 2</Denominacion>\r
                    <Domicilio>Test Domicilio 2</Domicilio>\r
                    <CodigoPostal>2222</CodigoPostal>\r
                    <FechaDesdeNov>2022-02-23T00:00:00</FechaDesdeNov>\r
                    <FechaHastaNov>2022-02-24T00:00:00</FechaHastaNov>\r
                    <Organizador>2222</Organizador>\r
                    <Productor>22222</Productor>\r
                    <CIIU>222222</CIIU>\r
                    <Movimientos>\r
                      <Movimiento>\r
                \t    <SaldoCtaCte>445500.12</SaldoCtaCte>\r
                        <Tipo>C</Tipo>\r
                        <CodigoMovimiento>1</CodigoMovimiento>\r
                        <Concepto>Pago</Concepto>\r
                        <Importe>21802.19</Importe>\r
                      </Movimiento>\r
                    </Movimientos>\r
                  </Empresa>\r
                  <Empresa>\r
                    <NroContrato>3</NroContrato>\r
                    <CUIT>333333333333</CUIT>\r
                    <Denominacion>Test 3</Denominacion>\r
                    <Domicilio>Test Domicilio 3</Domicilio>\r
                    <CodigoPostal>3333</CodigoPostal>\r
                    <FechaDesdeNov>2023-03-33T00:00:00</FechaDesdeNov>\r
                    <FechaHastaNov>2023-03-34T00:00:00</FechaHastaNov>\r
                    <Organizador>3333</Organizador>\r
                    <Productor>33333</Productor>\r
                    <CIIU>333333</CIIU>\r
                    <Movimientos>\r
                      <Movimiento>\r
                \t    <SaldoCtaCte>-23802.12</SaldoCtaCte>\r
                        <Tipo>C</Tipo>\r
                        <CodigoMovimiento>1</CodigoMovimiento>\r
                        <Concepto>Pago</Concepto>\r
                        <Importe>55802.19</Importe>\r
                      </Movimiento>\r
                    </Movimientos>\r
                  </Empresa>\r
                  </Empresas>""");

                  assertEquals(3, empresas.getEmpresaLista().size());
                  assertEquals(Empresa.class, empresas.getEmpresaLista().get(0).getClass());
                  assertEquals(Empresa.class, empresas.getEmpresaLista().get(1).getClass());
                  assertEquals(Empresa.class, empresas.getEmpresaLista().get(2).getClass());

        } catch (Exception e) {
            e.getMessage();
        }
    }

     @Test
    public void testConvertirAXLSX() throws IOException{
        Empresas empresas = new Empresas();
        Empresa empresa1 = new Empresa();
        empresa1.setNroContrato(1);
        empresa1.setCUIT("12345678");
        empresa1.setDenominacion("Empresa 1");
        empresa1.setDomicilio("Direccion 1");
        empresa1.setCodigoPostal(1000);
        empresa1.setProductor("Productor 1");

        List<Movimiento> movimientos1 = new ArrayList<>(); 
        Movimiento movimiento1 = new Movimiento();
        movimiento1.setNroContrato(1);
        movimiento1.setSaldoCtaCte(100.0);
        movimiento1.setConcepto("Concepto 1");
        movimiento1.setImporte(50.0);
        movimientos1.add(movimiento1);

        empresa1.setMovimientos(movimientos1);

        Empresa empresa2 = new Empresa();
        empresa2.setNroContrato(2);
        empresa2.setCUIT("23456789");
        empresa2.setDenominacion("Empresa 2");
        empresa2.setDomicilio("Direccion 2");
        empresa2.setCodigoPostal(2000);
        empresa2.setProductor("Productor 2");

        List<Movimiento> movimientos2 = new ArrayList<>();
        Movimiento movimiento2 = new Movimiento();
        movimiento2.setNroContrato(2);
        movimiento2.setSaldoCtaCte(200.0);
        movimiento2.setConcepto("Concepto 2");
        movimiento2.setImporte(100.0);
        movimientos2.add(movimiento2);

        empresa2.setMovimientos(movimientos2);

        List<Empresa> empresaLista = new ArrayList<>();
        empresaLista.add(empresa1);
        empresaLista.add(empresa2);
        empresas.setEmpresaLista(empresaLista);

        try {
            ByteArrayOutputStream outputStream = empresaService.convertirAXLSX(empresas);
        
            assertNotNull(outputStream);
            assertTrue(outputStream.size() > 0);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testConvertirAXLSX_Vacio() throws IOException {
        try {
            Empresas empresas = new Empresas();
            empresas.setEmpresaLista(new ArrayList<>());
            ByteArrayOutputStream outputStream = empresaService.convertirAXLSX(empresas);
            assertNull(outputStream);
        } catch (Exception e) {
            assertEquals("No hay empresas en la lista", e.getMessage());
        }
    }

    @Test
    public void testConvertirAXLSX_Null() {
        try {
            empresaService.convertirAXLSX(null);
            fail("Se esperaba una excepción");
        } catch (Exception e) {
            // Ok, se lanzó la excepción
        }
    }
}