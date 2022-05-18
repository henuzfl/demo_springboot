package com.zfl.demo.domain.account;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.zfl.demo.common.BaseEntity;
import com.zfl.demo.domain.order.Order;
import com.zfl.demo.infrastructure.auth.entity.SysUser;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Access(AccessType.FIELD)
@EntityListeners(AuditingEntityListener.class)
public class Account extends BaseEntity {

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sys_user_id", referencedColumnName = "id")
    @JsonBackReference
    private SysUser sysUser;
    private Integer money;

    @JsonBackReference
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Order> orders;
}
