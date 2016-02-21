package com.excilys.cdb.dtos;

import java.util.List;

import com.excilys.cdb.models.QueryPageParameter;

public class ComputerPageDTOCreator {

	public static ComputerPageDTO createPage(QueryPageParameter qp, List<ComputerDTO> computerDTOs){
			
		if(qp.getPageIndex() < 1){
			qp.setPageIndex(1);
		}
		if(qp.getPageSize() < 1){
			qp.setPageSize(30);
		}
		
		int count = getPageCount(qp.getPageSize(), qp.getMatchinRowCount());
		ComputerPageDTO computerPageDTO = new ComputerPageDTO(qp,count, computerDTOs);
		
		return computerPageDTO;		
	}
	
	private static int getPageCount(int pageSize, int totalRows){
		
		if ((totalRows % pageSize) == 0){
			return Math.floorDiv( totalRows , pageSize);
		}
		
		return Math.floorDiv( totalRows , pageSize) +1;
	}
}
