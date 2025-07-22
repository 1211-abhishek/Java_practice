package Pool;

import javax.xml.bind.SchemaOutputResolver;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class StringConstantPool {

    public static void main(String[] args) {

        String str1 = "abc";
        String str2 = "abc";
        String str3 = new String("abc");
        String str4 = new String("abc");

        System.out.println(str1 == str2);
        System.out.println(str2 == str3);
        System.out.println(str3 == str4);


        System.out.println("Integer Cache pool");


        int a = 10;
        int b = 10;
        int c = 150;
        int d = 150;

        Integer aa = 10;
        Integer bb = 10;
        Integer bbb = new Integer(10);
        Integer cc = 150;
        Integer dd = 150;

        System.out.println(aa == bb);
        System.out.println(aa == bbb);
        System.out.println(cc == bb);
        System.out.println(cc == dd);

        List<Integer> arlist = new ArrayList<>();

//        System.out.println("--------------------");
//        System.out.println("Employee objects");
//
//        Employee emp1 = new Employee(10,"a");
//        Employee emp2 = new Employee(10,"a");
//        Employee emp3 = new Employee(10,"b");
//        Employee emp4 = new Employee(11,"c");
//
//        System.out.println(emp1.equals(emp2));
//        System.out.println(emp2.equals(emp3));
//        System.out.println(emp3.equals(emp4));
      }
}

//class Employee{
//
//    int empId;
//    String empName;
//
//    Employee(int empId, String empName){
//        this.empId = empId;
//        this.empName = empName;
//    }
//
//    @Override
//    public boolean equals(Object obj) {
//        Employee e = (Employee) obj;
//        return this.empId == e.empId;
//    }
//}

