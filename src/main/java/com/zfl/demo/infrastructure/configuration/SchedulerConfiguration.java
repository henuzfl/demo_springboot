package com.zfl.demo.infrastructure.configuration;

import org.quartz.Scheduler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;
import java.util.concurrent.Executor;

@Configuration
public class SchedulerConfiguration {

    private final DataSource dataSource;

    private final ThreadPoolTaskExecutor threadPoolTaskExecutor;

    public SchedulerConfiguration(DataSource dataSource, ThreadPoolTaskExecutor threadPoolTaskExecutor) {
        this.dataSource = dataSource;
        this.threadPoolTaskExecutor = threadPoolTaskExecutor;
    }

    @Bean
    public Scheduler scheduler() {
        return schedulerFactoryBean().getScheduler();
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        schedulerFactoryBean.setDataSource(dataSource);
        schedulerFactoryBean.setApplicationContextSchedulerContextKey("applicationContext");
        schedulerFactoryBean.setTaskExecutor(schedulerThreadPool());
        schedulerFactoryBean.setStartupDelay(10);
        return schedulerFactoryBean;
    }

    @Bean
    public Executor schedulerThreadPool() {
        return threadPoolTaskExecutor;
    }
}
