package com.bhavinr.SampleAPIAssignment;


import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.bhavinr.SampleAPIAssignment.controller.EmployeeController;
import com.bhavinr.SampleAPIAssignment.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest
@ContextConfiguration("/applicationContext.xml")
public class TestingWebLayer {

    @Autowired
    private MockMvc mockMvc;

    //EmployeeRepository repository=mock(EmployeeRepository.class);

    @Test
    public void CanRequestForEmployees() throws Exception{

    }
}
