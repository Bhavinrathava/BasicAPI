package com.bhavinr.SampleAPIAssignment;


import com.bhavinr.SampleAPIAssignment.model.Employee;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class TestingWebLayer {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @Test
    public void CanRequestForEmployees() throws Exception{
        assertThat(this.restTemplate.getForObject("http://localhost:"+port+"/employee", ArrayList.class)).isNotNull();
    }
}
