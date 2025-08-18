package com.example.crud_on_excel.service;

import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class FileService {

    public String readExcelFile(MultipartFile multipartFile) throws IOException {

        String contentType = multipartFile.getContentType();
        String data = "";

        if (contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
                || contentType.equals("application/vnd.ms-excel")) {

            try (Workbook workbook = WorkbookFactory.create(multipartFile.getInputStream())) {
                Sheet sheet = workbook.getSheetAt(0); // first sheet
                StringBuilder sb = new StringBuilder();

                for (Row row : sheet) {

                    System.out.println("row : " + row);
                    System.out.println("row tostring : " + row.toString());
                    System.out.println("firstcell num : " + row.getFirstCellNum());
                    System.out.println("cell : " + row.getCell(0));

                    System.out.println("---------------");
                    //sb.append(row.getSheet());

//                    for (Cell cell : row) {
//                        switch (cell.getCellType()) {
//                            case STRING:
//                                sb.append(cell.getStringCellValue()).append(" ");
//                                break;
//                            case NUMERIC:
//                                sb.append(cell.getNumericCellValue()).append(" ");
//                                break;
//                            case BOOLEAN:
//                                sb.append(cell.getBooleanCellValue()).append(" ");
//                                break;
//                            default:
//                                sb.append(" ");
//                        }
//                    }
                    sb.append("\n");
                }

                System.out.println("data : " + sb);
                data = data + (sb.toString());
            }
        }
        System.out.println("Final data : " + data);
        return data;
    }
}
