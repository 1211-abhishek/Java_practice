package com.example.spring_crud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringCrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCrudApplication.class, args);

//
//		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("");
//		EntityManager entityManager = entityManagerFactory.createEntityManager();
//
//		Employee emp = entityManager.find(Employee.class, 1211);
//		System.out.println(emp);
	}
}
