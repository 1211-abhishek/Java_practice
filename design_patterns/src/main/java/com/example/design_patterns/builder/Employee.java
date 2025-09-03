package com.example.design_patterns.builder;

import lombok.Getter;

@Getter
public class Employee {

    private final int employeeId;
    private final String employeeName;
    private final String companyName;

    private Employee(Builder builder){
        this.employeeName = builder.employeeName;
        this.employeeId = builder.employeeId;
        this.companyName = builder.companyName;
    }

    static class Builder{

        private int employeeId;
        private String employeeName;
        private String companyName;

        public Builder employeeId(int employeeId){
            this.employeeId = employeeId;
            return this;
        }

        public Builder employeeName(String employeeName){
            this.employeeName = employeeName;
            return this;
        }

        public Builder companyName(String companyName){
            this.companyName = companyName;
            return this;
        }

        public Employee build(){
            return new Employee(this);
        }
    }

    public static void main(String[] args) {

        Employee abc = new Builder()
                .employeeId(11)
                .employeeName("abc")
                .employeeId(50)
                .build();

        System.out.println(abc.employeeId);
    }
}
