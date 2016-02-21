package com.excilys.cdb.dtos;

import java.util.List;

import com.excilys.cdb.models.QueryPageParameter;

public class ComputerPageDTO {
	public int current = 1;
	public int pageSize = 30;
	public String orderBy = "id";
	public String order = "ASC";
	public String search = "";
	public int pageCount;
	public int matchingRowCount = 0;
	public List<ComputerDTO> computers;
	
	public ComputerPageDTO(QueryPageParameter qp, int pageCount ,List<ComputerDTO> computers){
		this.current = qp.getPageIndex();
		this.pageSize = qp.getPageSize();
		this.pageCount = pageCount;
		this.orderBy = qp.getOrderBy().name();
		this.order = qp.getOrder().name();
		this.search = qp.getSearch();
		this.matchingRowCount = qp.getMatchinRowCount();
		this.computers = computers;
	}
	
	public ComputerPageDTO(int pageCount, int matchingRowCount ,List<ComputerDTO> computers){
		this.pageCount = pageCount;
		this.matchingRowCount = matchingRowCount;
		this.computers = computers;
	}

	/**
	 * @return the current
	 */
	public int getCurrent() {
		return current;
	}

	/**
	 * @param current the current to set
	 */
	public void setCurrent(int current) {
		this.current = current;
	}

	/**
	 * @return the pageSize
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * @param pageSize the pageSize to set
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * @return the pageCount
	 */
	public int getPageCount() {
		return pageCount;
	}

	/**
	 * @param pageCount the pageCount to set
	 */
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	/**
	 * @return the computers
	 */
	public List<ComputerDTO> getComputers() {
		return computers;
	}

	/**
	 * @param computers the computers to set
	 */
	public void setComputers(List<ComputerDTO> computers) {
		this.computers = computers;
	}

	/**
	 * @return the orderBy
	 */
	public String getOrderBy() {
		return orderBy;
	}

	/**
	 * @param orderBy the orderBy to set
	 */
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	/**
	 * @return the order
	 */
	public String getOrder() {
		return order;
	}

	/**
	 * @param order the order to set
	 */
	public void setOrder(String order) {
		this.order = order;
	}

	/**
	 * @return the search
	 */
	public String getSearch() {
		return search;
	}

	/**
	 * @param search the search to set
	 */
	public void setSearch(String search) {
		this.search = search;
	}

	/**
	 * @return the matchingRowCount
	 */
	public int getMatchingRowCount() {
		return matchingRowCount;
	}

	/**
	 * @param matchingRowCount the matchingRowCount to set
	 */
	public void setMatchingRowCount(int matchingRowCount) {
		this.matchingRowCount = matchingRowCount;
	}
	
	
	
}
