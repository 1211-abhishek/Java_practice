package FileHandling;

import java.io.*;

class Student implements Serializable {
    String name;
    int age;

    Student(String name, int age) {
        this.name = name;
        this.age = age;
    }
}

public class ObjeSerializableExample {
    public static void main(String[] args) {

        Student student = new Student("Abhishek", 22);

        try {
            FileOutputStream fileOut = new FileOutputStream("C:\\Users\\Sreenivas Bandaru\\Desktop\\Java\\student.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);

            out.writeObject(student);
            out.close();
            fileOut.close();

            System.out.println("Object has been serialized to student.ser");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileInputStream fileIn = new FileInputStream("C:\\Users\\Sreenivas Bandaru\\Desktop\\Java\\student.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);

            Student readStudent = (Student) in.readObject();
            in.close();
            fileIn.close();

            System.out.println("Object has been deserialized:");
            System.out.println("Name: " + readStudent.name);
            System.out.println("Age: " + readStudent.age);

        } catch (IOException | ClassNotFoundException e) {
            //e.printStackTrace();
        }
    }
}
