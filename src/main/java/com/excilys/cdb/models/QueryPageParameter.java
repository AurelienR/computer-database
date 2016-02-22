package com.excilys.cdb.models;

/**
 * Prepare SQL query parameters in order to perpare Computer page
 * 
 * @author AurelienR
 *
 */
public class QueryPageParameter {
	
	private int pageIndex = 1;
	private int pageSize = 30;
	private String search = "";
	private OrderBy orderBy = OrderBy.id;
	private Order order = Order.ASC;
	private int matchingRowCount = 0;
	
	
	public QueryPageParameter(){}


	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public String getSearch() {
		return search;
	}
	
	public String getQuerySearch() {
		return "%"+search+"%";
	}

	public void setSearch(String search) {
		this.search = search;
	}


	public OrderBy getOrderBy() {
		return orderBy;
	}


	public void setOrderBy(OrderBy orderBy) {
		this.orderBy = orderBy;
	}


	public Order getOrder() {
		return order;
	}


	public void setOrder(Order order) {
		this.order = order;
	}


	/**
	 * Calculate SQL offset based on pageIndex and pageSize
	 * @return offset
	 */
	public int getOffset() {			
		if(pageIndex < 1){
			pageIndex = 1;
		}		
		return (pageIndex - 1)* pageSize;
	}
	
	/**
	 * Return limit parameter for query
	 * @return limit
	 */
	public int getLimit() {
		return pageSize;
	}


	/**
	 * @return the matchinRowCount
	 */
	public int getMatchingRowCount() {
		return matchingRowCount;
	}


	/**
	 * @param matchinRowCount the matchinRowCount to set
	 */
	public void setMatchingRowCount(int matchinRowCount) {
		this.matchingRowCount = matchinRowCount;
	}			
	
}