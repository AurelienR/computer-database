package com.excilys.cdb.servlets;

import com.excilys.cdb.services.ComputerDtoService;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DeleteComputerServlet Servlet in charge of deleting computers.
 */
@WebServlet("/deleteComputer")
public class ComputerDeletion extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * @see HttpServlet#HttpServlet().
   */
  public ComputerDeletion() {
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

    // Retrieve parameters
    String idsStr = request.getParameter("selection");
    String[] ids = idsStr.split(",");

    // Delete each computer by id
    for (String idStr : ids) {
      // Car can throw exception
      int id = Integer.parseInt(idStr);
      ComputerDtoService.getInstance().deleteComputer(id);
    }

    // Forward request
    request.getRequestDispatcher("./computers").forward(request, response);
  }

}
