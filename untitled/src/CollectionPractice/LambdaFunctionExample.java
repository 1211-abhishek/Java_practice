package CollectionPractice;


interface Printer{

    void print();
}

public class LambdaFunctionExample {
    public static void main(String[] args) {

    Printer pt = () -> System.out.println("In print");
    pt.print();
    }
}
