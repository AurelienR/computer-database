package com.excilys.cli.impl;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import com.excilys.cli.Command;
import com.excilys.dao.DAOException;
import com.excilys.dao.ConnectionFactory;
import com.excilys.models.Company;
import com.excilys.models.Computer;

/**
 * Manage commandline to add a computer 
 * @author Aurelien.R
 *
 */
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
			sc.nextLine();
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
			while(!(companyName =sc.nextLine()).isEmpty() && ConnectionFactory.getInstance().getCompanyDAO().findByName(companyName).isEmpty()){
				System.out.println("Company Name is not matching with an existring one");
			}
			Company company = ConnectionFactory.getInstance().getCompanyDAO().findByName(companyName).get(0);
			computer.setCompany(company);
			
			ConnectionFactory.getInstance().getComputerDAO().insertComputer(computer);
			
		} catch (ParseException e) {
			System.out.println("Cannot parse date correctly");
			System.out.println("Message: "+ e.getMessage());
		} catch (IllegalArgumentException e){
			System.out.println("Invalid argument");
			System.out.println("Message: "+ e.getMessage());
		} catch (DAOException e){
			System.out.println("DAO issue");
			System.out.println("Message: "+ e.getMessage());
		}
		
	}

}
