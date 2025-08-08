package com.example.io_and_nio;

import com.example.io_and_nio.ip_package.File_Buff;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

@SpringBootApplication
public class IoAndNioApplication {

    public static void main(String[] args) {
        SpringApplication.run(IoAndNioApplication.class, args);


        String fileName = ".\\FileWriter.txt";

//        File_Buff obj = new File_Buff();
//        obj.createFile(fileName);

//        obj.writeToFileWithBuffer("this is new file", fileName);
//        obj.writeToFileWithFileOutputStream("write with fileoutputstream", fileName);
//        obj.readFromFileWithBuffer(fileName);
//        obj.writeToFileWithFileWriter("File writter message",fileName);
//        obj.readFileWithFileReader(fileName);

    }

}
