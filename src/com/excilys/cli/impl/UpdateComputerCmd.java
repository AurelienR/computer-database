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

public class UpdateComputerCmd implements Command{
	
	private final String dateFormat ="dd/MM/yyyy";
	private Scanner sc;
	
	public UpdateComputerCmd(Scanner sc){
		this.sc = sc;		
	}
	
	@Override
	public void execute() {
		String oldComputerName;
		Computer newComputer;
		
		try{
			System.out.println("Update a computer:");
			System.out.println("Name of the computer to update:");
			while(ConnectionFactory.getInstance().getComputerDAO().findByName(oldComputerName= sc.next()).isEmpty()){
				System.out.println("Name entered is not matching with an existing computer");
			};
			newComputer = ConnectionFactory.getInstance().getComputerDAO().findByName(oldComputerName).get(0);
			
			
			System.out.println("New Name:");
			String newName;
			while((newName = sc.next()).isEmpty()){}
			newComputer.setName(newName);
			
			System.out.println("Introduced ("+dateFormat+"): ");
			sc.nextLine();
			String dateStr = sc.nextLine();
			if(dateStr != null && !dateStr.isEmpty()){
				Date discDate = new SimpleDateFormat(dateFormat).parse(dateStr);
				newComputer.setIntroduced(new Timestamp(discDate.getTime()));
			}

			
			System.out.println("Discontinued ("+dateFormat+"): ");
			dateStr = sc.nextLine();
			if(dateStr != null && !dateStr.isEmpty()){
				Date discDate = new SimpleDateFormat(dateFormat).parse(dateStr);
				newComputer.setDiscontinued(new Timestamp(discDate.getTime()));
			}
			
			System.out.println("CompanyName ( must match existring one): ");
			String companyName;
			while(!(companyName =sc.nextLine()).isEmpty() && ConnectionFactory.getInstance().getCompanyDAO().findByName(companyName).isEmpty()){
				System.out.println("Company Name is not matching with an existring one");
			}
			Company company = ConnectionFactory.getInstance().getCompanyDAO().findByName(companyName).get(0);
			newComputer.setCompany(company);
			
			ConnectionFactory.getInstance().getComputerDAO().updateComputer(newComputer);
			
			
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
