package com.bhavinr.SampleAPIAssignment;
import com.bhavinr.SampleAPIAssignment.exceptionHandlers.BadRequestException;
import com.bhavinr.SampleAPIAssignment.exceptionHandlers.DatabaseEmptyException;
import com.bhavinr.SampleAPIAssignment.model.Employee;
import com.bhavinr.SampleAPIAssignment.repository.EmployeeRepository;
import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.*;


@SpringBootTest
class SampleApiAssignmentApplicationTests extends AbstractTest{

	EmployeeRepository repository;
	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@BeforeEach
	void setup()
	{
		repository=mock(EmployeeRepository.class);
	}

	@Test
	void contextLoads() {
	}

	@Test
	void CanCallFindAll()
	{
		List<Employee> testList=repository.findAll();
		verify(repository).findAll();
	}

	@Test
	void ThrowExceptionNoEmployeeData()
	{
		expectedException.expect(DatabaseEmptyException.class);
		expectedException.expectMessage("The database you want to access doesn't have any resources in it.");
		when(repository.findAll()).thenThrow(new DatabaseEmptyException("The database you want to access doesn't have any resources in it."));
	}

	@Test
	void CanFindAnEmployee()
	{
		Optional<Employee> testEmployee=repository.findById(anyLong());
		verify(repository).findById(anyLong());
	}

	@Test
	void InvalidIDinGetEmployee()
	{
		expectedException.expect(BadRequestException.class);
		expectedException.expectMessage("The User you queried for doesn't exist. ID:");

    when(repository.findById(anyLong()))
        .thenThrow(new DatabaseEmptyException("The User you queried for doesn't exist. ID"));
	}

	/*
	@Test
	public void CangetEmployeesList() throws Exception {
		String uri = "/employee";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Employee[] employees = super.mapFromJson(content,Employee[].class);
		assertTrue(employees.length > 0);
	}

	@Test
	public void CanGetSingleEmployee() throws Exception {
		String uri="/employee/1";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}


	@Test
	public void CanPostEmployeeDetails() throws Exception{
		String uri="/employee";
		Employee testEmployee=new Employee((long)6,"Bhavin","Rathava","3456878890");
		String JsonObject=super.mapToJson(testEmployee);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
				.accept(MediaType.APPLICATION_JSON_VALUE).content(JsonObject)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}

	@Test
	public void CanUpdateEmployee() throws Exception
	{
		String uri="/employee/1";
		Employee testEmployee=new Employee((long)6,"Bhavin","Rathava","3456878890");
		String JsonObject=super.mapToJson(testEmployee);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
				.accept(MediaType.APPLICATION_JSON_VALUE).content(JsonObject)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}

	@Test
	public void CanDeleteEmployee() throws Exception{
		String uri="/employee/1";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}

	@Test
	public void test1() throws Exception{
		assertNotNull(this.restTemplate.getForObject("http://localhost:" + port + "/"+"employee/1",Employee.class));
	}
	*/
}
