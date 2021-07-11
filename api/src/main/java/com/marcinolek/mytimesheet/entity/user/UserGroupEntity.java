package com.marcinolek.mytimesheet.entity.user;

import com.marcinolek.mytimesheet.entity.base.AbstractExtendedEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Table(name = "user_group")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserGroupEntity extends AbstractExtendedEntity {

    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "userGroup")
    private Set<Permission> permissions;

    public UserGroupEntity(Long id, LocalDateTime createDate, LocalDateTime editDate, String createdBy, String editedBy, Boolean removed, String name) {
        super(id, createDate, editDate, createdBy, editedBy, removed);
        this.name = name;
    }
}