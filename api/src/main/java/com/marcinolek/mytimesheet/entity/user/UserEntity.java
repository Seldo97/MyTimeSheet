package com.marcinolek.mytimesheet.entity.user;

import com.marcinolek.mytimesheet.entity.base.AbstractExtendedEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Table(name = "users")
@Entity
@Getter
@Setter
public class UserEntity extends AbstractExtendedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column
    private String name;

    @Column
    private String lastname;

    @Column
    private String email;

    @Column
    private String username;

    @Column
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_group_relation",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "user_group_id"))
    private List<UserGroupEntity> userGroups;

}