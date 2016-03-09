package com.excilys.cdb.mappers;

import com.excilys.cdb.dtos.ComputerDto;
import com.excilys.cdb.dtos.ComputerPageDto;
import com.excilys.cdb.models.QueryPageParameter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

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
   * @param qp
   *          queryParameter related to the page passed
   * @param computerDtos
   *          list of computer dto for the page
   * @return related ComputerPageDto
   */
  public static ComputerPageDto toComputerPageDto(QueryPageParameter qp,
      List<ComputerDto> computerDtos) {

    // Set up index by default if invalid
    if (qp.getPageIndex() < 1) {
      qp.setPageIndex(1);
    }
    // Set up pageSize by default if invalid
    if (qp.getPageSize() < 1) {
      qp.setPageSize(30);
    }

    // Calculate page count
    int count = getPageCount(qp.getPageSize(), qp.getMatchingRowCount());
    ComputerPageDto computerPageDto = new ComputerPageDto(qp, count, computerDtos);

    LOGGER.debug("\n\t\tMapper: map: [ qp: {}, computerDtos:{}] \n\t\tTO:",qp,computerDtos,computerPageDto);

    return computerPageDto;
  }

  /**
   * Calculate the page count based on pageSize and pageIndex.
   * 
   * @param pageSize
   *          pageSize
   * @param totalRows
   *          total rows matching
   * @return count of pages
   */
  private static int getPageCount(int pageSize, long totalRows) {

    if ((totalRows % pageSize) == 0) {
      return (int) Math.floorDiv(totalRows, pageSize);
    }

    return (int) Math.floorDiv(totalRows, pageSize) + 1;
  }

}
