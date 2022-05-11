package com.zfl.demo.infrastructure.auth.entity;

import com.zfl.demo.common.BaseEntity;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;

@Data
@Entity
@ToString(callSuper = true)
@Access(AccessType.FIELD)
@EntityListeners(AuditingEntityListener.class)
public class SysPermission extends BaseEntity {

    private Integer parentId;
    private String name;
    private String url;
    private String permission;
}
