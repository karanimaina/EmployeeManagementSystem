package com.example.employeeservice.service;

import com.example.employeeservice.UniversalResponse;
import com.example.employeeservice.model.Employee;
import com.example.employeeservice.record.EmployeeRecord;
import reactor.core.publisher.Mono;

public interface EmployeeInterface {
   Mono<UniversalResponse>createEmployee(EmployeeRecord employeeRecord);
   Mono<UniversalResponse>updateEmployee(long id,EmployeeRecord employeeRecord);
   Mono<UniversalResponse>deleteEmployee(long id);
   Mono<UniversalResponse>findEmployee(long id);

}
