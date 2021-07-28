package com.marcinolek.mytimesheet.service.timesheet.impl;

import com.marcinolek.mytimesheet.dto.timesheet.TimesheetDTO;
import com.marcinolek.mytimesheet.entity.timesheet.TimesheetEntity;
import com.marcinolek.mytimesheet.service.base.impl.AbstractCrudServiceImpl;
import com.marcinolek.mytimesheet.service.timesheet.TimesheetService;
import org.springframework.stereotype.Service;

@Service
public class TimesheetServiceImpl extends AbstractCrudServiceImpl<TimesheetEntity, TimesheetDTO, Long> implements TimesheetService {
}
