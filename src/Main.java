import java.sql.Timestamp;
import java.util.Date;

import dao.DAOFactory;
import models.Company;
import models.Computer;

public class Main {

	public static void main(String[] args) {

		//Company custCompany = DAOFactory.getInstance().getCompanyDAO().findByName("CompanyTest").get(0);
		
// 		DAOFactory.getInstance().getCompanyDAO().insertCompany(custCompany);
//		Company getCompany = DAOFactory.getInstance().getCompanyDAO().findByName("CompanyTest").get(0);
//		
//		System.out.println("GetCompany: "+ getCompany);
//		
//		
//		for(Company c:DAOFactory.getInstance().getCompanyDAO().findAll()){
//			System.out.println(c);
//		}
//		
//		
//		
//		Computer custComputer = new Computer();
//		custComputer.setCompany(custCompany);
//		Date currDate = new Date();
//		custComputer.setIntroduced(new Timestamp(currDate.getTime()));
//		custComputer.setDiscontinued(new Timestamp(currDate.getTime()-5));
//		custComputer.setName("testComputer");
//		
//		DAOFactory.getInstance().getComputerDAO().insertComputer(custComputer);
//		Computer updComputer = DAOFactory.getInstance().getComputerDAO().findByName("testComputer").get(0);
//		updComputer.setName("tesUpdateComputer");
//		
//		DAOFactory.getInstance().getComputerDAO().updateComputer(updComputer);
//		
//		for(Computer c:DAOFactory.getInstance().getComputerDAO().findAll()){
//			System.out.println(c);
//		}
//		
//		DAOFactory.getInstance().getComputerDAO().deleteComputer(updComputer.getId());
//		
//		for(Computer c:DAOFactory.getInstance().getComputerDAO().findAll()){
//			System.out.println(c);
//		}
//		
//		for(Computer c:DAOFactory.getInstance().getComputerDAO().findAll()){
//			System.out.println(c);
//		}
//		Computer c = DAOFactory.getInstance().getComputerDAO().findByName("testComputer").get(0);
//		c.setName("testComputer");
//		DAOFactory.getInstance().getComputerDAO().deleteComputer(c.getId());
		//System.out.println("GET updated computer"+DAOFactory.getInstance().getComputerDAO().findById(c.getId()).get(0));
	}

}
