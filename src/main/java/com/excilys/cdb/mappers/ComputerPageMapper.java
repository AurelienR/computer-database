package com.excilys.cdb.mappers;

import com.excilys.cdb.dtos.ComputerDto;
import com.excilys.cdb.dtos.ComputerPageDto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;

/**
 * Manage to initialize a ComputerPageDTO instance.
 *
 * @author excilys
 */
public class ComputerPageMapper {

  // Logger
  private static final Logger LOGGER = LoggerFactory.getLogger(ComputerPageMapper.class);

  /**
   * Initialize a ComputerPageDTO based on queryparameter.
   * 
   * @param page result search page
   * @return related ComputerPageDto
   */
  public static ComputerPageDto toComputerPageDto(String search, Page<ComputerDto> page) {

    ComputerPageDto computerPageDto = new ComputerPageDto(search, page);
    LOGGER.debug("\n\t\tMapper: map: page: {} \n\t\tTO:{}", page, computerPageDto);

    return computerPageDto;
  }

}
