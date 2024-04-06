package com.gisdev.crmshm.imports;

import com.gisdev.crmshm.entity.User;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ImportUsers {
    public static List<User> importUsers(MultipartFile file) throws IOException {
        List<User> users = new ArrayList<>();

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

                User user = createUserFromRow(row);
                users.add(user);
            }
        }
        return users;
    }

    private static User createUserFromRow(Row row) {
        User user = new User();
        user.setUsername(row.getCell(4).getStringCellValue());
        user.setRole(row.getCell(5).getStringCellValue());
        user.setPassword(row.getCell(6).getStringCellValue());
        return user;
    }
}
