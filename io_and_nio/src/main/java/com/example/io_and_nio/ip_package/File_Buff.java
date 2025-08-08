package com.example.io_and_nio.ip_package;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.CharBuffer;
import java.util.Arrays;

@Slf4j
@Data
public class File_Buff {


    public boolean createFile(String path) {

        File file = new File(path);

        try {
            if (file.createNewFile()) {

                log.info("new file successfully created as : {}", path);
                return true;
            } else {
                log.info("File already exist");
                return false;
            }
        } catch (IOException e) {
            log.error("Cant create file at {}", path);
            throw new RuntimeException(e);
        }
    }

    public void writeToFileWithBuffer(String message, String fileName) {

        log.info("in write to buffer method");
        createFile(fileName);

        // BufferedWriter bufferedWriter2;
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName, true))) {

            bufferedWriter.write(message);
            bufferedWriter.newLine();                // writes plat form specific new lines in buffer e.g windows : \r\n,  linux : \n
            bufferedWriter.write("new line");

            bufferedWriter.write(System.lineSeparator());   // returns platform specific new line object and it can be used with strings e.g "hi" + System.lineSeperator + "it's me"
            //bufferedWriter.append("aaa");                 // append to the current buffer position, not append to an existing file e.g writer.append("Hello").append(System.lineSeparator()).append("World");
            log.info("File write operation successful , message : {}", message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeToFileWithFileOutputStream(String message, String fileName) {

        createFile(fileName);

        File file = new File(fileName);

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file, true);
            byte[] bytes = new byte[5];
            bytes[0] = 'a';
            bytes[1] = 'b';
            bytes[2] = 'c';
            bytes[3] = 'd';
            bytes[4] = '\n';
            fileOutputStream.write(bytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String readFromFileWithBuffer(String fileName) {

        //File file = new File(fileName);
        String fileContent = "";
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(fileName));

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                fileContent = fileContent.concat(line);
            }

            log.info("File content : {}", fileContent);
            bufferedReader.close();

            return fileContent;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public String readFromFileWithFileInputStream(String fileName) {

        File file = new File(fileName);

        if (!file.exists()) {
            throw new RuntimeException("File not found");
        }

        try (FileInputStream fileInputStream = new FileInputStream(file)) {

            byte[] bytes = new byte[50];
            int read = fileInputStream.read(bytes);
            System.out.println("printing read : " + read);
            System.out.println(Arrays.toString(bytes));

            return Arrays.toString(bytes);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public void writeToFileWithFileWriter(String message, String fileName) {

        try (FileWriter fileWriter = new FileWriter(fileName)) {

            fileWriter.write(message);
            fileWriter.close();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public String readFileWithFileReader(String fileName) {

        File file = new File(fileName);


        try (FileReader fileReader = new FileReader(file)) {

            char[] charArray = new char[(int)file.length()];

            int read = fileReader.read(charArray);
            log.info("Message from file is : {}", Arrays.toString(charArray));
            return Arrays.toString(charArray);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

