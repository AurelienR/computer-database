package com.excilys.cdb.models;

public class QueryPageParameterCreator {
	
	public static QueryPageParameter Create(String pageStr, String pageSizeStr, String searchStr, String orderByStr, String orderStr){
		
		// Retrieve related dashboard page
		QueryPageParameter qp = new QueryPageParameter();
		
		if (pageStr != null && !pageStr.isEmpty()){
			
			int pageIndex = Integer.parseInt(pageStr);
			 
			if(pageIndex < 1){
				pageIndex = 1;
			}
			qp.setPageIndex(pageIndex);
		}
		if (pageSizeStr != null && !pageStr.isEmpty()){
			
			int pageSize = Integer.parseInt(pageSizeStr);
			 
			if(pageSize < 1){
				pageSize = 30;
			}
			
			qp.setPageSize(pageSize);
		}
		if (searchStr != null && !searchStr.isEmpty()){
			qp.setSearch(searchStr);
		}
		if(orderByStr != null && !orderByStr.isEmpty()){
			qp.setOrderBy(OrderBy.valueOf(orderByStr));
		}
		if(orderStr != null && !orderStr.isEmpty()){
			qp.setOrder(Order.valueOf(orderStr));
		}
		
		return qp;
	}

	
}
