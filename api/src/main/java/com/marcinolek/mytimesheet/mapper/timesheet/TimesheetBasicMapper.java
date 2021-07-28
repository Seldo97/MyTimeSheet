package com.marcinolek.mytimesheet.mapper.timesheet;

import com.marcinolek.mytimesheet.dto.timesheet.TimesheetBasicDTO;
import com.marcinolek.mytimesheet.entity.timesheet.TimesheetEntity;
import com.marcinolek.mytimesheet.mapper.base.AbstractMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TimesheetBasicMapper extends AbstractMapper<TimesheetBasicDTO, TimesheetEntity> {
}
