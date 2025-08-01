package CollectionPractice;

import sun.reflect.generics.tree.Tree;

import java.util.*;

public class CollectionTest {

    public static void main(String[] args) {


        treeSetExample();
    }


    public static void arrayListExample() {
        List<EmployeeModal> arrayListEx = new ArrayList<>();

        arrayListEx.add(new EmployeeModal(1211, "Abhishek", "Java"));
        arrayListEx.add(new EmployeeModal(1209, "Jayesh", "ServiceNow"));
        arrayListEx.add(new EmployeeModal(1210, "Ashar", "Java"));
        arrayListEx.add(new EmployeeModal(1208, "Abhay", "RPA"));

        System.out.println(arrayListEx);
        arrayListEx.sort((o, no) -> o.emp_id - no.emp_id);
        //Collections.sort();
        //Collections.sort(arrayListEx);
        System.out.println(arrayListEx);
    }

    public static void treeSetExample() {

        Set<EmployeeModal> treeSetEx = new TreeSet<>();
        treeSetEx.add(new EmployeeModal(1211, "Abhishek", "Java"));
        treeSetEx.add(new EmployeeModal(1209, "Jayesh", "ServiceNow"));
        treeSetEx.add(new EmployeeModal(1210, "Ashar", "Java"));
        treeSetEx.add(new EmployeeModal(1208, "Abhay", "RPA"));

        System.out.println(treeSetEx);
    }

    public static void hashMapExample(){

    }
}
