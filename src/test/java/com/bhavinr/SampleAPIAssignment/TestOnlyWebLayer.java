package com.bhavinr.SampleAPIAssignment;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.bhavinr.SampleAPIAssignment.controller.EmployeeController;
import com.bhavinr.SampleAPIAssignment.exceptionHandlers.DatabaseEmptyException;
import com.bhavinr.SampleAPIAssignment.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class TestOnlyWebLayer {

    @Autowired
    private MockMvc mockMvc;

    @Test void CheckMainEndPointResponds () throws Exception{
        this.mockMvc.perform(get("/employee/1")).andExpect(status().isOk());
    }
}