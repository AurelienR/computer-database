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
 * Servlet implementation class ComputerEdition.
 */
@WebServlet("/editComputer")
public class ComputerEdition extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * @see HttpServlet#HttpServlet().
   */
  public ComputerEdition() {
    super();
  }

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response).
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    response.getWriter().append("Served at: ").append(request.getContextPath());
  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response).
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    // Retrieve Computer information
    int id = Integer.parseInt(request.getParameter("id"));
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
    ComputerDtoService.getInstance().updateComputer(computerDto);

    // Forward toDashboard
    response.sendRedirect("./computers");
  }

}
