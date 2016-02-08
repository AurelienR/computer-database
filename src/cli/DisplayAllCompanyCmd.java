package cli;

import dao.DAOException;
import dao.DAOFactory;
import models.Computer;

public class DisplayAllCompanyCmd implements Command{

	DisplayAllCompanyCmd(){};

	@Override
	public void execute() {
		System.out.println("Display all companiess...");
		try{
			for(Computer c:DAOFactory.getInstance().getComputerDAO().findAll()){
				System.out.println(c);
			}
		}catch(DAOException e){
			System.out.println("Error cannot display companiess");
			System.out.println("Message: "+ e.getMessage());
		}
	}

}
