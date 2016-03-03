package com.excilys.cdb.cli;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Executer any command passed Command Pattern
 * 
 * @author Aurelien.R
 *
 */
public class CommandInvoker {
  // Logger
  static final Logger logger = LoggerFactory.getLogger(CommandInvoker.class);

  // Command to invoke
  Command command;

  /**
   * Set command that can be executed.
   * 
   * @param command
   *          command to invoke
   */
  public void setCommand(Command command) {
    logger.debug("Set command to invoke: " + command.getClass().getSimpleName());
    this.command = command;
  }

  /**
   * Execute the last command set.
   */
  public void invoke() {
    logger.debug("Execute command: " + command.getClass().getSimpleName());
    command.execute();
  }
}
