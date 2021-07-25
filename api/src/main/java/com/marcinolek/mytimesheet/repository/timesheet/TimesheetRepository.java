package com.marcinolek.mytimesheet.repository.timesheet;

import com.marcinolek.mytimesheet.entity.timesheet.TimesheetEntity;
import com.marcinolek.mytimesheet.repository.base.AbstractRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimesheetRepository extends AbstractRepository<TimesheetEntity, Long> {
}
