package com.excilys.cdb.dtos;

import com.excilys.cdb.models.OrderBy;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

import java.util.Iterator;
import java.util.List;

public class ComputerPageDto {
  public int current = 1;
  public int pageSize = 30;
  public int pageCount;
  public String search = "";
  public long matchingRowCount = 0;
  public String order = Direction.ASC.toString();
  public String orderBy = OrderBy.id.toString();
  public List<ComputerDto> computers;

  /**
   * Instantiates a new computer page dto.
   *
   * @param page Page result object of query
   */

  public ComputerPageDto(String search, Page<ComputerDto> page) {
    this.search = search;
    this.current = page.getNumber() + 1;
    this.pageSize = page.getSize();
    this.pageCount = page.getTotalPages();
    this.matchingRowCount = page.getTotalElements();

    Iterator<Order> itr = page.getSort().iterator();
    if (itr.hasNext()) {
      Order ord = itr.next();
      this.orderBy = OrderBy.valueOf(ord.getProperty()).toString();
      this.order = ord.getDirection().toString();
    }
    this.computers = page.getContent();
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

  public String getSearch() {
    return search;
  }

  public void setSearch(String search) {
    this.search = search;
  }

  public String getOrder() {
    return order;
  }

  public void setOrder(String order) {
    this.order = order;
  }

  public String getOrderBy() {
    return orderBy;
  }

  public void setOrderBy(String orderBy) {
    this.orderBy = orderBy;
  }

  public long getMatchingRowCount() {
    return matchingRowCount;
  }

  public void setMatchingRowCount(long matchingRowCount) {
    this.matchingRowCount = matchingRowCount;
  }
}
