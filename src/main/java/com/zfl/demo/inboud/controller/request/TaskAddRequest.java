package com.zfl.demo.inboud.controller.request;

import lombok.Data;

/**
 * @author zhangfeilong
 * @created_at 2022/5/19 10:16
 */
@Data
public class TaskAddRequest {

    private String groupName;

    private String name;

    private String cronExpression;

    private String description;
}
