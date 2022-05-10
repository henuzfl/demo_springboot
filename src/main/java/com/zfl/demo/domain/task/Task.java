package com.zfl.demo.domain.task;

import com.zfl.demo.domain.BaseEntity;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Data
@Entity
@ToString(callSuper = true)
@Access(AccessType.FIELD)
@EntityListeners(AuditingEntityListener.class)
public class Task extends BaseEntity {

    private String groupName;

    private String name;

    private String cronExpression;

    @Enumerated(EnumType.ORDINAL)
    private TaskState state;

    private String description;
}
