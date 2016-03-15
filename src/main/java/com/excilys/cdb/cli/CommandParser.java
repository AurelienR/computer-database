package com.excilys.cdb.cli;

import com.excilys.cdb.cli.impl.CreateComputerCmd;
import com.excilys.cdb.cli.impl.DeleteCompanyCmd;
import com.excilys.cdb.cli.impl.DeleteComputerCmd;
import com.excilys.cdb.cli.impl.DisplayAllCompanyCmd;
import com.excilys.cdb.cli.impl.DisplayAllComputerCmd;
import com.excilys.cdb.cli.impl.DisplayComputerDetailsCmd;
import com.excilys.cdb.cli.impl.ExitCmd;
import com.excilys.cdb.cli.impl.UpdateComputerCmd;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Scanner;

// TODO: Auto-generated Javadoc
/**
 * Commandline parser that manage client entries.
 *
 * @author Aurelien.R
 */
public class CommandParser {

  // Logger
  static final Logger logger = LoggerFactory.getLogger(CommandParser.class);

  // Constants
  private static final String LIST_COMPANIES_CMD = "listcompanies";
  private static final String DELETE_COMPANY_CMD = "deletecompany";
  private static final String LIST_COMPUTERS_CMD = "listcomputers";
  private static final String GET_COMPUTER_DETAILS_CMD = "computerdetails";
  private static final String CREATE_COMPUTER_CMD = "createcomputer";
  private static final String UPDATE_COMPUTER_CMD = "updatecomputer";
  private static final String DELETE_COMPUTER_CMD = "deletecomputer";
  private static final String EXIT_CMD = "exit";

  // Attributes
  private CommandInvoker cmdInvoker;
  private ClassPathXmlApplicationContext ctx;
  private Scanner sc;

  /**
   * Instantiates a new command parser.
   */
  // Constructors
  public CommandParser() {
    this.cmdInvoker = new CommandInvoker();
    this.ctx = new ClassPathXmlApplicationContext("/spring/dao-context.xml");
    this.sc = new Scanner(System.in);
  }

  // Methods
  /**
   * Parse client input, and give instruction to follow.
   */
  public void parseCmd() {
    System.out.println("\nEnter a command:\n");
    logger.debug("Parsing command...");
    String cmdStr = sc.next();
    Command cmd;

    switch (cmdStr) {
      case LIST_COMPANIES_CMD:
        cmd = ctx.getBean(DisplayAllCompanyCmd.class);
        break;
      case DELETE_COMPANY_CMD:
        cmd = ctx.getBean(DeleteCompanyCmd.class, sc);
        break;
      case LIST_COMPUTERS_CMD:
        cmd = ctx.getBean(DisplayAllComputerCmd.class);
        break;
      case GET_COMPUTER_DETAILS_CMD:
        cmd = ctx.getBean(DisplayComputerDetailsCmd.class, sc);
        break;
      case CREATE_COMPUTER_CMD:
        cmd = ctx.getBean(CreateComputerCmd.class, sc);
        break;
      case UPDATE_COMPUTER_CMD:
        cmd = ctx.getBean(UpdateComputerCmd.class, sc);
        break;
      case DELETE_COMPUTER_CMD:
        cmd = ctx.getBean(DeleteComputerCmd.class, sc);
        break;
      case EXIT_CMD:
        cmd = ctx.getBean(ExitCmd.class);
        break;
      default:
        logger.warn("Command not found : " + cmdStr);
        return;
    }
    cmdInvoker.setCommand(cmd);
    cmdInvoker.invoke();
  }

  /**
   * Display in console all command line available for clients.
   */
  public void displayAvailableCmds() {
    StringBuilder sb =
        new StringBuilder("\n-----------------------------------------------------\n");
    sb.append("LIST OF COMMANDS:\n").append(LIST_COMPANIES_CMD).append('\n')
        .append(DELETE_COMPANY_CMD).append('\n').append(LIST_COMPUTERS_CMD).append('\n')
        .append(GET_COMPUTER_DETAILS_CMD).append('\n').append(CREATE_COMPUTER_CMD).append('\n')
        .append(UPDATE_COMPUTER_CMD).append('\n').append(DELETE_COMPUTER_CMD).append('\n')
        .append(EXIT_CMD).append('\n')
        .append("\n-----------------------------------------------------\n");
    System.out.println(sb);
  }

  /**
   * Display welcome message in console to the client.
   */
  public void welcome() {
    StringBuilder sb =
        new StringBuilder("\n************************************************************\n");
    sb.append("COMPUTER DATABASE").append('\n')
        .append("************************************************************").append('\n');
    System.out.println(sb);
  }
}
