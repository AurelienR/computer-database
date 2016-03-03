package com.excilys.cdb.servlets;

import com.excilys.cdb.dtos.ComputerDto;
import com.excilys.cdb.mappers.ComputerMapper;
import com.excilys.cdb.services.ComputerDtoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CreateComputerServlet.
 * Servlet to add a new computer to DB based on POST parameters.
 */
@WebServlet("/createComputer")
public class ComputerCreation extends HttpServlet {
  private static final long serialVersionUID = 1L;

  // Services
  @Autowired
  private ComputerDtoService computerDtoService;

  
  /**
   * @see HttpServlet#HttpServlet().
   */
  public ComputerCreation() {
    super();
  }

  @Override
  public void init() throws ServletException {
    super.init();
    // Inject to spring context
    SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
  }

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response).
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response).
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    // Map
    ComputerDto computerDto = ComputerMapper.toComputerDto(request);

    // Add computer to DB
    computerDtoService.createComputer(computerDto);

    // Forward toDashboard
    response.sendRedirect("./computers");
  }

}
