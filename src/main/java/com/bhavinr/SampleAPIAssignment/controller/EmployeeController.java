package com.bhavinr.SampleAPIAssignment.controller;

import com.bhavinr.SampleAPIAssignment.exceptionHandlers.BadRequestException;
import com.bhavinr.SampleAPIAssignment.exceptionHandlers.DatabaseEmptyException;
import com.bhavinr.SampleAPIAssignment.model.Employee;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.bhavinr.SampleAPIAssignment.repository.EmployeeRepository;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;
import java.util.Optional;


@RestController
public class EmployeeController {
    @Autowired
    EmployeeRepository repository;


    @GetMapping("*") public String test() { return "Not a valid endpoint. Please Check Documentation"; }

    @GetMapping("/employee")
    public List<Employee> getAllUsers()
    {
        List<Employee> listOfEmployees=repository.findAll();

        if(listOfEmployees.size()==0)
        {
            throw new DatabaseEmptyException("The database you want to access doesn't have any resources in it.");
        }

        return repository.findAll();
    }

    @GetMapping("/employee/{id}")
    public Employee getUserById(@PathVariable("id") Long id)
    {
       if(repository.findById(id).isPresent())
            {return repository.findById(id).get();}
       else throw new DatabaseEmptyException("The User you queried for doesn't exist. ID:"+id);
    }

    @PutMapping("/employee/{id}")
    public Employee EditEmployee (@RequestBody Employee employee, @PathVariable("id") Long id)
    {
        /*
        Employee currentEmpl=repository.getOne(id);
        BeanUtils.copyProperties(employee,currentEmpl,"id");
        //return repository.saveAndFlush(currentEmpl);
        */
        
        Optional<Employee> targetEmployeeOptional=repository.findById(id);
        if(targetEmployeeOptional.isEmpty())
        {
            throw new DatabaseEmptyException("The user targeted for change doesn't exist. ID: "+id);
        }
        Employee targetEmployee=targetEmployeeOptional.get();
        BeanUtils.copyProperties(employee,targetEmployee,"id");
        return repository.saveAndFlush(targetEmployee);

    }

    @PostMapping("/employee/{id}")
    public Employee createEmployee (@PathVariable(value = "id") Long id, @RequestBody Employee employee)
    {
        if(repository.existsById(id))
        {
            throw new BadRequestException("The User by the Given ID already exists. Please Try another ID.");
        }
        Employee employeeNew=new Employee(id,employee.getFirstname(),employee.getLastname(), employee.getcontactnumber());
        return repository.saveAndFlush(employeeNew);


    }

    @DeleteMapping("/employee/{id}")
    public void deleteEmployee (@PathVariable("id") Long id)
    {
        if(!repository.existsById(id))
        {
            throw new BadRequestException("The User requested for deletion doesn't exist! Enter a valid id!");
        }
        repository.deleteById(id);
    }

}
