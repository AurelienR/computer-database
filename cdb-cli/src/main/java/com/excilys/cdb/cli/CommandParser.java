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
  private Scanner sc;

  /**
   * Instantiates a new command parser.
   */
  // Constructors
  public CommandParser() {
    this.cmdInvoker = new CommandInvoker();
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
        cmd = new DisplayAllCompanyCmd();
        break;
      case DELETE_COMPANY_CMD:
        cmd = new DeleteCompanyCmd(sc);
        break;
      case LIST_COMPUTERS_CMD:
        cmd = new DisplayAllComputerCmd(sc);
        break;
      case GET_COMPUTER_DETAILS_CMD:
        cmd = new DisplayComputerDetailsCmd(sc);
        break;
      case CREATE_COMPUTER_CMD:
        cmd = new CreateComputerCmd(sc);
        break;
      case UPDATE_COMPUTER_CMD:
        cmd = new UpdateComputerCmd(sc);
        break;
      case DELETE_COMPUTER_CMD:
        cmd = new DeleteComputerCmd(sc);
        break;
      case EXIT_CMD:
        cmd = new ExitCmd();
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
        new StringBuilder("\n****************************************************************************************\n");
    sb.append("*\tLIST OF COMMANDS:\n")
    .append("*\t\t").append(LIST_COMPANIES_CMD).append('\n')
    .append("*\t\t").append(DELETE_COMPANY_CMD).append('\n')
    .append("*\t\t").append(LIST_COMPUTERS_CMD).append('\n')
    .append("*\t\t").append(GET_COMPUTER_DETAILS_CMD).append('\n')
    .append("*\t\t").append(CREATE_COMPUTER_CMD).append('\n')
    .append("*\t\t").append(UPDATE_COMPUTER_CMD).append('\n')
    .append("*\t\t").append(DELETE_COMPUTER_CMD).append('\n')
    .append("*\t\t").append(EXIT_CMD).append('\n')
        .append("****************************************************************************************\n");
    System.out.println(sb);
  }

  /**
   * Display welcome message in console to the client.
   */
  public void welcome() {
    StringBuilder sb =
        new StringBuilder("\n****************************************************************************************\n");
    sb.append("                                                                                      ").append("\n")
.append("  ,ad8888ba,   88           88                                       88  88           ").append("\n")
.append(" d8\"'    `\"8b  88           88                                       88  88           ").append("\n")
.append("d8'            88           88                                       88  88           ").append("\n")
.append("88             88           88                   ,adPPYba,   ,adPPYb,88  88,dPPYba,   ").append("\n")
.append("88             88           88     aaaaaaaa     a8\"     \"\"  a8\"    `Y88  88P'    \"8a  ").append("\n")
.append("Y8,            88           88     \"\"\"\"\"\"\"\"     8b          8b       88  88       d8  ").append("\n")
.append(" Y8a.    .a8P  88           88                  \"8a,   ,aa  \"8a,   ,d88  88b,   ,a8\"  ").append("\n")
.append("  `\"Y8888Y\"'   88888888888  88                   `\"Ybbd8\"'   `\"8bbdP\"Y8  8Y\"Ybbd8\"'   ").append("\n")
.append("                                                                                      ").append("\n")
.append("                                                                                      ").append("\n")
.append("****************************************************************************************");
    System.out.println(sb);
  }
}
