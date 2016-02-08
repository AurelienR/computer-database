package cli;

import java.util.Scanner;

import dao.DAOException;
import dao.DAOFactory;
import models.Computer;

public class DisplayComputerDetailsCmd implements Command {
	
	private Scanner sc;
	
	public DisplayComputerDetailsCmd(Scanner sc) {
		this.sc = sc;
	};
	
	@Override
	public void execute() {
		try {
			System.out.println("Find computer details:");
			System.out.println("Name (of existing computer):");
			String computerName;
			
			while(!(computerName =sc.nextLine()).isEmpty() && DAOFactory.getInstance().getComputerDAO().findByName(computerName).isEmpty()){
				System.out.println("Computer Name is not matching with an existring one");
			}
			
			if(computerName.isEmpty())return;
			
			for(Computer c : DAOFactory.getInstance().getComputerDAO().findByName(computerName)){
				System.out.println(c);
			}
			
		} catch (IllegalArgumentException e){
			System.out.println("Invalide argument");
			System.out.println("Message: "+ e.getMessage());
		} catch (DAOException e){
			System.out.println("DAO issue");
			System.out.println("Message: "+ e.getMessage());
		}
	}
}
