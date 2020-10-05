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

}
