package com.example.tabula.service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import technology.tabula.*;
import technology.tabula.extractors.SpreadsheetExtractionAlgorithm;

import java.io.IOException;
import java.util.List;

@Service
public class TabulaService {

    public void extractTableUsingTabula(MultipartFile multipartFile){

        PDDocument pdDocument;
        try {
             pdDocument = PDDocument.load(multipartFile.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ObjectExtractor extractor = new ObjectExtractor(pdDocument);
        SpreadsheetExtractionAlgorithm sea = new SpreadsheetExtractionAlgorithm();

        PageIterator it = extractor.extract();
        while (it.hasNext()) {
            Page page = it.next();
            List<Table> tables = sea.extract(page);
            for (Table table : tables) {
                for (List<RectangularTextContainer> row : table.getRows()) {
                    for (RectangularTextContainer cell : row) {
                        System.out.print(cell.getText() + " | ");
                    }
                    System.out.println();
                }
            }
        }
    }
}
