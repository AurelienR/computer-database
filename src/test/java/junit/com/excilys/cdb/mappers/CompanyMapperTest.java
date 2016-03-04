package junit.com.excilys.cdb.mappers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

import com.excilys.cdb.dtos.CompanyDto;
import com.excilys.cdb.mappers.CompanyMapper;
import com.excilys.cdb.models.Company;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class CompanyMapperTest {

  @Mock
  CompanyDto companyDto;
  @Mock
  Company company;

  @Before
  public void setUp() {

  }

  @After
  public void tearDown() {
    companyDto = null;
    company = null;
  }

  /**
   * Test toCompanyDTO method with null parameter.
   */
  @Test
  public void toCompanyDtoNullParameterTest() {
    try {
      CompanyDto resultCompanyDto = CompanyMapper.toCompanyDto(null);
      assertNull(resultCompanyDto);
    } catch (Throwable t) {
      fail("Exception thrown should not be thrown");
    }
  }

  /**
   * Test toCompanyDTO method with invalid company as parameter.
   */
  @Test
  public void toCompanyDtoInvalidCompanyTest() {

    when(company.getId()).thenReturn(-5L);
    when(company.getName()).thenReturn(null);

    try {
      CompanyDto resultCompanyDto = CompanyMapper.toCompanyDto(company);
      assertNotNull(resultCompanyDto);
      assertEquals(resultCompanyDto.getId(), -5L);
      assertEquals(resultCompanyDto.getName(), null);
    } catch (Throwable t) {
      fail("Exception thrown should not be thrown");
    }
  }

  /**
   * Test toCompanyDTO with a valid company.
   */
  @Test
  public void toCompanyDtoValidCompanyTest() {

    when(company.getId()).thenReturn(5L);
    when(company.getName()).thenReturn("apple");

    try {
      CompanyDto resultCompanyDto = CompanyMapper.toCompanyDto(company);
      assertNotNull(resultCompanyDto);
      assertEquals(resultCompanyDto.getId(), 5L);
      assertEquals(resultCompanyDto.getName(), "apple");
    } catch (Throwable t) {
      fail("Exception thrown should not be thrown");
    }
  }

  /**
   * Test toCompany with a null CompanyDTO parameter.
   */
  @Test
  public void toCompanyNullParameterTest() {
    try {
      Company resultCompany = CompanyMapper.toCompany(null);
      assertNull(resultCompany);
    } catch (Throwable t) {
      fail("Exception thrown should not be thrown");
    }
  }

  /**
   * Test toCompany with an invalid CompanyDTO parameter.
   */
  @Test
  public void toCompanyInvalidCompanyDtoTest() {

    when(companyDto.getId()).thenReturn(-30L);
    when(companyDto.getName()).thenReturn(null);

    try {
      Company resultCompany = CompanyMapper.toCompany(companyDto);
      assertNotNull(resultCompany);
      assertEquals(resultCompany.getId(), -30L);
      assertEquals(resultCompany.getName(), null);
    } catch (Throwable t) {
      fail("Exception thrown should not be thrown");
    }
  }

  /**
   * Test toCompany with a valid CompanyDTO parameter.
   */
  @Test
  public void toCompanyValidCompanyDtoTest() {

    when(companyDto.getId()).thenReturn(6L);
    when(companyDto.getName()).thenReturn("apple");

    try {
      Company resultCompany = CompanyMapper.toCompany(companyDto);
      assertNotNull(resultCompany);
      assertEquals(resultCompany.getId(), 6L);
      assertEquals(resultCompany.getName(), "apple");
    } catch (Throwable t) {
      fail("Exception thrown should not be thrown");
    }
  }

  /**
   * Test toCompanyDTOList with a null parameter.
   */
  @Test
  public void toCompanyDtoListNullParamTest() {

    try {
      List<CompanyDto> resultCompany = CompanyMapper.toCompanyDtoList(null);
      assertNull(resultCompany);
    } catch (Throwable t) {
      fail("Exception thrown should not be thrown");
    }
  }

  /**
   * Test toCompanyDTOList with an invalid list company parameter parameter.
   */
  @Test
  public void toCompanyDtoListInvalidParamTest() {

    long id = -1L;
    String name = null;

    when(company.getId()).thenReturn(id);
    when(company.getName()).thenReturn(name);
    List<Company> companies = new ArrayList<Company>();
    companies.add(null);
    companies.add(company);

    int count = companies.size();

    try {
      List<CompanyDto> resultCompanyDtos = CompanyMapper.toCompanyDtoList(companies);
      assertNull(resultCompanyDtos.get(0));
      assertEquals(count, resultCompanyDtos.size());
      assertEquals(id, resultCompanyDtos.get(1).getId());
      assertNull(resultCompanyDtos.get(1).getName());
    } catch (Throwable t) {
      fail("Exception thrown should not be thrown");
    }
  }

}
