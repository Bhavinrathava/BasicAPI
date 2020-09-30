package com.bhavinr.SampleAPIAssignment.controller;

import com.bhavinr.SampleAPIAssignment.model.Employee;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.bhavinr.SampleAPIAssignment.repository.EmployeeRepository;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeRepository repository;

    @GetMapping("*") public String test() { return "test"; }

    @GetMapping("/employee")
    public List<Employee> getAllUsers()
    {
        //System.out.println(repository.findAll());
        return repository.findAll();
    }

    @GetMapping("/employee/{id}")
    public Employee getUserById(@PathVariable("id") Long id)
    {
        /*
        Employee empl=repository.getOne(id);
        return empl;
        */

       if(repository.findById(id).isPresent())
       {
           return repository.findById(id).get();
       }
       else throw new ResourceAccessException("The User you queried for doesn't exist. ID:"+id);

    }

    @PutMapping("/employee/{id}")
    public Employee EditEmployee (@RequestBody Employee employee, @PathVariable("id") Long id)
    {
        Employee currentEmpl=repository.getOne(id);
        BeanUtils.copyProperties(employee,currentEmpl,"id");
        return repository.saveAndFlush(currentEmpl);
    }

    @PostMapping("/employee/{id}")
    public Employee createEmployee (@PathVariable(value = "id") Long id, @RequestBody Employee employee)
    {
        Employee employeeNew=new Employee(id,employee.getFirstname(),employee.getLastname(), employee.getcontactnumber());
        System.out.println("GIVEN DETAILS :"+employee.getId());
        System.out.println("NEW EMPLOYEE: "+employeeNew.getId());
        System.out.println("NEW EMPLOYEE: "+employeeNew.getFirstname());
        System.out.println("NEW EMPLOYEE: "+employeeNew.getLastname());
        System.out.println("NEW EMPLOYEE: "+employeeNew.getcontactnumber());
        return repository.saveAndFlush(employeeNew);

    }

    @DeleteMapping("/employee/{id}")
    public void deleteEmployee (@PathVariable("id") Long id)
    {
        repository.deleteById(id);
    }

}
