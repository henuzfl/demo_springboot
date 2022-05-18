package com.zfl.demo.application.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.*;

/**
 * @author zhangfeilong
 * @description 示例job2
 */
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
@Slf4j
public class SampleJob2 implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        log.info("SampleJob2 is executing.");
    }
}
