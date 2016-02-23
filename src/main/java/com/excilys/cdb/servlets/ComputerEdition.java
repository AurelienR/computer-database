package com.excilys.cdb.servlets;

import com.excilys.cdb.dtos.ComputerDto;
import com.excilys.cdb.mappers.ComputerMapper;
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

    // Map
    ComputerDto computerDto = ComputerMapper.toComputerDto(request);

    // Add computer to DB
    ComputerDtoService.getInstance().updateComputer(computerDto);

    // Forward toDashboard
    response.sendRedirect("./computers");
  }

}
