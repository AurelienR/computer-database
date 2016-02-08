package cli;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import dao.DAOException;
import dao.DAOFactory;
import models.Company;
import models.Computer;

public class CreateComputerCmd implements Command {
	
	private final String dateFormat ="dd/MM/yyyy";
	private Scanner sc;
	
	public CreateComputerCmd(Scanner sc) {
		this.sc = sc;
	};
	
	@Override
	public void execute() {
		Computer computer = new Computer();		

		try {
			System.out.println("Create a computer:");
			System.out.println("Name:");
			computer.setName(sc.next());
			
			System.out.println("Introduced ("+dateFormat+"): ");
			String dateStr = sc.nextLine();
			if(dateStr != null && !dateStr.isEmpty()){
				Date discDate = new SimpleDateFormat(dateFormat).parse(dateStr);
				computer.setIntroduced(new Timestamp(discDate.getTime()));
			}

			
			System.out.println("Discontinued ("+dateFormat+"): ");
			dateStr = sc.nextLine();
			if(dateStr != null && !dateStr.isEmpty()){
				Date discDate = new SimpleDateFormat(dateFormat).parse(dateStr);
				computer.setIntroduced(new Timestamp(discDate.getTime()));
			}
			
			System.out.println("CompanyName ( must match existring one): ");
			String companyName;
			while(!(companyName =sc.nextLine()).isEmpty() && DAOFactory.getInstance().getCompanyDAO().findByName(companyName).isEmpty()){
				System.out.println("Company Name is not matching with an existring one");
			}
			Company company = new Company();
			company.setName(companyName);
			computer.setCompany(company);
			
			DAOFactory.getInstance().getComputerDAO().insertComputer(computer);
			
		} catch (ParseException e) {
			System.out.println("Cannot parse date correctly");
			System.out.println("Message: "+ e.getMessage());
		} catch (IllegalArgumentException e){
			System.out.println("Invalide argument");
			System.out.println("Message: "+ e.getMessage());
		} catch (DAOException e){
			System.out.println("DAO issue");
			System.out.println("Message: "+ e.getMessage());
		}
		
	}

}
