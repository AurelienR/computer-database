package com.excilys.cdb.models;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

/**
 * Prepare SQL query parameters in order to perpare Computer page.
 *
 * @author AurelienR
 */
public class QueryPageParameter {

  Pageable pageable;
  private String search = "";

  /**
   * Instantiates a new query page parameter.
   */
  public QueryPageParameter(int page, int size, Direction direction, String orderBy,
      String search) {
    this.pageable = new PageRequest(page, size, new Sort(direction,orderBy));
    this.search = search;
  }

  /**
   * Gets the search.
   *
   * @return the search
   */
  public String getSearch() {
    return search;
  }


  /**
   * Gets the pageable.
   *
   * @return the pageable
   */
  public Pageable getPageable() {
    return pageable;
  }
}