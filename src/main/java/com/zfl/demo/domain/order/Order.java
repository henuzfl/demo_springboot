package com.zfl.demo.domain.order;

import com.zfl.demo.common.BaseEntity;
import com.zfl.demo.domain.account.Account;
import com.zfl.demo.domain.stock.Stock;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Data
@Entity
@Table(name = "order_tbl")
@ToString(callSuper = true)
@Access(AccessType.FIELD)
@EntityListeners(AuditingEntityListener.class)
public class Order extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "stock_id")
    private Stock stock;
    private Integer count;
    private Integer money;
}
