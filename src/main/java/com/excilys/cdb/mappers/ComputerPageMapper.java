package com.excilys.cdb.mappers;

import java.util.List;

import com.excilys.cdb.dtos.ComputerDTO;
import com.excilys.cdb.dtos.ComputerPageDTO;
import com.excilys.cdb.models.Computer;
import com.excilys.cdb.models.ComputerPage;

public class ComputerPageMapper {

	public static ComputerPageDTO toComputerPageDTO(ComputerPage computerPage){
		List<Computer> computers = computerPage.getPage();
		List<ComputerDTO> computerDTOs = ComputerMapper.toComputerDTOList(computers);
		int pageCount = computerPage.getPageCount();
		return new ComputerPageDTO(computerPage.getPageIndex(),computerPage.getPageSize(),pageCount,computerDTOs);
	}
}
