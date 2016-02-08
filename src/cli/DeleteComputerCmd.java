package cli;

import java.util.Scanner;

import dao.DAOFactory;
import models.Computer;

public class DeleteComputerCmd implements Command{
	
	private Scanner sc;
	
	public DeleteComputerCmd(Scanner sc){
		this.sc = sc;
	}

	@Override
	public void execute() {
		System.out.println("Delete computer:");
		System.out.println("Name (of existing computer):");
		String computerName;
		
		while(!(computerName =sc.nextLine()).isEmpty() && DAOFactory.getInstance().getComputerDAO().findByName(computerName).isEmpty()){
			System.out.println("Computer Name is not matching with an existring one");
		}
		
		if(computerName.isEmpty())return;
		
		for(Computer c : DAOFactory.getInstance().getComputerDAO().findByName(computerName)){
			DAOFactory.getInstance().getComputerDAO().deleteComputer(c.getId());
			System.out.println("Delete:   "+ c);
		}
		
	}

}
