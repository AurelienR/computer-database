package cli;

import dao.DAOException;
import dao.DAOFactory;
import models.Computer;

public class DisplayAllComputerCmd implements Command {
	
	DisplayAllComputerCmd(){};

	@Override
	public void execute() {
		System.out.println("Display all computers...");
		try{
			for(Computer c:DAOFactory.getInstance().getComputerDAO().findAll()){
				System.out.println(c);
			}
		}catch(DAOException e){
			System.out.println("Error cannot display computers");
			System.out.println("Message: "+ e.getMessage());
		}
	}
}
