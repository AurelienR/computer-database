package cli;

public class CommandInvoker {
	Command command;

	public void setCommand(Command command) {
		this.command = command;
	}
	
	public void invoke(){
		command.execute();
	}	
}
