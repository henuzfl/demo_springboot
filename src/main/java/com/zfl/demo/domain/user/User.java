package com.zfl.demo.domain.user;

import com.zfl.demo.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@Entity
@ToString(callSuper = true)
@Access(AccessType.FIELD)
@EntityListeners(AuditingEntityListener.class)
public class User extends BaseEntity {

    @NotEmpty(message = "名称不允许为空")
    private String name;

    @NotEmpty(message = "年龄不允许为空")
    private Integer age;


}
