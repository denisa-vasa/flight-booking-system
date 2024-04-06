package com.gisdev.crmshm.export.Flight;

import com.gisdev.crmshm.entity.Flight;
import com.gisdev.crmshm.util.excel.CellStyleBuilder;
import com.gisdev.crmshm.util.excel.ExcelDocument;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class FlightExportFile {
    public static ByteArrayInputStream generateExcelFile(List<Flight> flightList) throws IOException {

        ExcelDocument excelDocument = new ExcelDocument();
        XSSFWorkbook workbook = excelDocument.getWorkbook();
        XSSFSheet sheet = excelDocument.createSheet("Flights");
        sheet.setDisplayGridlines(true);

        ByteArrayOutputStream fileOutput = new ByteArrayOutputStream();

        CellStyle headerStyle = CellStyleBuilder.get(workbook)
                .withForegroundColor(IndexedColors.BLACK.index)
                .withHorizontalAlignment(HorizontalAlignment.CENTER)
                .withVerticalAlignment(VerticalAlignment.BOTTOM)
                .withBold(true)
                .withWrapText(true)
                .withAllBorders(BorderStyle.THIN)
                .withFontName("Arial Black")
                .withFontSize((short) 11.0)
                .withItalic(false)
                .withBold(true)
                .build();

        CellStyle bodyStyle = CellStyleBuilder.get(workbook)
                .withForegroundColor(IndexedColors.BLACK.index)
                .withHorizontalAlignment(HorizontalAlignment.CENTER)
                .withVerticalAlignment(VerticalAlignment.BOTTOM)
                .withWrapText(true)
                .withAllBorders(BorderStyle.THIN)
                .build();

        int counter = 4;
        for (int c = 0; c <= counter; c++) {
            sheet.setColumnWidth(c, 6000);
        }

        Row headerRow = sheet.createRow(0);
        excelDocument.createCell("Departure", headerRow, 0, headerStyle);
        excelDocument.createCell("Departure Time", headerRow, 1, headerStyle);
        excelDocument.createCell("Destination", headerRow, 2, headerStyle);
        excelDocument.createCell("Class", headerRow, 3, headerStyle);

        int rowCount = 0;
        for (Flight flight : flightList) {
            Row row = sheet.createRow(++rowCount);
            excelDocument.createCell(flight.getDeparture(), row, 0, bodyStyle);
            excelDocument.createCell(String.valueOf(flight.getDepartureTime()), row, 1, bodyStyle);
            excelDocument.createCell(flight.getDestination(), row, 2, bodyStyle);
            excelDocument.createCell(String.valueOf(flight.getTravelClass()), row, 3, bodyStyle);
            int colCount = 4;
            for (int i = 0; i <= flightList.size(); i++) {
                Cell cell = excelDocument.createCell("", row, colCount, bodyStyle);
                cell.setBlank();
                colCount++;
            }
        }

        workbook.write(fileOutput);
        return new ByteArrayInputStream(fileOutput.toByteArray());
    }
}
