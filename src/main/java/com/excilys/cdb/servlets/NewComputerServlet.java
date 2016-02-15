package com.excilys.cdb.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.cdb.dtos.CompanyDTO;
import com.excilys.cdb.models.Company;
import com.excilys.cdb.services.CompanyService;

/**
 * Servlet implementation class NewComputerServlet
 */
@WebServlet("/newComputer") 
public class NewComputerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String ADD_COMPUTER_URI = "/views/addComputer.jsp";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewComputerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Company> companies = CompanyService.getInstance().findAll();
		List<CompanyDTO> companyDTOs = new ArrayList<CompanyDTO>();
		companies.parallelStream().forEachOrdered(c -> companyDTOs.add(c.toCompanyDTO()));		
		request.setAttribute("companies", companyDTOs);
		request.getRequestDispatcher(ADD_COMPUTER_URI).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
