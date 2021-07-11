package com.marcinolek.mytimesheet.entity.base;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AbstractExtendedEntity extends AbstractEntity {

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    protected LocalDateTime createDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    protected LocalDateTime editDate;

    protected String createdBy;

    protected String editedBy;

    protected Boolean removed = false;

    public AbstractExtendedEntity(Long id, LocalDateTime createDate, LocalDateTime editDate, String createdBy, String editedBy, Boolean removed) {
        super(id);
        this.createDate = createDate;
        this.editDate = editDate;
        this.createdBy = createdBy;
        this.editedBy = editedBy;
        this.removed = removed;
    }
}
