package org.example.xmlconf;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

class Main{
    public static void main(String[] args) {

        System.out.println("In main method");

        ApplicationContext context = new ClassPathXmlApplicationContext("SpringConf.xml");
        Employee employee = (Employee) context.getBean("Employee");

        System.out.println(employee);

    }
}