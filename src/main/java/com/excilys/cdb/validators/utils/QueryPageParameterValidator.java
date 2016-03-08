package com.excilys.cdb.validators.utils;

import com.excilys.cdb.models.Order;
import com.excilys.cdb.models.OrderBy;
import com.excilys.cdb.models.QueryPageParameter;
import com.excilys.cdb.validators.ValidatorException;

/**
 * Check QueryPageParameter state.
 *
 * @author Aurelien.R
 */
public class QueryPageParameterValidator {

  /**
   * Check page index.
   *
   * @param pageIndex          pageIndex to check
   * @throws ValidatorException           if pageIndex <1
   */
  public static void checkPageIndex(int pageIndex) throws ValidatorException {
    if (pageIndex < 1) {
      throw new ValidatorException("Page index cannot be less than 1, pageIndex: " + pageIndex);
    }
  }

  /**
   * Check pageSize.
   *
   * @param pageSize          pageSize to check
   * @throws ValidatorException           if pageSize > 1
   */
  public static void checkPageSize(int pageSize) throws ValidatorException {
    if (pageSize < 1) {
      throw new ValidatorException("Page size cannot be less than 1, pageSize : " + pageSize);
    }
  }

  /**
   * Check offset.
   *
   * @param offset          offset to check
   * @throws ValidatorException           if offset is <0
   */
  public static void checkOffset(int offset) throws ValidatorException {
    if (offset < 0) {
      throw new ValidatorException("Offset cannot be less than 0, offset: " + offset);
    }
  }

  /**
   * Check limit.
   *
   * @param limit          limit to check
   * @throws ValidatorException           if limit < 1
   */
  public static void checkLimit(int limit) throws ValidatorException {
    if (limit < 1) {
      throw new ValidatorException("Limit cannot be less than 1, limit : " + limit);
    }
  }

  /**
   * Check search field is not null.
   *
   * @param search          search field to check
   * @throws ValidatorException           if search is null
   */
  public static void checkSearch(String search) throws ValidatorException {
    if (search == null) {
      throw new ValidatorException("Search field string cannot be null");
    }
  }

  /**
   * Check orderBy field is no null.
   *
   * @param orderBy          orderBy field to check
   * @throws ValidatorException           if orderBy is null
   */
  public static void checkOrderBy(OrderBy orderBy) throws ValidatorException {
    if (orderBy == null) {
      throw new ValidatorException("OrderBy enum field cannot be null");
    }
  }

  /**
   * Check order field is not null.
   *
   * @param order          order to check
   * @throws ValidatorException           if order is null
   */
  public static void checkOrder(Order order) throws ValidatorException {
    if (order == null) {
      throw new ValidatorException("Order enum field cannot be null");
    }
  }

  /**
   * Check matchingrowCount.
   *
   * @param count          count of matching rows to check
   * @throws ValidatorException           if count is < 0
   */
  public static void checkMatchingRowCount(long count) throws ValidatorException {
    if (count < 0) {
      throw new ValidatorException("MatchingRowCount cannot be a negative number: " + count);
    }
  }

  /**
   * Validate the state of QueryPageParameter.
   *
   * @param qp          queryParameter to check
   * @throws ValidatorException           if invalid QueryPageParameter state
   */
  public static void validate(QueryPageParameter qp) throws ValidatorException {
    checkPageIndex(qp.getPageIndex());
    checkPageSize(qp.getPageSize());
    checkOffset(qp.getOffset());
    checkLimit(qp.getLimit());
    checkSearch(qp.getSearch());
    checkOrderBy(qp.getOrderBy());
    checkOrder(qp.getOrder());
    checkMatchingRowCount(qp.getMatchingRowCount());
  }
}
