package com.bhavinr.SampleAPIAssignment.repository;

import com.bhavinr.SampleAPIAssignment.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
