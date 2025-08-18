package com.example.tesseract.service;

import com.example.tesseract.entity.Aadhar;
import com.example.tesseract.preprocess.OcrPreprocessor;
import com.example.tesseract.repository.OCRRepository;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import lombok.extern.slf4j.Slf4j;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.WebClient;

import javax.imageio.ImageIO;
import java.awt.*;
//import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import static java.lang.System.out;


@Service
@Slf4j
public class OCRService {

    @Autowired
    OCRRepository ocrRepository;

    public Aadhar postAadharData(MultipartFile multipartFile) throws IOException, TesseractException {

        String contentType = multipartFile.getContentType();

        Tesseract tesseract = new Tesseract();
        tesseract.setDatapath("C:\\Program Files\\Tesseract-OCR\\tessdata");
        tesseract.setLanguage("eng+hin");

        StringBuilder data = new StringBuilder();

        if (contentType.startsWith("image/")) {

            Path imagePath = Paths.get("ocr-image-" + UUID.randomUUID().toString().substring(0,3) + multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf('.')));
            Path imagePathPreProcessed = Paths.get("pre-" + imagePath);
            //Path imagePathPreProcessed = Paths.get("ocr-image-preprocessed" + Objects.requireNonNull(multipartFile.getOriginalFilename()).substring(Objects.requireNonNull(multipartFile.getOriginalFilename()).lastIndexOf('.')));
            out.println("Printing path : " + imagePathPreProcessed);
            Files.copy(multipartFile.getInputStream(), imagePath, StandardCopyOption.REPLACE_EXISTING);

            OcrPreprocessor.preprocess(imagePath.toString(), imagePathPreProcessed.toString());
            //BufferedImage bufferedImage = ImageIO.read(imagePathPreProcessed.toFile());
            BufferedImage bufferedImage = ImageIO.read(multipartFile.getInputStream());

            data = new StringBuilder(tesseract.doOCR(bufferedImage));
            log.info("data : {}", data);

        } else if (contentType.equals("application/pdf")) {

//            Path pdfPath = Paths.get("ocr-pdfdoc.pdf");
//
//            byte[] bytes = multipartFile.getBytes();
//            Files.copy(new ByteArrayInputStream(bytes), pdfPath, StandardCopyOption.REPLACE_EXISTING);
            PDDocument pdDocument = PDDocument.load(multipartFile.getInputStream()); //load(pdfPath.toFile());
            PDFRenderer pdfRenderer = new PDFRenderer(pdDocument);

            for (int page = 0; page < pdDocument.getNumberOfPages(); page++) {
                BufferedImage bufferedImage = pdfRenderer.renderImageWithDPI(page, 300);
                String s = tesseract.doOCR(bufferedImage);
                out.println(s);
                data.append(s);
            }
        }

        Map<String, String> aadharInFoMap = extractAadhaarData(data.toString());
        aadharInFoMap.forEach((key, value) -> out.println(key + " : " + value));

        Aadhar aadhar = new Aadhar(aadharInFoMap.get("aadhaarNumber"), aadharInFoMap.get("name"), aadharInFoMap.get("dob"), aadharInFoMap.get("gender"));
        return ocrRepository.save(aadhar);
    }


//    public Aadhar postAadharData(MultipartFile multipartFile) throws TesseractException, IOException {
//
//        Tesseract tesseract = new Tesseract();
//        tesseract.setDatapath("C:\\Program Files\\Tesseract-OCR\\tessdata");
//        tesseract.setLanguage("eng+hin");
//
//        Path path = Paths.get("temp\\ocr.jpg");
//
//        Files.copy(multipartFile.getInputStream(),path, StandardCopyOption.REPLACE_EXISTING);
//        BufferedImage bufferedImage= ImageIO.read(path.toFile());
//
//        String s = tesseract.doOCR(bufferedImage);
//        System.out.println(s);
//
//        String[] aadharLines = s.split("\\n");
//
//        long id;
//        String name;
//        String dob;
//        String address;
//
//        System.out.println("----------- for each loop -------------");
//
//        Map<String, String> aadharInFoMap = extractAadhaarData(s);
//        aadharInFoMap.forEach((key, value) -> {
//            System.out.println(key + " : " + value);
//        });
//
//
//        Aadhar aadhar2 = new Aadhar(aadharInFoMap.get("aadhaarNumber"),aadharInFoMap.get("name"),aadharInFoMap.get("dob"),aadharInFoMap.get("gender"));
//
//        return ocrRepository.save(aadhar2);
//    }

    private static final WebClient webClient = WebClient.create("http://localhost:11434");
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static Map<String, String> extractAadhaarData(String rawText) {
//        String prompt = "Extract Aadhaar details (aadhaarNumber, name, dob, gender) " +
//                "from the text below. the text contains some garbage values ignore them like for dob sometimes it has 008 and many more , i want cleaned data only. also dont get confused in gender, it can be male ,female or transgender only not anything except this if thereis anything with gender male,female or transgender clean it. Return ONLY JSON without extra text.\n\n" + rawText;

        String prompt = "Extract Aadhaar details (aadhaarNumber, name, dob, gender) from the text below. "
                + "aadharnumber must be of a 12 digit keep in mind"
                + "Ignore irrelevant/garbage values (e.g., extra numbers like '008' in DOB). "
                + "Also dob (date of birth) must be in DD/MM/YYYY format"
                + "Gender must be exactly one of: 'Male', 'Female', or 'Transgender' — clean it if needed. "
                + "Return ONLY JSON without extra text.\n\n"
                + rawText;

        String safePrompt = prompt
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "");

        String ndjson = webClient.post()
                .uri("/api/generate")
                .header("Content-Type", "application/json")
                .bodyValue("{\"model\":\"qwen3:0.6b\",\"format\":\"json\",\"prompt\":\"" + safePrompt + "\"}")
                .retrieve()
                .bodyToMono(String.class)
                .block();

        if (ndjson == null || ndjson.isEmpty()) {
            throw new RuntimeException("No response from Ollama. Is it running?");
        }

        try {
            StringBuilder combinedResponse = new StringBuilder();
            for (String line : ndjson.split("\n")) {
                if (!line.trim().isEmpty()) {
                    Map<String, Object> chunk = objectMapper.readValue(line, new TypeReference<>() {
                    });
                    if (chunk.containsKey("response")) {
                        combinedResponse.append(chunk.get("response").toString());
                    }
                }
            }

            String jsonString = combinedResponse.toString().trim();
            int start = jsonString.indexOf('{');
            int end = jsonString.lastIndexOf('}');
            if (start >= 0 && end >= 0) {
                jsonString = jsonString.substring(start, end + 1);
            }

            return objectMapper.readValue(jsonString, new TypeReference<>() {
            });
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse Aadhaar data from Ollama", e);
        }
    }

    public ByteArrayOutputStream exportAadharToPdf() {

        Document document = new Document();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, byteArrayOutputStream);
            document.open();

            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
            Paragraph title = new Paragraph("Aadhar Report", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            document.add(Chunk.NEWLINE);

            PdfPTable table = new PdfPTable(4);
            table.setWidthPercentage(100);
            table.setWidths(new float[]{2, 4, 1.5f, 1});

            Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
            String[] headers = {"aadhar", "Name", "DOB", "Gender"};
            for (String header : headers) {
                PdfPCell hcell = new PdfPCell(new Phrase(header, headFont));
                hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(hcell);
            }

            List<Aadhar> aadharList = ocrRepository.findAll();

            for (Aadhar aadhar : aadharList) {
                table.addCell(String.valueOf(aadhar.getAadharId()));
                table.addCell(aadhar.getName());
                table.addCell(String.valueOf(aadhar.getDob()));
                table.addCell(aadhar.getGender());
            }

            document.add(table);
            document.close();

        } catch (DocumentException e) {
            e.printStackTrace();
        }

        return byteArrayOutputStream;
    }
}
