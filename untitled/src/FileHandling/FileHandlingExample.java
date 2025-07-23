package FileHandling;

import java.io.*;

public class FileHandlingExample {

    public static void main(String[] args) {

        try {
            File myFile = new File("example.txt");
            if (myFile.createNewFile()) {
                System.out.println("File created: " + myFile.getName());
            } else {
                System.out.println("File already exists.");
            }

            FileWriter writer = new FileWriter("C:\\Users\\Sreenivas Bandaru\\Desktop\\Java\\example.txt");
            writer.write("Hello, First Line.\nSecond line.");
            writer.close();
            System.out.println("Successfully wrote to the file.");

            BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Sreenivas Bandaru\\Desktop\\Java\\example.txt"));
            String line;
            System.out.println("File content:");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            reader.close();

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
