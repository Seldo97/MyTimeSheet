package com.marcinolek.mytimesheet.entity.user;

import com.marcinolek.mytimesheet.entity.base.BaseExtendedEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "user_group")
@Entity
@Getter
@Setter
public class UserGroupEntity extends BaseExtendedEntity {

    @Column
    String name;

}