package com.bhavinr.SampleAPIAssignment.controller;

import com.bhavinr.SampleAPIAssignment.model.Employee;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.bhavinr.SampleAPIAssignment.repository.EmployeeRepository;

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
        Employee empl=repository.getOne(id);
        return empl;
    }

    @RequestMapping (value="{id}", method = RequestMethod.PUT)
    public Employee EditEmployee (@RequestBody Employee employee, @PathVariable("id") Long id)
    {
        Employee currentEmpl=repository.getOne(id);
        BeanUtils.copyProperties(employee,currentEmpl,"id");
        return repository.saveAndFlush(currentEmpl);
    }

    @PostMapping("/employee")
    public Employee createEmployee (@RequestBody Employee employee)
    {
        return repository.saveAndFlush(employee);
    }

    @RequestMapping (value ="{id}", method = RequestMethod.DELETE)
    public void deleteEmployee (@PathVariable("id") Long id)
    {
        repository.deleteById(id);
    }

}
