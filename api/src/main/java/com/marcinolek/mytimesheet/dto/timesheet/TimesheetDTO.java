package com.marcinolek.mytimesheet.dto.timesheet;

import com.marcinolek.mytimesheet.dto.tag.TagDTO;
import com.marcinolek.mytimesheet.dto.task.TaskDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class TimesheetDTO extends TimesheetBasicDTO {

    private TaskDTO task;

    private List<TagDTO> tags;

}
