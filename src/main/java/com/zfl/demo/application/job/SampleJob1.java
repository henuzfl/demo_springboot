package com.zfl.demo.application.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.*;

/**
 * @author zhangfeilong
 * 示例job1
 */
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
@Slf4j
public class SampleJob1 implements Job {

    @Override
    public void execute(JobExecutionContext context) {
        log.info("SampleJob1 is executing.");
    }
}
