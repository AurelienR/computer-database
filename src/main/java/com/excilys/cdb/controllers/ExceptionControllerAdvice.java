package com.excilys.cdb.controllers;

import com.excilys.cdb.daos.DaoException;
import com.excilys.cdb.utils.DateFormatManagerException;
import com.excilys.cdb.validators.ValidatorException;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.PrintWriter;
import java.io.StringWriter;

@ControllerAdvice
@RequestMapping("/")
public class ExceptionControllerAdvice {

  /**
   * Default exception page dispatcher.
   *
   * @param ex exception object
   * @param model page model
   * @return view name
   */
  @ExceptionHandler(Exception.class)
  public String exception(Exception ex, Model model) {
    setExceptionModel(ex, model);
    model.addAttribute("message", "Unknown exception error");
    return "errors/500";
  }

  /**
   * Date Format exception page dispatcher.
   *
   * @param ex exception object
   * @param model page model
   * @return view name
   */
  @ExceptionHandler(DateFormatManagerException.class)
  public String dateException(Exception ex, Model model) {
    setExceptionModel(ex, model);
    model.addAttribute("message", "Invalid date format");
    return "errors/500";
  }

  /**
   * Dao exception page dispatcher.
   *
   * @param ex exception object
   * @param model page model
   * @return view name
   */
  @ExceptionHandler(DaoException.class)
  public String daoException(Exception ex, Model model) {
    setExceptionModel(ex, model);
    model.addAttribute("message", "Database operation error");
    return "errors/500";
  }

  /**
   * Validation exception page dispatcher.
   *
   * @param ex exception object
   * @param model page model
   * @return view name
   */
  @ExceptionHandler(ValidatorException.class)
  public String validationException(Exception ex, Model model) {
    setExceptionModel(ex, model);
    model.addAttribute("message", "Invalid data exception");
    return "errors/500";
  }

  private void setExceptionModel(Exception ex, Model model) {
    StringWriter sw = new StringWriter();
    ex.printStackTrace(new PrintWriter(sw));
    model.addAttribute("stackTrace", sw.toString());
    model.addAttribute("cause", ex.getCause());
  }
}
