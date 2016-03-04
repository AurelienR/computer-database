package com.excilys.cdb.models;

// TODO: Auto-generated Javadoc
/**
 * Prepare SQL query parameters in order to perpare Computer page.
 *
 * @author AurelienR
 */
public class QueryPageParameter {

  private int pageIndex = 1;
  private int pageSize = 30;
  private String search = "";
  private OrderBy orderBy = OrderBy.id;
  private Order order = Order.ASC;
  private long matchingRowCount = 0L;

  /**
   * Instantiates a new query page parameter.
   */
  public QueryPageParameter() {
  }

  /**
   * Gets the page index.
   *
   * @return the page index
   */
  public int getPageIndex() {
    return pageIndex;
  }

  /**
   * Sets the page index.
   *
   * @param pageIndex
   *          the new page index
   */
  public void setPageIndex(int pageIndex) {
    this.pageIndex = pageIndex;
  }

  /**
   * Gets the page size.
   *
   * @return the page size
   */
  public int getPageSize() {
    return pageSize;
  }

  /**
   * Sets the page size.
   *
   * @param pageSize
   *          the new page size
   */
  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
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
   * Gets the query search.
   *
   * @return the query search
   */
  public String getQuerySearch() {
    return "%" + search + "%";
  }

  /**
   * Sets the search.
   *
   * @param search
   *          the new search
   */
  public void setSearch(String search) {
    this.search = search;
  }

  /**
   * Gets the order by.
   *
   * @return the order by
   */
  public OrderBy getOrderBy() {
    return orderBy;
  }

  /**
   * Sets the order by.
   *
   * @param orderBy
   *          the new order by
   */
  public void setOrderBy(OrderBy orderBy) {
    this.orderBy = orderBy;
  }

  /**
   * Gets the order.
   *
   * @return the order
   */
  public Order getOrder() {
    return order;
  }

  /**
   * Sets the order.
   *
   * @param order
   *          the new order
   */
  public void setOrder(Order order) {
    this.order = order;
  }

  /**
   * Calculate SQL offset based on pageIndex and pageSize.
   *
   * @return offset
   */
  public int getOffset() {
    if (pageIndex < 1) {
      pageIndex = 1;
    }
    return (pageIndex - 1) * pageSize;
  }

  /**
   * Return limit parameter for query.
   *
   * @return limit
   */
  public int getLimit() {
    return pageSize;
  }

  /**
   * Gets the matching row count.
   *
   * @return the matchinRowCount
   */
  public long getMatchingRowCount() {
    return matchingRowCount;
  }

  /**
   * Sets the matching row count.
   *
   * @param matchinRowCount
   *          the matchinRowCount to set
   */
  public void setMatchingRowCount(long matchinRowCount) {
    this.matchingRowCount = matchinRowCount;
  }

  @Override
  public String toString() {
    return "QueryPageParameter [pageIndex=" + pageIndex + ", pageSize=" + pageSize + ", search="
        + search + ", orderBy=" + orderBy + ", order=" + order + ", matchingRowCount="
        + matchingRowCount + "]";
  }


}