package com.zfl.demo.infrastructure.auth.entity;


import com.zfl.demo.common.BaseEntity;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@ToString(callSuper = true)
@Access(AccessType.FIELD)
@EntityListeners(AuditingEntityListener.class)
public class SysUser extends BaseEntity {

    private String username;
    private String password;

    @ManyToMany
    @JoinTable(name = "sys_user_role", joinColumns = {@JoinColumn(name = "sys_user_id")}, inverseJoinColumns = {@JoinColumn(name = "sys_role_id")})
    private List<SysRole> roles;
}
