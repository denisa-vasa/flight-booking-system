package com.gisdev.crmshm.imports;

import com.gisdev.crmshm.dataType.TravelClass;
import com.gisdev.crmshm.entity.Flight;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ImportFlights {
    public static List<Flight> importFlights(MultipartFile file) throws IOException {
        List<Flight> flights = new ArrayList<>();

        try (InputStream inputStream = file.getInputStream()) {
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheetAt(0);

            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                if (row.getRowNum() == 0) {
                    //Skip the header row
                    continue;
                }

                Flight flight = createFlightFromRow(row);
                flights.add(flight);
            }
        }
        return flights;
    }

    private static Flight createFlightFromRow(Row row) {
        Flight flight = new Flight();
        flight.setDeparture(row.getCell(0).getStringCellValue());
        flight.setDepartureTime(LocalDateTime.parse(row.getCell(1).getStringCellValue()));
        flight.setDestination(row.getCell(2).getStringCellValue());
        flight.setTravelClass(TravelClass.valueOf(row.getCell(3).getStringCellValue()));
        return flight;
    }
}
