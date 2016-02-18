package com.excilys.cdb.models;

public class QueryPageParameter {
	
	private int pageSize;
	private int pageIndex;
	
	public QueryPageParameter(int pageIndex, int pageSize){

		this.pageIndex = pageIndex;
		this.pageSize = pageSize;
	}


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
	
	public int getOffset(){		
		
		if(pageIndex < 1){
			pageIndex = 1;
		}		
		return (pageIndex - 1)* pageSize;
	}
	
	public int getLimit(){
		return pageSize;
	}			
}
