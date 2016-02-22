package com.excilys.cdb.servlets;

import com.excilys.cdb.dtos.CompanyDto;
import com.excilys.cdb.dtos.ComputerDto;
import com.excilys.cdb.services.ComputerDtoService;

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

  /**
   * @see HttpServlet#HttpServlet().
   */
  public ComputerCreation() {
    super();
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

    // Retrieve Computer information
    int id = 0;
    String nameStr = request.getParameter("computerName");
    String introducedStr = request.getParameter("introduced");
    String discontinuedStr = request.getParameter("discontinued");

    // Retrieve related Company
    int companyId;
    CompanyDto companyDto;
    try {
      companyId = Integer.parseInt(request.getParameter("companyId"));
      companyDto = new CompanyDto(companyId, null);
    } catch (NumberFormatException e) {
      companyDto = null;
    }

    // Instanciate related ComputerDTO
    ComputerDto computerDto = new ComputerDto(id, nameStr, introducedStr, discontinuedStr,
        companyDto);

    // Add computer to DB
    ComputerDtoService.getInstance().createComputer(computerDto);

    // Forward toDashboard
    request.getRequestDispatcher("./computers").forward(request, response);
  }

}
