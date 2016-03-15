package com.excilys.cdb.mappers;

import com.excilys.cdb.models.OrderBy;
import com.excilys.cdb.models.QueryPageParameter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort.Direction;

import javax.servlet.http.HttpServletRequest;

public class QueryPageParameterMapper {

  // Logger
  private static final Logger LOGGER = LoggerFactory.getLogger(QueryPageParameterMapper.class);

  /**
   * Creates the.
   *
   * @param pageStr the page index
   * @param pageSizeStr the page size
   * @param searchStr the search
   * @param orderByStr the order by
   * @param orderStr the order
   * @return the query page parameter
   */
  public static QueryPageParameter toQueryPageParameter(String pageStr, String pageSizeStr,
      String searchStr, String orderByStr, String orderStr) {

    int pageIndex = 0;
    int pageSize = 30;
    String search = "";
    OrderBy orderBy = OrderBy.id;
    Direction order = Direction.ASC;

    if (pageStr != null && !pageStr.isEmpty()) {
      pageIndex = Integer.parseInt(pageStr) - 1;
    }
    if (pageSizeStr != null && !pageStr.isEmpty()) {
      pageSize = Integer.parseInt(pageSizeStr);
    }
    if (searchStr != null) {
      search = searchStr;
    }
    if (orderByStr != null && !orderByStr.isEmpty() && OrderBy.valueOf(orderByStr) != null) {
      orderBy = OrderBy.valueOf(orderByStr);
    }
    if (orderStr != null && !orderStr.isEmpty() && Direction.valueOf(orderStr) != null) {
      order = Direction.valueOf(orderStr);
    }

    return toQueryPageParameter(pageIndex, pageSize, search, orderBy, order);

  }

  /**
   * To query page parameter.
   *
   * @param pageIndex the page index
   * @param pageSize the page size
   * @param searchStr the search str
   * @param orderBy the order by
   * @param order the order
   * @return the query page parameter
   */
  public static QueryPageParameter toQueryPageParameter(int pageIndex, int pageSize,
      String searchStr, OrderBy orderBy, Direction order) {

    // Retrieve related dashboard page
    QueryPageParameter qp =
        new QueryPageParameter(pageIndex, pageSize, order, orderBy.toString(), searchStr);
    LOGGER.debug(
        "\n\t\tMapper: Map: [index:{} , size:{}, search:{}, orderby:{} , order:{}]\n\t\tTO:{}",
        pageIndex, pageSize, searchStr, orderBy, order, qp);
    return qp;
  }

  /**
   * Creates the querypageParameter to send to services.
   *
   * @param request the request that contains page info
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
