package com.excilys.cdb.cli;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.excilys.cdb.models.Company;
import com.excilys.cdb.models.Computer;
import com.excilys.cdb.services.CompanyService;
import com.excilys.cdb.services.ComputerService;

public class InputCommandParser {

	public static String getNameInput(Scanner sc) {
		String name;
		while ((name = sc.next()) == null || name.isEmpty()) {
			System.out.println("Invalid input, retry Name:");
		}
		return name;
	}

	public static LocalDateTime getDateInput(Scanner sc, String dateFormat) throws ParseException{
		String dateStr;
		while(!(dateStr = sc.nextLine()).isEmpty() && !isValidDate(dateStr,dateFormat)){
			System.out.println("Invalid input, retry Date:");
		}
		Date date = new SimpleDateFormat(dateFormat).parse(dateStr);	
		return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
	}
	
	public static List<Company> getValidCompanyByName(Scanner sc){
		String companyName;
		while(!(companyName =sc.nextLine()).isEmpty() && CompanyService.getInstance().findByName(companyName).isEmpty()){
			System.out.println("No Company Name matching, retry Company Name:");
		}
		return CompanyService.getInstance().findByName(companyName);
	}
	
	public static List<Computer> getValidComputerByName(Scanner sc){
		String computerName;
		while(!(computerName =sc.nextLine()).isEmpty() && ComputerService.getInstance().findByName(computerName).isEmpty()){
			System.out.println("No Computer Name matching, retry Company Name:");
		}
		return ComputerService.getInstance().findByName(computerName);
	}

	private static boolean isValidDate(String dateStr, String dateFormatStr) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormatStr);
		dateFormat.setLenient(false);
		try {
			dateFormat.parse(dateStr.trim());
		} catch (ParseException pe) {
			return false;
		}
		return true;
	}
}
