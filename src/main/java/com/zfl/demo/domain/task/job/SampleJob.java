package com.zfl.demo.domain.task.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.*;

@PersistJobDataAfterExecution
@DisallowConcurrentExecution
@Slf4j
public class SampleJob implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        log.info("SampleJob execute");
    }
}
