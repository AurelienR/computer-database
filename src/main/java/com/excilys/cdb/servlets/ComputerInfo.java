package com.excilys.cdb.servlets;

import com.excilys.cdb.dtos.CompanyDto;
import com.excilys.cdb.dtos.ComputerDto;
import com.excilys.cdb.dtos.ComputerPageDto;
import com.excilys.cdb.mappers.ComputerPageMapper;
import com.excilys.cdb.mappers.QueryPageParameterMapper;
import com.excilys.cdb.models.QueryPageParameter;
import com.excilys.cdb.services.CompanyDtoService;
import com.excilys.cdb.services.ComputerDtoService;

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
 * Servlet implementation class ComputersServlet. Retrieve informations of one or all computers.
 */
@WebServlet("/computers")
public class ComputerInfo extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public static final String LIST_COMPUTERS_URI = "/views/dashboard.jsp";
  private static final String EDIT_COMPUTER_URI = "/views/editComputer.jsp";

  // Services
  @Autowired
  ComputerDtoService computerDtoService;
  @Autowired
  CompanyDtoService companyDtoService;

  /**
   * Instantiates a new computer info.
   *
   * @see HttpServlet#HttpServlet()
   */
  public ComputerInfo() {
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

    // Retrieve parameters
    String idStr = request.getParameter("id");

    if (idStr != null) {
      // URL GET "/computers?id=..."
      // Parse Id
      Long id = Long.parseLong(idStr);

      // Retrieve company et computers
      ComputerDto computerDto = computerDtoService.findById(id).get(0);
      List<CompanyDto> companyDtos = companyDtoService.findAll();

      // Prepare request
      request.setAttribute("companies", companyDtos);
      request.setAttribute("computer", computerDto);

      // Forward to jsp
      request.getRequestDispatcher(EDIT_COMPUTER_URI).forward(request, response);

    } else {
      // URL GET "/computers?page=...&pageSize=..."
      // Get related query
      QueryPageParameter qp = QueryPageParameterMapper.toQueryPageParameter(request);

      // Retrieve DTOs
      List<ComputerDto> computerDtos = computerDtoService.findByQuery(qp);

      // Get total matching computers
      qp.setMatchingRowCount(computerDtoService.count(qp));
      ComputerPageDto pageDto = ComputerPageMapper.toComputerPageDto(qp, computerDtos);

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
