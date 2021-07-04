package com.marcinolek.mytimesheet.entity.user;

import com.marcinolek.mytimesheet.entity.base.AbstractExtendedEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Table(name = "user_group")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserGroupEntity extends AbstractExtendedEntity {

    @Column
    String name;

    public UserGroupEntity(Long id, LocalDateTime createDate, LocalDateTime editDate, String createdBy, String editedBy, Boolean removed, String name) {
        super(id, createDate, editDate, createdBy, editedBy, removed);
        this.name = name;
    }
}