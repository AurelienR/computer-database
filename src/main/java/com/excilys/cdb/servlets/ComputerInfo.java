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
import com.excilys.cdb.mappers.ComputerPageMapper;
import com.excilys.cdb.models.ComputerPage;
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
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String idStr;
		String pageStr;
		String pageSizeStr;
		// URL GET "/computers?id=..."
		if ((idStr = request.getParameter("id")) != null){
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
		else if((pageStr = request.getParameter("page")) != null && (pageSizeStr = request.getParameter("pageSize")) != null){
			
			// Retrieve parameters
			int pageIndex =  Integer.parseInt(pageStr);
			int pageSize = Integer.parseInt(pageSizeStr);
			
			// Retrieve related dashboard page
			ComputerPage page = new ComputerPage(pageIndex,pageSize);
			ComputerPageDTO pageDTO =  ComputerPageMapper.toComputerPageDTO(page);
			
			// Get computer count
			int computerCount = ComputerDTOService.getInstance().count();
			
			// Prepare request
			request.setAttribute("page", pageDTO);
			request.setAttribute("computerCount", computerCount);
			
			// Forward to jsp
			request.getRequestDispatcher(LIST_COMPUTERS_URI).forward(request, response);
		}
		// URL: GET "/computers..."
		else{
			
			// Initialize dashboard Page
			int pageIndex =  1;
			int pageSize = 30;
			ComputerPage page = new ComputerPage(pageIndex,pageSize);
			ComputerPageDTO pageDTO = ComputerPageMapper.toComputerPageDTO(page);
			
			// Get computer count
			int computerCount = ComputerDTOService.getInstance().count();
			
			// Prepare request
			request.setAttribute("page", pageDTO);
			request.setAttribute("computerCount", computerCount);
			
			// Forward request
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
