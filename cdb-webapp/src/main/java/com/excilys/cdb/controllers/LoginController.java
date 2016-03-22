package com.excilys.cdb.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {
  
  // Logger
  static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
  
  @ModelAttribute("user")
  public String getUsername() {
    return getPrincipal();
  }
  
  @RequestMapping(value = "/accessdenied", method = RequestMethod.GET)
  public String accessDeniedPage(ModelMap model) {
    LOGGER.info("Controller: GET /accessdenied");
    model.addAttribute("user", getPrincipal());
    return "errors/accessDenied";
  }

  @RequestMapping(value = "/login", method = RequestMethod.GET)
  public String loginPage() {
    LOGGER.info("Controller: GET /login");
    return "login";
  }

  @RequestMapping(value = "/logout", method = RequestMethod.GET)
  public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
    LOGGER.info("Controller: GET /logout");
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    if (auth != null) {
      new SecurityContextLogoutHandler().logout(request, response, auth);
    }
    return "redirect:/login?logout";
  }

  public static String getPrincipal() {
    String userName = null;
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    if (principal instanceof UserDetails) {
      userName = ((UserDetails) principal).getUsername();
    } else {
      userName = principal.toString();
    }
    LOGGER.info("Controller: retrieve current user context - Username: {}",userName);
    return userName;
  }
}
