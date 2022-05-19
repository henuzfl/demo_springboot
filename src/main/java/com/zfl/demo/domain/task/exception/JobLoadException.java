package com.zfl.demo.domain.task.exception;

/**
 * @author zhangfeilong
 * @created_at 2022/5/19 10:21
 */
public class JobLoadException extends RuntimeException {

    public JobLoadException() {
        super("加载任务失败");
    }
}
