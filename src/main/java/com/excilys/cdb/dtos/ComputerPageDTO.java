package com.excilys.cdb.dtos;

import java.util.List;

public class ComputerPageDTO {
	public int current;
	public int pageSize;
	public int pageCount;
	public List<ComputerDTO> computers;
	
	public ComputerPageDTO(int current, int pageSize, int pageCount, List<ComputerDTO> computers){
		this.current = current;
		this.pageSize = pageSize;
		this.pageCount = pageCount;
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
	
}
