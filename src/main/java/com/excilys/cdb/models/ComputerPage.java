package com.excilys.cdb.models;

import java.util.List;

import com.excilys.cdb.dao.impl.ComputerDAOImpl;

public class ComputerPage {
	private int pageIndex;
	private int pageSize;
	
	public ComputerPage(int pageIndex, int pageSize){
		this.pageIndex = pageIndex;
		this.pageSize = pageSize;
	}
	
	/**
	 * @return the pageIndex
	 */
	public int getPageIndex() {
		return pageIndex;
	}

	/**
	 * @param pageIndex the pageIndex to set
	 */
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
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
		
	public List<Computer> getPage(){
		return getPage(pageIndex, pageSize);
	}
	
	public int getPageCount(){
		return getPageCount(pageSize);
	}
	
	/**
	 * Get a page by its index base on pageSize attribute set
	 * @param pageIndex pageIndex of the page to return
	 * @return list of rows related to the pageIndex
	 */
	public static List<Computer> getPage(int pageIndex, int pageSize){
        int fromIndex = (pageIndex-1) * pageSize;
        if (fromIndex < 0){
        	 fromIndex = 0;
        }
        return ComputerDAOImpl.getInstance().findRange(fromIndex, pageSize);
	}
	
	
	/**
	 * Get the pages count based on list and page size attributes
	 * @return number of pages
	 */
	public static int getPageCount(int pageSize){
		return Math.floorDiv(ComputerDAOImpl.getInstance().findAll().size() , pageSize);
	}
}

