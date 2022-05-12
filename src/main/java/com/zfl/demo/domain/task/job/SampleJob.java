package com.zfl.demo.domain.task.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.PersistJobDataAfterExecution;

@PersistJobDataAfterExecution
@DisallowConcurrentExecution
@Slf4j
public class SampleJob implements Job {

    @Override
    public void execute(JobExecutionContext context) {
        log.info("SampleJob execute");
    }
}
