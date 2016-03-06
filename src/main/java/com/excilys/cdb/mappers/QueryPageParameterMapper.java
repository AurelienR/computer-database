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
     *            the page index
     * @param pageSizeStr
     *            the page size
     * @param searchStr
     *            the search
     * @param orderByStr
     *            the order by
     * @param orderStr
     *            the order
     * @return the query page parameter
     */
    public static QueryPageParameter toQueryPageParameter(String pageStr, String pageSizeStr, String searchStr,
	    String orderByStr, String orderStr) {

	int pageIndex = 1;
	int pageSize = 30;
	
	if (pageStr != null && !pageStr.isEmpty()) {
	    pageIndex = Integer.parseInt(pageStr);	   
	}
	
	if (pageSizeStr != null && !pageStr.isEmpty()) {
	    pageSize = Integer.parseInt(pageSizeStr);	    
	}

	return toQueryPageParameter(pageIndex, pageSize, searchStr, orderByStr, orderStr);
	
    }

    public static QueryPageParameter toQueryPageParameter(int pageIndex, int pageSize, String searchStr,
	    String orderByStr, String orderStr) {

	// Retrieve related dashboard page
	QueryPageParameter qp = new QueryPageParameter();

	if (pageIndex >= 1) {
	    qp.setPageIndex(pageIndex);
	}

	if (pageSize >= 1) {
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

	logger.debug("\n\t\tMapper: Map: [index:" + pageIndex + " , size:" + pageSize + ", search:" + searchStr
		+ ", orderby:" + orderByStr + " , order:" + orderStr + "]" + "\n\t\tTO:" + qp);
	return qp;
    }

    /**
     * Creates the querypageParameter to send to services.
     *
     * @param request
     *            the request that contains page info
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
