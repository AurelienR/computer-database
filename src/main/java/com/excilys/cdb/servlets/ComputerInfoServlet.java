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
import com.excilys.cdb.mappers.CompanyMapper;
import com.excilys.cdb.mappers.ComputerMapper;
import com.excilys.cdb.mappers.ComputerPageMapper;
import com.excilys.cdb.models.Company;
import com.excilys.cdb.models.ComputerPage;
import com.excilys.cdb.services.CompanyService;
import com.excilys.cdb.services.ComputerService;

/**
 * Servlet implementation class ComputersServlet
 */
@WebServlet("/computers") 
public class ComputerInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public static final String LIST_COMPUTERS_URI = "/views/dashboard.jsp";
	private static final String EDIT_COMPUTER_URI = "/views/editComputer.jsp";

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ComputerInfoServlet() {
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
		// if url type is GET "/computers?id=..."
		if ((idStr = request.getParameter("id")) != null){
			int id = Integer.parseInt(idStr);
			ComputerDTO computerDTO = ComputerMapper.toComputerDTO(ComputerService.getInstance().findById(id).get(0));
			
			List<Company> companies = CompanyService.getInstance().findAll();
			List<CompanyDTO> companyDTOs = CompanyMapper.toCompanyDTOList(companies);
			
			request.setAttribute("companies", companyDTOs);
			request.setAttribute("computer", computerDTO);
			request.getRequestDispatcher(EDIT_COMPUTER_URI).forward(request, response);
		}
		// if url type is GET "/computers?page=...&pageSize=..."
		else if((pageStr = request.getParameter("page")) != null && (pageSizeStr = request.getParameter("pageSize")) != null){
			int pageIndex =  Integer.parseInt(pageStr);
			int pageSize = Integer.parseInt(pageSizeStr);
			
			ComputerPage page = new ComputerPage(pageIndex,pageSize);
			ComputerPageDTO pageDTO =  ComputerPageMapper.toComputerPageDTO(page);
			int computerCount = ComputerService.getInstance().findAll().size();
			
			request.setAttribute("page", pageDTO);
			request.setAttribute("computerCount", computerCount);
			request.getRequestDispatcher(LIST_COMPUTERS_URI).forward(request, response);
		}
		// if url type is GET "/computers..."
		else{
			int pageIndex =  1;
			int pageSize = 30;
			ComputerPage page = new ComputerPage(pageIndex,pageSize);
			ComputerPageDTO pageDTO = ComputerPageMapper.toComputerPageDTO(page);
			int computerCount = ComputerService.getInstance().findAll().size();
			request.setAttribute("page", pageDTO);
			request.setAttribute("computerCount", computerCount);
			request.getRequestDispatcher(LIST_COMPUTERS_URI).forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
