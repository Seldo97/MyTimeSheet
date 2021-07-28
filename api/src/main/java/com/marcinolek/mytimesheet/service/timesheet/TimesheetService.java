package com.marcinolek.mytimesheet.service.timesheet;

import com.marcinolek.mytimesheet.dto.timesheet.TimesheetDTO;
import com.marcinolek.mytimesheet.entity.timesheet.TimesheetEntity;
import com.marcinolek.mytimesheet.service.base.AbstractCrudService;

public interface TimesheetService extends AbstractCrudService<TimesheetEntity, TimesheetDTO, Long> {
}
