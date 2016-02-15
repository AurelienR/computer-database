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

/**
 * Static methods to manage secure input and cli for client
 * @author Aurelien.R
 *
 */
public class InputCommandParser {
	
	//Methods
	/**
	 * Invite user to type a name, not required field
	 * @param sc system input scanner
	 * @return valid name from client input
	 */
	public static String getNameInput(Scanner sc) {
		String name = sc.next();
		return name;
	}

	/**
	 * Invite user to type a name until the field is not empty
	 * @param sc system input scanner
	 * @return valid name from client input
	 */
	public static String getRequiredNameInput(Scanner sc) {
		String name;
		while ((name = sc.next()) == null || name.isEmpty()) {
			System.out.println("Invalid input, field required ,retry Name:");
		}
		return name;
	}

	/**
	 * Invite user to enter a date to a specific format, user can skip this step
	 * @param sc system in scanner
	 * @param dateFormat dateFormat string expected
	 * @return related LocalDateTime instance
	 * @throws ParseException
	 */
	public static LocalDateTime getDateInput(Scanner sc, String dateFormat) throws ParseException {
		String dateStr;
		while (!(dateStr = sc.nextLine()).isEmpty() && !isValidDate(dateStr, dateFormat)) {
			System.out.println("Invalid input, retry Date:");
		}
		if (dateStr.isEmpty())
			return null;
		Date date = new SimpleDateFormat(dateFormat).parse(dateStr);
		return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
	}

	/**
	 * Invite user to enter a company name and retry until input is matching Company name database
	 * Can be skip by user
	 * @param sc system in scanner
	 * @return related list of company
	 */
	public static List<Company> getValidCompanyByName(Scanner sc) {
		String companyName;
		while (!(companyName = sc.nextLine()).isEmpty()
				&& CompanyService.getInstance().findByName(companyName).isEmpty()) {
			System.out.println("No Company Name matching, retry Company Name:");
		}
		if (companyName.isEmpty())
			return null;
		return CompanyService.getInstance().findByName(companyName);
	}

	/**
	 * Invite user to enter a company name and retry until input is matching Company name database
	 * Cannot be skip by user
	 * @param sc system in scanner
	 * @return related list of company
	 */
	public static List<Company> getRequiredValidCompanyByName(Scanner sc) {
		String companyName;
		while ((companyName = sc.nextLine()) == null || companyName.isEmpty()
				|| CompanyService.getInstance().findByName(companyName).isEmpty()) {
			System.out.println("No Company Name matching, required field ,retry Company Name:");
		}
		return CompanyService.getInstance().findByName(companyName);
	}

	/**
	 * Invite user to enter a computer name and retry until input is matching computer name database
	 * Can be skip by user
	 * @param sc system in scanner
	 * @return related list of computers
	 */
	public static List<Computer> getValidComputerByName(Scanner sc) {
		String computerName;
		while (!(computerName = sc.nextLine()).isEmpty()
				&& ComputerService.getInstance().findByName(computerName).isEmpty()) {
			System.out.println("No Computer Name matching, retry Computer Name:");
		}
		return ComputerService.getInstance().findByName(computerName);
	}

	/**
	 * Invite user to enter a computer name and retry until input is matching computer name database
	 * Cannot be skip by user
	 * @param sc system in scanner
	 * @return related list of computers
	 */
	public static List<Computer> getRequiredValidComputerByName(Scanner sc) {
		String computerName;
		while ((computerName = sc.nextLine()) == null || computerName.isEmpty()
				|| ComputerService.getInstance().findByName(computerName).isEmpty()) {
			System.out.println("No Computer Name matching, required field, retry Computer Name:");
		}
		return ComputerService.getInstance().findByName(computerName);
	}

	/**
	 * Check if the dateStr is matching the expected dateFormatStr format
	 * @param dateStr string to check
	 * @param dateFormatStr string format expected
	 * @return true if dateStr is matching dateFormatStr format, else return false
	 */
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