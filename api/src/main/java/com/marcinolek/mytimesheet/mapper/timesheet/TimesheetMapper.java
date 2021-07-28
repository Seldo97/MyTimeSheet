package com.marcinolek.mytimesheet.mapper.timesheet;

import com.marcinolek.mytimesheet.dto.timesheet.TimesheetDTO;
import com.marcinolek.mytimesheet.entity.timesheet.TimesheetEntity;
import com.marcinolek.mytimesheet.mapper.base.AbstractMapper;
import com.marcinolek.mytimesheet.mapper.tag.TagMapper;
import com.marcinolek.mytimesheet.mapper.task.TaskMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {TaskMapper.class, TagMapper.class})
public interface TimesheetMapper extends AbstractMapper<TimesheetDTO, TimesheetEntity> {
}
