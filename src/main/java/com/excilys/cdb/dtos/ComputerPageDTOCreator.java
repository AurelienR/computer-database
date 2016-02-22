package com.excilys.cdb.dtos;

import java.util.List;

import com.excilys.cdb.models.QueryPageParameter;

/**
 * Manage to initialize a ComputerPageDTO instance
 * @author excilys
 *
 */
public class ComputerPageDTOCreator {

	/**
	 * Initialize a ComputerPageDTO based on queryparameter
	 * @param qp queryParameter related to the page passed
	 * @param computerDTOs 
	 * @return
	 */
	public static ComputerPageDTO createPage(QueryPageParameter qp, List<ComputerDTO> computerDTOs){
		
		// Set up index by default if invalid
		if(qp.getPageIndex() < 1){
			qp.setPageIndex(1);
		}
		// Set up pageSize by default if invalid
		if(qp.getPageSize() < 1){
			qp.setPageSize(30);
		}
		
		// Calculate page count
		int count = getPageCount(qp.getPageSize(), qp.getMatchingRowCount());
		ComputerPageDTO computerPageDTO = new ComputerPageDTO(qp,count, computerDTOs);
		
		return computerPageDTO;		
	}
	
	
	/**
	 * Calculate the page count based on pageSize and pageIndex
	 * @param pageSize pageSize
	 * @param totalRows total rows matching
	 * @return count of pages
	 */
	private static int getPageCount(int pageSize, int totalRows){
		
		if ((totalRows % pageSize) == 0){
			return Math.floorDiv( totalRows , pageSize);
		}
		
		return Math.floorDiv( totalRows , pageSize) +1;
	}
}
