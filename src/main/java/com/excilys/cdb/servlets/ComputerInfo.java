package com.excilys.cdb.servlets;

import com.excilys.cdb.dtos.CompanyDto;
import com.excilys.cdb.dtos.ComputerDto;
import com.excilys.cdb.dtos.ComputerPageDto;
import com.excilys.cdb.dtos.ComputerPageDtoCreator;
import com.excilys.cdb.models.QueryPageParameter;
import com.excilys.cdb.models.QueryPageParameterCreator;
import com.excilys.cdb.services.CompanyDtoService;
import com.excilys.cdb.services.ComputerDtoService;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ComputersServlet.
 * Retrieve informations of one or all computers.
 */
@WebServlet("/computers")
public class ComputerInfo extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public static final String LIST_COMPUTERS_URI = "/views/dashboard.jsp";
  private static final String EDIT_COMPUTER_URI = "/views/editComputer.jsp";

  /**
   * Instantiates a new computer info.
   *
   * @see HttpServlet#HttpServlet()
   */
  public ComputerInfo() {
    super();
  }

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response).
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    // Retrieve parameters
    String idStr = request.getParameter("id");
    String pageStr = request.getParameter("page");
    String pageSizeStr = request.getParameter("pageSize");
    String orderByStr = request.getParameter("orderBy");
    String orderStr = request.getParameter("order");
    String searchStr = request.getParameter("search");

    if (idStr != null) {
      // URL GET "/computers?id=..."
      // Parse Id
      int id = Integer.parseInt(idStr);

      // Retrieve company et computers
      ComputerDto computerDto = ComputerDtoService.getInstance().findById(id).get(0);
      List<CompanyDto> companyDtos = CompanyDtoService.getInstance().findAll();

      // Prepare request
      request.setAttribute("companies", companyDtos);
      request.setAttribute("computer", computerDto);

      // Forward to jsp
      request.getRequestDispatcher(EDIT_COMPUTER_URI).forward(request, response);
    } else {
      // URL GET "/computers?page=...&pageSize=..."
      // Get related query
      QueryPageParameter qp = QueryPageParameterCreator.create(pageStr, pageSizeStr, searchStr,
          orderByStr, orderStr);

      // Retrieve DTOs
      List<ComputerDto> computerDtos = ComputerDtoService.getInstance().findByQuery(qp);
      ComputerPageDto pageDto = ComputerPageDtoCreator.createPage(qp, computerDtos);

      // Prepare request
      request.setAttribute("page", pageDto);

      // Forward to jsp
      request.getRequestDispatcher(LIST_COMPUTERS_URI).forward(request, response);
    }
  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response).
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doGet(request, response);
  }
}
