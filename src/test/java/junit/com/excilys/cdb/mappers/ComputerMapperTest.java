package junit.com.excilys.cdb.mappers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.excilys.cdb.dtos.CompanyDTO;
import com.excilys.cdb.dtos.ComputerDTO;
import com.excilys.cdb.mappers.ComputerMapper;
import com.excilys.cdb.models.Company;
import com.excilys.cdb.models.Computer;
import com.excilys.cdb.utils.DateFormatManagerException;

@RunWith(MockitoJUnitRunner.class)
public class ComputerMapperTest {

	@Mock
	ComputerDTO computerDTO;
	@Mock
	Computer computer;
	@Mock
	Company company;
	@Mock
	CompanyDTO companyDTO;
	

	@Before
	public void setUp() {

	}

	@After
	public void tearDown() {
		computerDTO = null;
		computer = null;
	}

	/**
	 * Test toComputerDTO method with null parameter
	 */
	@Test
	public void toComputerDTONullParameterTest(){
		try {
			ComputerDTO resultComputerDTO = ComputerMapper.toComputerDTO(null);
			assertNull(resultComputerDTO);		}
		catch(Throwable t) {
			fail("Exception thrown should not be thrown");
		}
	}
	
	/**
	 * Test toComputerDTO method with invalid computer as parameter
	 */
	@Test
	public void toComputerDTOInvalidComputerTest(){
		
		when(computer.getId()).thenReturn(-5);
		when(computer.getName()).thenReturn(null);
		when(computer.getIntroduced()).thenReturn(null);
		when(computer.getDiscontinued()).thenReturn(null);
		when(computer.getCompany()).thenReturn(null);
		
		try {
			ComputerDTO resultComputerDTO = ComputerMapper.toComputerDTO(computer);
			assertNotNull(resultComputerDTO);
			assertEquals(resultComputerDTO.getId(),-5);
			assertEquals(resultComputerDTO.getName(),null);
			assertNull(resultComputerDTO.getIntroduced());
			assertNull(resultComputerDTO.getDiscontinued());
			assertNull(resultComputerDTO.getCompany());
		}
		catch(Throwable t) {
			fail("Exception thrown should not be thrown");
		}
	}
	
	/**
	 * Test toComputerDTO with a valid computer
	 */
	@Test
	public void toComputerDTOValidComputerTest(){
		
		LocalDateTime intro = LocalDateTime.of(2013, 02, 28, 14, 20);
		LocalDateTime disc = LocalDateTime.of(2016, 02, 28, 14, 20);
		
		when(company.getId()).thenReturn(28);
		when(company.getName()).thenReturn("testCompany");
		
		when(computer.getId()).thenReturn(5);
		when(computer.getName()).thenReturn("testComputer");
		when(computer.getIntroduced()).thenReturn(intro);
		when(computer.getDiscontinued()).thenReturn(disc);
		when(computer.getCompany()).thenReturn(company);
		
		try {
			ComputerDTO resultComputerDTO = ComputerMapper.toComputerDTO(computer);
			assertNotNull(resultComputerDTO);
			assertEquals(5,resultComputerDTO.getId());
			assertEquals("testComputer",resultComputerDTO.getName());
			assertEquals("2013-02-28",resultComputerDTO.getIntroduced());
			assertEquals("2016-02-28",resultComputerDTO.getDiscontinued());
			assertEquals(28,resultComputerDTO.getCompany().getId());
			assertEquals("testCompany",resultComputerDTO.getCompany().getName());
		}
		catch(Throwable t) {
			fail("Exception thrown should not be thrown");
		}
	}
	
	/**
	 * Test toComputer with a null ComputerDTO parameter
	 */
	@Test
	public void toComputerNullParameterTest(){
		try {
			Computer resultComputer = ComputerMapper.toComputer(null);
			assertNull(resultComputer);	
		}
		catch(Throwable t) {
			fail("Exception thrown should not be thrown");
		}
	}
	
	/**
	 * Test toComputer with an invalid ComputerDTO parameter
	 */
	@Test
	public void toComputerInvalidComputerDTOTest(){
		
		LocalDateTime intro = LocalDateTime.of(2016, 03, 04, 00, 00);
		LocalDateTime disc = LocalDateTime.of(2017, 03, 04, 00, 00);
		
		when(computerDTO.getId()).thenReturn(-5);
		when(computerDTO.getName()).thenReturn(null);
		when(computerDTO.getIntroduced()).thenReturn("2016-03-04");
		when(computerDTO.getDiscontinued()).thenReturn("2017-03-04");
		when(computerDTO.getCompany()).thenReturn(null);
		
		try {
			Computer resultComputer = ComputerMapper.toComputer(computerDTO);
			assertNotNull(resultComputer);
			assertEquals(-5,resultComputer.getId());
			assertNull(resultComputer.getName());
			assertNotNull(resultComputer.getIntroduced());
			assertNotNull(resultComputer.getDiscontinued());
			assertEquals(intro,resultComputer.getIntroduced());
			assertEquals(disc,resultComputer.getDiscontinued());
			assertNull(resultComputer.getCompany());
		}
		catch(Throwable t) {
			fail("Exception thrown should not be thrown");
		}
	}
	
	/**
	 * Test toComputer with an invalid ComputerDTO dates format
	 */
	@Test(expected = DateFormatManagerException.class)  
	public void toComputerInvalidDateFormatTest(){
		
		when(computerDTO.getId()).thenReturn(-5);
		when(computerDTO.getName()).thenReturn(null);
		when(computerDTO.getIntroduced()).thenReturn("13/50/30091");
		when(computerDTO.getDiscontinued()).thenReturn("2017-03-04");
		when(computerDTO.getCompany()).thenReturn(null);
		
		ComputerMapper.toComputer(computerDTO);
	}
	
	/**
	 * Test toComputer method with a valid computer
	 */
	@Test
	public void toComputerValidComputerDTOTest(){
		
		LocalDateTime intro = LocalDateTime.of(2016, 03, 04, 00, 00);
		LocalDateTime disc = LocalDateTime.of(2017, 03, 04, 00, 00);
		
		when(companyDTO.getId()).thenReturn(202);
		when(companyDTO.getName()).thenReturn("CompanyTest");		
		
		when(computerDTO.getId()).thenReturn(5);
		when(computerDTO.getName()).thenReturn("ComputerTest");
		when(computerDTO.getIntroduced()).thenReturn("2016-03-04");
		when(computerDTO.getDiscontinued()).thenReturn("2017-03-04");
		when(computerDTO.getCompany()).thenReturn(companyDTO);
		
		try {
			Computer resultComputer = ComputerMapper.toComputer(computerDTO);
			assertNotNull(resultComputer);
			assertEquals(5,resultComputer.getId());
			assertEquals("ComputerTest",resultComputer.getName());
			assertEquals(intro,resultComputer.getIntroduced());
			assertEquals(disc,resultComputer.getDiscontinued());
			assertNotNull(resultComputer.getCompany());
			assertEquals(202, resultComputer.getCompany().getId());
			assertEquals("CompanyTest", resultComputer.getCompany().getName());
		}
		catch(Throwable t) {
			fail("Exception thrown should not be thrown");
		}
	}
		
}
