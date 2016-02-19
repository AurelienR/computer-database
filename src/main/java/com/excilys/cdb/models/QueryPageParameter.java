package com.excilys.cdb.models;

public class QueryPageParameter {
	
	private int pageIndex = 1;
	private int pageSize = 30;
	private String search = "%";
	private OrderBy orderBy = OrderBy.id;
	private Order order = Order.ASC;
	
	
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

	public void setSearch(String search) {
		this.search = "%"+search;
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


	public int getOffset() {			
		if(pageIndex < 1){
			pageIndex = 1;
		}		
		return (pageIndex - 1)* pageSize;
	}
	
	public int getLimit() {
		return pageSize;
	}			
}