package com.excilys.cdb.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.cdb.dtos.CompanyDTO;
import com.excilys.cdb.dtos.ComputerDTO;
import com.excilys.cdb.services.ComputerDTOService;

/**
 * Servlet implementation class CreateComputerServlet
 * 
 * Servlet to add a new computer to DB based on POST parameters
 */
@WebServlet("/createComputer") 
public class ComputerCreation extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ComputerCreation() {
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
		int id = 0;
		String nameStr = request.getParameter("computerName");
		String introducedStr = request.getParameter("introduced");
		String discontinuedStr = request.getParameter("discontinued");
		
		// Retrieve related Company
		int companyId;		
		CompanyDTO companyDTO;
		try{
			companyId = Integer.parseInt(request.getParameter("companyId"));
			companyDTO =  new CompanyDTO(companyId, null);
		} catch(NumberFormatException e){
			companyDTO = null;
		}	
		
		// Instanciate related ComputerDTO
		ComputerDTO computerDTO = new ComputerDTO(id, nameStr, introducedStr, discontinuedStr, companyDTO);
		
		// Add computer to DB
		ComputerDTOService.getInstance().createComputer(computerDTO);
		
		// Forward toDashboard
		request.getRequestDispatcher("./computers").forward(request, response);
	}
		
}
