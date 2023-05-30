package com.example.employeeservice.model;

import jakarta.persistence.*;
import lombok.*;

import javax.swing.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name= "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long departmentId;
    private String name;
    private  String position;
    private int age;

}
