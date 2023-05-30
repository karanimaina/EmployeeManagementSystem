package com.example.employeeservice.service;

import com.example.employeeservice.UniversalResponse;
import com.example.employeeservice.exception.EmployeeException;
import com.example.employeeservice.model.Employee;
import com.example.employeeservice.record.EmployeeRecord;
import com.example.employeeservice.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService implements EmployeeInterface {
    private final EmployeeRepository employeeRepository;

    @Override
    public Mono<UniversalResponse> createEmployee(EmployeeRecord employeeRecord) {
        return Mono.fromCallable(() -> {
            Employee employee = Employee.builder()
                    .departmentId(employeeRecord.departmentId())

                    .name(employeeRecord.name())
                    .position(employeeRecord.position())
                    .age(employeeRecord.age())
                    .build();
            employeeRepository.save(employee);
            return UniversalResponse.builder().status(200).data(employee).message("employee added successfully").build();
        }).publishOn(Schedulers.boundedElastic());
    }

    @Override
    public Mono<UniversalResponse> updateEmployee(long id, EmployeeRecord employeeRecord) {
        return Mono.fromCallable(() -> {
            Optional<Employee> employeeOptional = employeeRepository.findById(id);
            Employee employee = null;
            if (employeeOptional.isPresent()) {
                employee = employeeOptional.get();
                BeanUtils.copyProperties(employeeRecord,employee);
                employeeRepository.save(employee);
            }
            return UniversalResponse.builder().status(200).data(employee).message("employee added successfully").build();
        }).publishOn(Schedulers.boundedElastic());
    }

    @Override
    public Mono<UniversalResponse> deleteEmployee(long id) {
        return Mono.fromCallable(() -> {
            Optional<Employee> employee = employeeRepository.findById(id);
            if (employee.isPresent()) {
                employeeRepository.delete(employee.get());
                return UniversalResponse.builder().message("employee deleted successfully").build();
            }
            throw  new EmployeeException("employee does not exist");

        }).publishOn(Schedulers.boundedElastic());
    }

    @Override
    public Mono<UniversalResponse> findEmployee(long id) {
      return Mono.fromCallable(()-> {
          Optional<Employee> employee = employeeRepository.findById(id);
          return UniversalResponse.builder().status(200).data(employee).message("employee retrieved successfully").build();
      }).publishOn(Schedulers.boundedElastic());
    }
}
