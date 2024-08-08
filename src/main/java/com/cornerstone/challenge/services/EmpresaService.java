package com.cornerstone.challenge.services;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.cornerstone.challenge.entities.Empresa;
import com.cornerstone.challenge.entities.Empresas;
import com.cornerstone.challenge.entities.Movimiento;
import com.cornerstone.challenge.services.Interfaces.IEmpresaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

@Service
public class EmpresaService implements IEmpresaService{

    @Override
    public Empresas leerDatos(String xmlString) throws JsonMappingException, JsonProcessingException {
        
        XmlMapper xmlMapper = new XmlMapper();

        Empresas empresas = xmlMapper.readValue(xmlString, Empresas.class);

        for (Empresa empresa : empresas.getEmpresaLista()) {
            int NroContrato = empresa.getNroContrato();

            List<Movimiento> movimientos = empresa.getMovimientos();
            for (int i = 0; i < movimientos.size(); i++) {
                movimientos.get(i).setNroContrato(NroContrato);
            }
        }

        return empresas;
    }

    @Override
    public ByteArrayOutputStream convertirAXLSX(Empresas empresas) throws IOException {

        List<Empresa> listaEmpresas = empresas.getEmpresaLista();

        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream file = new ByteArrayOutputStream()) {
            Sheet hojaEmpresas = workbook.createSheet("Empresas");
            Row headerEmpresas = hojaEmpresas.createRow(0);
            headerEmpresas.createCell(0).setCellValue("NroContrato");
            headerEmpresas.createCell(1).setCellValue("CUIT");
            headerEmpresas.createCell(2).setCellValue("DENOMINACION");
            headerEmpresas.createCell(3).setCellValue("DOMICILIO");
            headerEmpresas.createCell(4).setCellValue("CODIGOPOSTAL");
            headerEmpresas.createCell(5).setCellValue("PRODUCTOR");

            Sheet hojaMovimientos = workbook.createSheet("Movimientos");
            Row headerMovimientos = hojaMovimientos.createRow(0);
            headerMovimientos.createCell(0).setCellValue("NroContrato");
            headerMovimientos.createCell(1).setCellValue("SaldoCtaCte");
            headerMovimientos.createCell(2).setCellValue("Concepto");
            headerMovimientos.createCell(3).setCellValue("Importe");

            int movRow = 1;

            for (int i = 0; i < listaEmpresas.size(); i++) {
                
                Row filaIEmpresas = hojaEmpresas.createRow(i+1);

                filaIEmpresas.createCell(0).setCellValue(listaEmpresas.get(i).getNroContrato());
                filaIEmpresas.createCell(1).setCellValue(listaEmpresas.get(i).getCUIT());
                filaIEmpresas.createCell(2).setCellValue(listaEmpresas.get(i).getDenominacion());
                filaIEmpresas.createCell(3).setCellValue(listaEmpresas.get(i).getDomicilio());
                filaIEmpresas.createCell(4).setCellValue(listaEmpresas.get(i).getCodigoPostal());
                filaIEmpresas.createCell(5).setCellValue(listaEmpresas.get(i).getProductor());

                List<Movimiento> movimientos = listaEmpresas.get(i).getMovimientos();

                for (int j = 0; j < movimientos.size(); j++) {
                    Row filaIMovimientos = hojaMovimientos.createRow(movRow);
                    filaIMovimientos.createCell(0).setCellValue(movimientos.get(j).getNroContrato());
                    filaIMovimientos.createCell(1).setCellValue(movimientos.get(j).getSaldoCtaCte());
                    filaIMovimientos.createCell(2).setCellValue(movimientos.get(j).getConcepto());
                    filaIMovimientos.createCell(3).setCellValue(movimientos.get(j).getImporte());

                    movRow++;
                }
            }

            workbook.write(file);
            workbook.close();

            return file;
        }
        
    }

}
