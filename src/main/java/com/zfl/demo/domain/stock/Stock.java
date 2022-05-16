package com.zfl.demo.domain.stock;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zfl.demo.common.BaseEntity;
import com.zfl.demo.domain.order.Order;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Access(AccessType.FIELD)
@EntityListeners(AuditingEntityListener.class)
public class Stock extends BaseEntity {

    private String name;
    private Integer count;
    private Integer price;

    @JsonIgnore
    @OneToMany(mappedBy = "stock", fetch = FetchType.LAZY)
    private List<Order> orders;
}
