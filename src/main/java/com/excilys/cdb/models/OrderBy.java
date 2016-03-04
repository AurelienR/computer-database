package com.excilys.cdb.models;

// TODO: Auto-generated Javadoc
/**
 * Enum of orderBy possibilities.
 *
 * @author excilys
 */
public enum OrderBy {

  /** The id. */
  id("computer.id"),
  /** The name. */
  name("computer.name"),
  /** The introduced. */
  introduced("computer.introduced"),
  /** The discontinued. */
  discontinued("computer.discontinued"),
  /** The company. */
  company("company.name");

  /** The text. */
  private final String text;

  /**
   * Instantiates a new order by.
   *
   * @param text the text
   */
  private OrderBy(String text) {
    this.text = text;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Enum#toString()
   */
  @Override
  public String toString() {
    return this.text;
  }
}
