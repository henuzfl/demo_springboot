package com.zfl.demo.domain.task;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.persistence.EntityExistsException;
import java.util.List;

@Service
@Slf4j
public class TaskService {

    private final Scheduler scheduler;

    private final TaskRepository taskRepository;

    public TaskService(Scheduler scheduler, TaskRepository taskRepository) {
        this.scheduler = scheduler;
        this.taskRepository = taskRepository;
    }

    @PostConstruct
    public void init() throws SchedulerException {
        this.scheduler.start();
    }

    public List<Task> getAllTasks() {
        return Lists.newArrayList(this.taskRepository.findAll());
    }

    public Task create(Task task) throws SchedulerException {
        if (taskRepository.existsByGroupNameAndName(task.getGroupName(), task.getName())) {
            throw new EntityExistsException("Task already exists");
        }
        addJobToScheduler(task.getGroupName(), task.getName(), task.getCronExpression());
        this.taskRepository.save(task);
        return task;
    }

    public void pause(long id) throws SchedulerException {
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        if (task.getState() == TaskState.PAUSED) {
            return;
        }
        scheduler.pauseJob(JobKey.jobKey(task.getName(), task.getGroupName()));
        task.setState(TaskState.PAUSED);
        taskRepository.save(task);
    }

    public void resume(long id) throws SchedulerException {
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        if (task.getState() == TaskState.NORMAL) {
            return;
        }
        scheduler.resumeJob(JobKey.jobKey(task.getName(),
                task.getGroupName()));
        task.setState(TaskState.NORMAL);
        this.taskRepository.save(task);
    }

    public void delete(long id) throws SchedulerException {
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        TriggerKey triggerKey = TriggerKey.triggerKey(task.getName(), task.getGroupName());
        scheduler.pauseTrigger(triggerKey);
        scheduler.unscheduleJob(triggerKey);
        scheduler.deleteJob(JobKey.jobKey(task.getName(), task.getGroupName()));
        taskRepository.delete(task);
    }

    private void addJobToScheduler(String groupName, String className,
                                   String cronExpression) throws SchedulerException {
        JobDetail newJob = JobBuilder.
                newJob(loadJob(className).getClass())
                .withIdentity(className, groupName).
                build();
        CronScheduleBuilder cron =
                CronScheduleBuilder.cronSchedule(cronExpression);
        CronTrigger trigger = TriggerBuilder.newTrigger().
                withIdentity(className, groupName).
                withSchedule(cron).
                build();
        scheduler.scheduleJob(newJob, trigger);
    }

    public static Job loadJob(String className) {
        try {
            return (Job) Class.forName(className).getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Failed to load job", e);
        }
    }
}
