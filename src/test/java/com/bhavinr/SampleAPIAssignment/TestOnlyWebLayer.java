package com.bhavinr.SampleAPIAssignment;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.bhavinr.SampleAPIAssignment.model.Employee;
import com.bhavinr.SampleAPIAssignment.repository.EmployeeRepository;


import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
public class TestOnlyWebLayer extends AbstractTest{

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    EmployeeRepository repository;

    @Test
    public void CheckMainEndPointResponse () throws Exception{
        this.mockMvc.perform(get("/employee/")).andExpect(status().isOk());
    }

    @Test public void GetValidUserExpect200() throws Exception{
        this.mockMvc.perform(get("/employee/1")).andExpect(status().isOk());
    }
    @Test public void GetInValidUserExpect4xx() throws Exception{
        this.mockMvc.perform(get("/employee/1")).andExpect(status().is4xxClientError());
    }


	@Test
	public void CangetEmployeesList() throws Exception {
		String uri = "/employee";
		MvcResult mvcResult = this.mockMvc.perform(get(uri)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Employee[] employees = super.mapFromJson(content,Employee[].class);
		assertTrue(employees.length > 0);
	}

	@Test
	public void CanPostEmployeeDetails() throws Exception{
		String uri="/employee/6";
		Employee testEmployee=new Employee((long)6,"Bhavin","Rathava","3456878890");
		String JsonObject=super.mapToJson(testEmployee);
		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post(uri)
				.contentType("application/json").content(JsonObject)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}

	@Test
	public void CanUpdateEmployee() throws Exception
	{
		String uri="/employee/2";
		Employee testEmployee=new Employee((long)2,"Bhavin","Rathava","3456878890");
		String JsonObject=super.mapToJson(testEmployee);
		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.put(uri)
				.contentType("application/json").content(JsonObject)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}

	@Test
	public void CanDeleteEmployee() throws Exception{
		String uri="/employee/1";
		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}


}