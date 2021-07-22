package com.marcinolek.mytimesheet.entity.user;

import com.marcinolek.mytimesheet.entity.base.AbstractEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Immutable;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "role")
@Entity
@Getter
@Setter
@NoArgsConstructor
@Immutable
public class RoleEntity extends AbstractEntity {

    String name;

    public RoleEntity(Long id, String name) {
        super(id);
        this.name = name;
    }
}
