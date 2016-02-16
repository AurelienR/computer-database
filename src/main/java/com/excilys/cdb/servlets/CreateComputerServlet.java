package com.excilys.cdb.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.cdb.dtos.CompanyDTO;
import com.excilys.cdb.dtos.ComputerDTO;
import com.excilys.cdb.mappers.ComputerMapper;
import com.excilys.cdb.models.Computer;
import com.excilys.cdb.services.ComputerService;

/**
 * Servlet implementation class CreateComputerServlet
 */
@WebServlet("/createComputer") 
public class CreateComputerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateComputerServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Retrieve Computer information
		int id = -1;
		String nameStr = request.getParameter("computerName");
		String introducedStr = request.getParameter("introduced");
		String discontinuedStr = request.getParameter("discontinued");
		int companyId = Integer.parseInt(request.getParameter("companyId"));
		
		// Instanciate related ComputerDTO
		CompanyDTO companyDTO =  new CompanyDTO(companyId, "");		
		ComputerDTO computerDTO = new ComputerDTO(id, nameStr, introducedStr, discontinuedStr, companyDTO);
		
		// Map computerDTO to computer
		Computer computer = ComputerMapper.toComputer(computerDTO);
		
		// Add computer to DB
		ComputerService.getInstance().createComputer(computer);
		
		// Forward toDashboard
		request.getRequestDispatcher(ComputerInfoServlet.LIST_COMPUTERS_URI).forward(request, response);
	}
		
}
