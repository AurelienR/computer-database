package com.excilys.cdb.mappers;

import com.excilys.cdb.models.Order;
import com.excilys.cdb.models.OrderBy;
import com.excilys.cdb.models.QueryPageParameter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

public class QueryPageParameterMapper {

  // Logger
  static final Logger logger = LoggerFactory.getLogger(QueryPageParameterMapper.class);

  /**
   * Creates the.
   *
   * @param pageStr
   *          the page index
   * @param pageSizeStr
   *          the page size
   * @param searchStr
   *          the search
   * @param orderByStr
   *          the order by
   * @param orderStr
   *          the order
   * @return the query page parameter
   */
  public static QueryPageParameter toQueryPageParameter(String pageStr, String pageSizeStr,
      String searchStr, String orderByStr, String orderStr) {

    // Retrieve related dashboard page
    QueryPageParameter qp = new QueryPageParameter();

    if (pageStr != null && !pageStr.isEmpty()) {

      int pageIndex = Integer.parseInt(pageStr);

      if (pageIndex < 1) {
        pageIndex = 1;
      }
      qp.setPageIndex(pageIndex);
    }
    if (pageSizeStr != null && !pageStr.isEmpty()) {

      int pageSize = Integer.parseInt(pageSizeStr);

      if (pageSize < 1) {
        pageSize = 30;
      }

      qp.setPageSize(pageSize);
    }
    if (searchStr != null && !searchStr.isEmpty()) {
      qp.setSearch(searchStr);
    }
    if (orderByStr != null && !orderByStr.isEmpty()) {
      qp.setOrderBy(OrderBy.valueOf(orderByStr));
    }
    if (orderStr != null && !orderStr.isEmpty()) {
      qp.setOrder(Order.valueOf(orderStr));
    }

    logger.debug("\nMapper: Map: [index:" + pageStr + " , size:" + pageSizeStr + ", search:"
        + searchStr + ", orderby:" + orderByStr + " , order:" + orderStr + "]" + "\n TO:" + qp);

    return qp;
  }

  /**
   * Creates the querypageParameter to send to services.
   *
   * @param request
   *          the request that contains page info
   * @return the query page parameter
   */
  public static QueryPageParameter toQueryPageParameter(HttpServletRequest request) {

    // Retrieve parameters
    String pageStr = request.getParameter("page");
    String pageSizeStr = request.getParameter("pageSize");
    String orderByStr = request.getParameter("orderBy");
    String orderStr = request.getParameter("order");
    String searchStr = request.getParameter("search");

    return toQueryPageParameter(pageStr, pageSizeStr, searchStr, orderByStr, orderStr);
  }

}
