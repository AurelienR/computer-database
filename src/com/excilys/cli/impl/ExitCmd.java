package com.excilys.cli.impl;

import com.excilys.cli.Command;

public class ExitCmd implements Command{
	
	public ExitCmd (){}

	@Override
	public void execute() {
		System.out.println("Exiting Computer Database");
		System.exit(0);		
	};
}
