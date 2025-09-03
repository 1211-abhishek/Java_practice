package com.example.design_patterns.prototype;

import lombok.Data;

@Data
public class Student implements User {

    private int rollNo;

    public Student(int rollNo){
        this.rollNo = rollNo;
    }

    @Override
    public Student clone(){
        try {
//            return new Student(this.rollNo);
            return (Student) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }


    public  static void main(String[] args){

        Student student = new Student(11);
        Student copyStudent = student.clone();

        copyStudent.rollNo = 21;

        System.out.println("Student : " + student.getRollNo());
        System.out.println("Student copy : " + copyStudent.getRollNo());
    }
}