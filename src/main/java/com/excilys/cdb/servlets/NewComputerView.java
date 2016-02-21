package com.excilys.cdb.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.cdb.dtos.CompanyDTO;
import com.excilys.cdb.services.CompanyDTOService;

/**
 * Servlet implementation class NewComputerServlet
 * 
 * Servlet in charge of prepare new computer view
 */
@WebServlet("/newComputer") 
public class NewComputerView extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String ADD_COMPUTER_URI = "/views/addComputer.jsp";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewComputerView() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Retrieve companyDTOs list
		List<CompanyDTO> companyDTOs = CompanyDTOService.getInstance().findAll();

		// Prepare request
		request.setAttribute("companies", companyDTOs);
		
		// Forward to new computer view
		request.getRequestDispatcher(ADD_COMPUTER_URI).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
