package com.marcinolek.mytimesheet.entity.user;

import com.marcinolek.mytimesheet.entity.base.AbstractExtendedEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Table(name = "users")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class UserEntity extends AbstractExtendedEntity {

    private String name;

    private String lastname;

    private String email;

    private String username;

    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<RoleEntity> roles;

    public UserEntity(Long id, LocalDateTime createDate, LocalDateTime editDate, String createdBy, String editedBy,
                      Boolean removed, String name, String lastname, String email, String username, String password) {
        super(id, createDate, editDate, createdBy, editedBy, removed);
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public UserEntity(Long id, LocalDateTime createDate, LocalDateTime editDate, String createdBy, String editedBy, Boolean removed, String name, String lastname, String email, String username, String password, List<RoleEntity> roles) {
        super(id, createDate, editDate, createdBy, editedBy, removed);
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }
}