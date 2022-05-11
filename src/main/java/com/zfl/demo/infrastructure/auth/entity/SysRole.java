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
public class SysRole extends BaseEntity {

    private String name;
    private String description;

    @ManyToMany
    @JoinTable(name = "sys_role_permission", joinColumns = {@JoinColumn(name = "sys_role_id")}, inverseJoinColumns = {@JoinColumn(name = "sys_permission_id")})
    private List<SysPermission> permissions;
}
