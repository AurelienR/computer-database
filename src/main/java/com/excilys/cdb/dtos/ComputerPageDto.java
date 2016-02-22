package com.excilys.cdb.dtos;

import com.excilys.cdb.models.QueryPageParameter;

import java.util.List;

public class ComputerPageDto {
  public int current = 1;
  public int pageSize = 30;
  public String orderBy = "id";
  public String order = "ASC";
  public String search = "";
  public int pageCount;
  public int matchingRowCount = 0;
  public List<ComputerDto> computers;

  /**
   * Instantiates a new computer page dto.
   *
   * @param qp
   *          the queryPageParameter of the page
   * @param pageCount
   *          the page count
   * @param computers
   *          the computers
   */
  public ComputerPageDto(QueryPageParameter qp, int pageCount, List<ComputerDto> computers) {
    this.current = qp.getPageIndex();
    this.pageSize = qp.getPageSize();
    this.pageCount = pageCount;
    this.orderBy = qp.getOrderBy().name();
    this.order = qp.getOrder().name();
    this.search = qp.getSearch();
    this.matchingRowCount = qp.getMatchingRowCount();
    this.computers = computers;
  }

  /**
   * Instantiates a new computer page dto.
   *
   * @param pageCount
   *          the total page count
   * @param matchingRowCount
   *          the query matching row count
   * @param computers
   *          the related list of computers
   */
  public ComputerPageDto(int pageCount, int matchingRowCount, List<ComputerDto> computers) {
    this.pageCount = pageCount;
    this.matchingRowCount = matchingRowCount;
    this.computers = computers;
  }

  public int getCurrent() {
    return current;
  }

  public void setCurrent(int current) {
    this.current = current;
  }

  public int getPageSize() {
    return pageSize;
  }

  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }

  public int getPageCount() {
    return pageCount;
  }

  public void setPageCount(int pageCount) {
    this.pageCount = pageCount;
  }

  public List<ComputerDto> getComputers() {
    return computers;
  }

  public void setComputers(List<ComputerDto> computers) {
    this.computers = computers;
  }

  public String getOrderBy() {
    return orderBy;
  }

  public void setOrderBy(String orderBy) {
    this.orderBy = orderBy;
  }

  public String getOrder() {
    return order;
  }

  public void setOrder(String order) {
    this.order = order;
  }

  public String getSearch() {
    return search;
  }

  public void setSearch(String search) {
    this.search = search;
  }

  public int getMatchingRowCount() {
    return matchingRowCount;
  }

  public void setMatchingRowCount(int matchingRowCount) {
    this.matchingRowCount = matchingRowCount;
  }

}
