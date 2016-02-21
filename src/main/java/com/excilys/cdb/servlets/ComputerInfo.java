package com.excilys.cdb.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.cdb.dtos.CompanyDTO;
import com.excilys.cdb.dtos.ComputerDTO;
import com.excilys.cdb.dtos.ComputerPageDTO;
import com.excilys.cdb.dtos.ComputerPageDTOCreator;
import com.excilys.cdb.models.QueryPageParameter;
import com.excilys.cdb.models.QueryPageParameterCreator;
import com.excilys.cdb.services.CompanyDTOService;
import com.excilys.cdb.services.ComputerDTOService;

/**
 * Servlet implementation class ComputersServlet
 * 
 * Retrieve informations of one or all computers
 */
@WebServlet("/computers") 
public class ComputerInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public static final String LIST_COMPUTERS_URI = "/views/dashboard.jsp";
	private static final String EDIT_COMPUTER_URI = "/views/editComputer.jsp";

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ComputerInfo() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Retrieve parameters
		String idStr  = request.getParameter("id");
		String pageStr = request.getParameter("page");
		String pageSizeStr = request.getParameter("pageSize");
		String orderByStr = request.getParameter("orderBy");
		String orderStr = request.getParameter("order");
		String searchStr = request.getParameter("search");
		
		
		// URL GET "/computers?id=..."
		if (idStr != null){
			// Parse Id
			int id = Integer.parseInt(idStr);
			
			// Retrieve company et computers
			ComputerDTO computerDTO = ComputerDTOService.getInstance().findById(id).get(0);
			List<CompanyDTO> companyDTOs = CompanyDTOService.getInstance().findAll();
			
			// Prepare request
			request.setAttribute("companies", companyDTOs);
			request.setAttribute("computer", computerDTO);
			
			// Forward to jsp
			request.getRequestDispatcher(EDIT_COMPUTER_URI).forward(request, response);
		}
		// URL GET "/computers?page=...&pageSize=..."
		else {

			// Get related query
			QueryPageParameter qp = QueryPageParameterCreator.Create(pageStr, pageSizeStr, searchStr, orderByStr, orderStr);
			
			// Retrieve DTOs
			List<ComputerDTO> computerDTOs = ComputerDTOService.getInstance().findByQuery(qp);
			ComputerPageDTO pageDTO =  ComputerPageDTOCreator.createPage(qp, computerDTOs);
			
			// Prepare request
			request.setAttribute("page", pageDTO);
			
			// Forward to jsp
			request.getRequestDispatcher(LIST_COMPUTERS_URI).forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
