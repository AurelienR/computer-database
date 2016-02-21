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
 * Servlet implementation class ComputerEdition
 */
@WebServlet("/editComputer") 
public class ComputerEdition extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ComputerEdition() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Retrieve Computer information
		int id = Integer.parseInt(request.getParameter("id"));		
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
		ComputerDTOService.getInstance().updateComputer(computerDTO);
		
		// Forward toDashboard
		response.sendRedirect("./computers");
	}

}
