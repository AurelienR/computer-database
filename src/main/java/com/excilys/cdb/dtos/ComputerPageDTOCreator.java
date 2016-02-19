package com.excilys.cdb.dtos;

import java.util.List;

public class ComputerPageDTOCreator {

	public static ComputerPageDTO createPage(int pageIndex, int pageSize, int totalRows, List<ComputerDTO> computerDTOs){
			
		if(pageIndex < 1){
			pageIndex = 1;
		}
		if(pageSize < 1){
			pageSize = 30;
		}
		
		int count = getPageCount(pageSize, totalRows);
		ComputerPageDTO computerPageDTO = new ComputerPageDTO(pageIndex,pageSize,count, computerDTOs);
		
		return computerPageDTO;		
	}
	
	private static int getPageCount(int pageSize, int totalRows){
		
		if ((totalRows % pageSize) == 0){
			return Math.floorDiv( totalRows , pageSize);
		}
		
		return Math.floorDiv( totalRows , pageSize) +1;
	}
}
