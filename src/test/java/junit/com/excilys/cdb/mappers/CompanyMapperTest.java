package junit.com.excilys.cdb.mappers;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.excilys.cdb.dtos.CompanyDTO;
import com.excilys.cdb.mappers.CompanyMapper;
import com.excilys.cdb.models.Company;

@RunWith(MockitoJUnitRunner.class)
public class CompanyMapperTest {

	@Mock
	CompanyDTO companyDTO;
	@Mock
	Company company;

	@Before
	public void setUp() {

	}

	@After
	public void tearDown() {
		companyDTO = null;
		company = null;
	}

	/**
	 * Test toCompanyDTO method with null parameter
	 */
	@Test
	public void toCompanyDTONullParameterTest(){
		try {
			CompanyDTO resultCompanyDTO = CompanyMapper.toCompanyDTO(null);
			assertNull(resultCompanyDTO);		}
		catch(Throwable t) {
			fail("Exception thrown should not be thrown");
		}
	}
	
	/**
	 * Test toCompanyDTO method with invalid company as parameter
	 */
	@Test
	public void toCompanyDTOInvalidCompanyTest(){
		
		when(company.getId()).thenReturn(-5);
		when(company.getName()).thenReturn(null);
		
		try {
			CompanyDTO resultCompanyDTO = CompanyMapper.toCompanyDTO(company);
			assertNotNull(resultCompanyDTO);
			assertEquals(resultCompanyDTO.getId(),-5);
			assertEquals(resultCompanyDTO.getName(),null);
		}
		catch(Throwable t) {
			fail("Exception thrown should not be thrown");
		}
	}
	
	/**
	 * Test toCompanyDTO with a valid company
	 */
	@Test
	public void toCompanyDTOValidCompanyTest(){
		
		when(company.getId()).thenReturn(5);
		when(company.getName()).thenReturn("apple");
		
		try {
			CompanyDTO resultCompanyDTO = CompanyMapper.toCompanyDTO(company);
			assertNotNull(resultCompanyDTO);
			assertEquals(resultCompanyDTO.getId(),5);
			assertEquals(resultCompanyDTO.getName(),"apple");
		}
		catch(Throwable t) {
			fail("Exception thrown should not be thrown");
		}
	}
	
	/**
	 * Test toCompany with a null CompanyDTO parameter
	 */
	@Test
	public void toCompanyNullParameterTest(){
		try {
			Company resultCompany = CompanyMapper.toCompany(null);
			assertNull(resultCompany);	
		}
		catch(Throwable t) {
			fail("Exception thrown should not be thrown");
		}
	}
	
	/**
	 * Test toCompany with an invalid CompanyDTO parameter
	 */
	@Test
	public void toCompanyInvalidCompanyDTOest(){
		
		when(companyDTO.getId()).thenReturn(-30);
		when(companyDTO.getName()).thenReturn(null);
		
		try {
			Company resultCompany = CompanyMapper.toCompany(companyDTO);
			assertNotNull(resultCompany);
			assertEquals(resultCompany.getId(),-30);
			assertEquals(resultCompany.getName(),null);
		}
		catch(Throwable t) {
			fail("Exception thrown should not be thrown");
		}
	}
	
	/**
	 * Test toCompany with a valid CompanyDTO parameter
	 */
	@Test
	public void toCompanyValidCompanyDTOTest(){
		
		when(companyDTO.getId()).thenReturn(6);
		when(companyDTO.getName()).thenReturn("apple");
		
		try {
			Company resultCompany = CompanyMapper.toCompany(companyDTO);
			assertNotNull(resultCompany);
			assertEquals(resultCompany.getId(),6);
			assertEquals(resultCompany.getName(),"apple");
		}
		catch(Throwable t) {
			fail("Exception thrown should not be thrown");
		}
	}
	
	/**
	 * Test toCompanyDTOList with a null parameter
	 */
	@Test
	public void toCompanyDTOListNullParamTest(){
				
		try {
			List<CompanyDTO> resultCompany = CompanyMapper.toCompanyDTOList(null);
			assertNull(resultCompany);
		}
		catch(Throwable t) {
			fail("Exception thrown should not be thrown");
		}
	}
	
	/**
	 * Test toCompanyDTOList with an invalid list company parameter parameter
	 */
	@Test
	public void toCompanyDTOListInvalidParamTest(){
		
		int id = -1;
		String name = null;
		
		when(company.getId()).thenReturn(id);
		when(company.getName()).thenReturn(name);		
		List<Company> companies =  new ArrayList<Company>();
		companies.add(null);
		companies.add(company);
		
		int count = companies.size();
		
		try {
			List<CompanyDTO> resultCompanyDTOs = CompanyMapper.toCompanyDTOList(companies);
			assertNull(resultCompanyDTOs.get(0));
			assertEquals(count,resultCompanyDTOs.size());
			assertEquals(id,resultCompanyDTOs.get(1).getId());
			assertNull(resultCompanyDTOs.get(1).getName());
		}
		catch(Throwable t) {
			fail("Exception thrown should not be thrown");
		}
	}
	
}
