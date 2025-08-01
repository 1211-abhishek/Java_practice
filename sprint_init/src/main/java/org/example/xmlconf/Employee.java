package org.example.xmlconf;

public class Employee {

    int empId;
    String empName;
    Company dep;

    public Employee() {
        System.out.println("Employee initialized");
    }

    public Employee(int empId, String empName) {
        this.empId = empId;
        this.empName = empName;
    }
    public Employee(int empId) {
        this.empId = empId;
        this.empName = "no name";
    }

    public void setEmpId(int empId) {
        System.out.println("In setter empId");
        this.empId = empId;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public void setDep(Company dep) {
        this.dep = dep;
    }

    public Company getDep() {
        return dep;
    }
    public int getEmpId() {
        return empId;
    }

    public String getEmpName() {
        return empName;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empId=" + empId +
                ", empName='" + empName + '\'' +
                ", dep=" + dep +
                '}';
    }
}
