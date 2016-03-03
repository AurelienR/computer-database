package com.excilys.cdb.servlets;

import com.excilys.cdb.dtos.CompanyDto;
import com.excilys.cdb.services.CompanyDtoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class NewComputerServlet
 * Servlet in charge of prepare new computer view.
 */
@WebServlet("/newComputer")
public class NewComputerView extends HttpServlet {
  
  // Services
  @Autowired
  CompanyDtoService companyDtoService;
  
  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The Constant ADD_COMPUTER_URI. */
  private static final String ADD_COMPUTER_URI = "/views/addComputer.jsp";

  /**
   * Instantiates a new new computer view.
   *
   * @see HttpServlet#HttpServlet()
   */
  public NewComputerView() {
    super();
  }
  
  @Override
  public void init() throws ServletException {
    super.init();
    // Inject to spring context
    SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
  }

  /**
   * Do get.
   *
   * @param request the request
   * @param response the response
   * @throws ServletException the servlet exception
   * @throws IOException Signals that an I/O exception has occurred.
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    // Retrieve companyDTOs list
    List<CompanyDto> companyDtos = companyDtoService.findAll();

    // Prepare request
    request.setAttribute("companies", companyDtos);

    // Forward to new computer view
    request.getRequestDispatcher(ADD_COMPUTER_URI).forward(request, response);
  }

  /**
   * Do post.
   *
   * @param request the request
   * @param response the response
   * @throws ServletException the servlet exception
   * @throws IOException Signals that an I/O exception has occurred.
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doGet(request, response);
  }

}
