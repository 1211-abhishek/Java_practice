package org.example.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class Member implements Serializable
{
   /** Default value included to remove warning. Remove or modify at will. **/
   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue
   private Long id;

   @NotNull
   @Size(min = 1, max = 25)
   @Pattern(regexp = "[A-Za-z ]*", message = "must contain only letters and spaces")
   private String name;

   @NotNull
   @NotEmpty
   @Email
   private String email;

   @NotNull
   @Size(min = 10, max = 12)
   @Digits(fraction = 0, integer = 12)
   @Column(name = "phone_number")
   private String phoneNumber;

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public String getPhoneNumber() {
      return phoneNumber;
   }

   public void setPhoneNumber(String phoneNumber) {
      this.phoneNumber = phoneNumber;
   }
}